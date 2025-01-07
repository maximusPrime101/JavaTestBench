package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GeminiApiService {

	private GeminiApiClient geminiApiClient;

	// Retry settings
	private static final int MAX_RETRIES = 3;
	private static final int RETRY_DELAY = 2;

	public GeminiApiService(GeminiApiClient geminiApiClient) {
		this.geminiApiClient = geminiApiClient;
	}

	public String callGeminiApi(String promptPath, String imagePath, String outputDir) throws IOException,Exception  {
		String response = null;
		int attempt = 0;

		while (attempt < MAX_RETRIES) {
			try {
				// Interact with GeminiApiClient
				String prompt = geminiApiClient.readFromFile(promptPath);
				String imageBase64 = GeminiApiClient.encodeImageToBase64(imagePath);

				// call API
				response = geminiApiClient.generateContent(prompt, imageBase64);

				// Save response
				geminiApiClient.saveResponseToFile(outputDir, response);

				break;

			} catch (RuntimeException | IOException e) {
				attempt++;
				System.err.println("===============\nAttempt " + attempt + " failed: " + e.getMessage());

				if (attempt < MAX_RETRIES) {
					try {
						TimeUnit.SECONDS.sleep(RETRY_DELAY);
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
						throw new IOException("Retry interrupted", ie);
					}
				} else {
					throw new IOException("Max retries reached. API call failed.", e);
				}
			}
		}
		return response;
	}

	public GeminiApiClient getGeminiApiClient() {
		return geminiApiClient;
	}
}
