package application.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request {

    String urlString;
    String requestMethod;
    String input;

    public Request(String url, String method, String input) {
        urlString = url;
        requestMethod = method;
        this.input = input;
    }

    public void doRequest() throws IOException {

        URL url = new URL("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/.json");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("PUT");

        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        String jsonInputString = "{\n" +
                "    \"user\": {\n" +
                "        \"fabian\": {\n" +
                "            \"password\": \"1234\",\n" +
                "            \"email\": \"fabian.surber@gmail.com\"\n" +
                "        },\n" +
                "        \"leo\": {\n" +
                "            \"password\": \"abc\",\n" +
                "            \"email\": \"leo.röttele@gmail.com\"\n" +
                "        }\n" +
                "    }\n" +
                "}\n";

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}

