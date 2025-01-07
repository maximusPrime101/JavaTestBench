package pages;

import java.io.IOException;

public class GeminiApiService {

    private GeminiApiClient geminiApiClient;

    public GeminiApiService(GeminiApiClient geminiApiClient) {
        this.geminiApiClient = geminiApiClient;
    }

    public String callGeminiApi(String promptPath, String imagePath, String outputDir) throws IOException {
        try {
			// Read the prompt from the file
			String prompt = geminiApiClient.readFromFile(promptPath);
			
			// Encode the image to Base64
			String imageBase64 = GeminiApiClient.encodeImageToBase64(imagePath);
			
			// Make the API call
			String response = geminiApiClient.generateContent(prompt, imageBase64);
			
			// Save the response to a file in the output directory
			geminiApiClient.saveResponseToFile(outputDir, response);

			return response;
		} catch (IOException e) {
			
			e.printStackTrace();
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
    }
    
    public GeminiApiClient getGeminiApiClient() {
        return geminiApiClient;
    }
}
