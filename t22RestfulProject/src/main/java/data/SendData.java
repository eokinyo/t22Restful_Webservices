package data;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SendData {
    private static final String SERVER_URL = "http://127.0.0.1:8080/rest/t22RestfulProject/setfeedbackcollection";

    public static void sendData(int currentSpeed, int distanceTravelled,int currentIntensity,int timeTaken) {
        try {
            URL url = new URL(SERVER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);

            String jsonPayload = String.format("{\"initialSpeed\": %d, \"initialDistance\": %d,\"currentIntensity\": %d,\"timeTaken\": %d}", currentSpeed, distanceTravelled,currentIntensity,timeTaken);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                System.out.println("Data sent successfully.");
            } else {
                System.out.println("Failed to send data. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

