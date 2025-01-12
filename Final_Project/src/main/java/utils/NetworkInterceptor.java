package utils;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Request;
import org.openqa.selenium.devtools.v130.network.model.Response;

public class NetworkInterceptor {
	private DevTools devTools;
	private Request request;
	private Response response;

	private NetworkInterceptor(DevTools devTools) {
		this.devTools = devTools;
	}

	public static NetworkInterceptor initNetworkInterceptor(ChromeDriver driver) {
		NetworkInterceptor networkInterceptor = new NetworkInterceptor(driver.getDevTools());
		networkInterceptor.devTools.createSession();
		networkInterceptor.devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		return networkInterceptor;
	}

	public void closeDevToolsSession() {
		if (devTools != null) {
			devTools.close();
		}
	}

	public Request getRequest() {
		return request;
	}

	public Response getResponse() {
		return response;
	}

	public void interceptRequestForUrl(String requestUrl) {
		try {
			// Listener to capture request events:
			devTools.addListener(Network.requestWillBeSent(), requestSent -> {
				Request request = requestSent.getRequest();
				if (request.getUrl().equals(requestUrl)) {
					this.request = request;
					System.out.println("Request URL: " + this.request.getUrl());
					System.out.println("Request Method: " + this.request.getMethod());
					System.out.println("Request Headers: " + this.request.getHeaders());
					if (this.request.getPostData().isPresent()) {
						System.out.println("Request Post Data: " + this.request.getPostData().get());
					}
				}
			});
			Thread.sleep(4000);
		} catch (Exception e) {
			closeDevToolsSession();
			throw new RuntimeException("Failed listening to network requests");
		}
	}

	public void interceptResponseForUrl(String requestUrl) {
		try {
			// Listener to capture response events:
			devTools.addListener(Network.responseReceived(), responseReceived -> {
				Response response = responseReceived.getResponse();
				if (response.getUrl().equals(requestUrl)) {
					this.response = response;
					System.out.println("Response URL: " + this.response.getUrl());
					System.out.println("Response Status: " + this.response.getStatus());
				}
			});
			Thread.sleep(4000);
		} catch (Exception e) {
			closeDevToolsSession();
			throw new RuntimeException("Failed listening to network requests");
		}
	}
}
