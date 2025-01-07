package pages;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeminiApiClient {

    private static final String API_URL_FILE = "credentials/API_URL.txt";
    private static final String API_KEY_FILE = "credentials/API_Key.txt";
    private static final String DEFAULT_OUTPUT_DIR = "src/test/resources/geminiAPI_output";
    private static final String JSON_PAYLOAD_FILE = "credentials/Json_Payload.txt";
    
    // Static block for directory validation and initialization
    static {
        Path defaultOutputDirPath = Paths.get(DEFAULT_OUTPUT_DIR);
        try {
            if (!Files.exists(defaultOutputDirPath)) {
                Files.createDirectories(defaultOutputDirPath);
            } else if (!Files.isDirectory(defaultOutputDirPath)) {
                throw new IllegalStateException("Default output directory is not a valid directory: " + DEFAULT_OUTPUT_DIR);
            } else if (!Files.isWritable(defaultOutputDirPath)) {
                throw new IllegalStateException("Default output directory is not writable: " + DEFAULT_OUTPUT_DIR);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize default output directory: " + e.getMessage(), e);
        }
    }   
    
    
    
    public String generateContent(String prompt, String imageBase64) throws Exception {
        

        String apiUrl = readFromFile(API_URL_FILE);
        String apiKey = readFromFile(API_KEY_FILE);
        String jsonFormat = readFromFile(JSON_PAYLOAD_FILE);	
       
        String jsonPayload = jsonFormat.formatted(prompt, imageBase64);
        
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl.trim() + "?key=" + apiKey.trim()))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonPayload))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Request failed with status code: " + response.statusCode() + " and body: " + response.body());
        }
    }

    public String readFromFile(String filePath) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + filePath);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public void saveResponseToFile(String testNameFolder, String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);

        JsonNode textNode = rootNode.at("/candidates/0/content/parts/0/text");
        String textContent = textNode.asText();

        if (textContent.isEmpty()) {
            throw new IOException("Extracted text content is empty. Please verify the JSON response structure.");
        }

        // Create the output directory path dynamically
        Path outputDirPath = Paths.get(DEFAULT_OUTPUT_DIR, testNameFolder);
        Files.createDirectories(outputDirPath);

        // Create a unique file inside the folder
        Path outputPath = outputDirPath.resolve(System.currentTimeMillis() + ".txt");
        Files.write(outputPath, textContent.getBytes(StandardCharsets.UTF_8));

        System.out.println("Response saved to: " + outputPath);
    
    }

    public static String encodeImageToBase64(String imagePath) {
        try (InputStream inputStream = GeminiApiClient.class.getClassLoader().getResourceAsStream(imagePath)) {
            if (inputStream == null) {
                throw new IOException("Image file not found: " + imagePath);
            }
            return Base64.getEncoder().encodeToString(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
