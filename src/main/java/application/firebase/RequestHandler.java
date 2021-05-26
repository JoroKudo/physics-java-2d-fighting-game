package application.firebase;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RequestHandler {

    private final String baseUrl = "https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/";
    private final JSONParser jsonParser = new JSONParser();

    public long getWins(String user) throws IOException {
        URL url = new URL(baseUrl + "fighters/" + user + ".json");
        HttpURLConnection con = initializeConnection(url, "GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) jsonParser.parse(content.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (long) jsonObject.get("wins");
    }

    public ArrayList<String> getAllFighters() throws IOException {
        ArrayList<String> allFighters = new ArrayList<>();
        URL url = new URL(baseUrl + "fighters.json");
        HttpURLConnection con = this.initializeConnection(url, "GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            allFighters.add(inputLine);
        }
        in.close();
        con.disconnect();
        return allFighters;
    }

    public boolean checkIfFighterExists(String user) throws IOException {
        URL url = new URL(baseUrl + "fighters/" + user + ".json");
        HttpURLConnection con = this.initializeConnection(url, "GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        String data = content.toString();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) jsonParser.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (jsonObject != null);
    }

    public void addFight(String p1, String p2, String winner) throws IOException {
        URL url = new URL(baseUrl + "fightLog/fights.json");
        HttpURLConnection con = this.initializeConnection(url, "POST");
        con.setDoOutput(true);
        String jsonInputString = "{\"User1\": \"" + p1 + "\", \"User2\":\"" + p2 + "\", \"Winner\": \"" + winner + "\" }";
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        con.getInputStream();
    }


    public void addWin(String user) throws IOException {
        long wins = getWins(user);
        wins += 1;
        URL url = new URL(baseUrl + "fighters/" + user + ".json");
        HttpURLConnection con = this.initializeConnection(url, "PUT");
        con.setDoOutput(true);
        String jsonInputString = "{\n" +
                "\"wins\": " + wins + "\n" +
                "}";
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        con.getInputStream();
    }

    public void addFighter(String user) throws IOException {
        URL url = new URL(baseUrl + "fighters/" + user + ".json");
        HttpURLConnection con = this.initializeConnection(url, "PUT");
        con.setDoOutput(true);
        String jsonInputString = """
                {
                "wins": 0
                }""";
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }
    }

    private HttpURLConnection initializeConnection(URL url, String requestMethod) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(requestMethod);
        if (requestMethod.equals("GET")) {
            con.setRequestProperty("Content-Type", "application/json");
        } else {
            con.setRequestProperty("Accept", "application/json");
        }
        return con;
    }

}
