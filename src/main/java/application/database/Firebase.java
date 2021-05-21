package application.database;

import org.apache.commons.math3.analysis.function.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class Firebase {

    private String inline;
    private JSONParser jsonParser = new JSONParser();

    public void addFight(String p1, String p2, String winner) throws IOException {
        URL url = new URL("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fightLog/fights.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{\"User1\": \"" + p1 + "\", \"User2\":\"" + p2 + "\", \"Winner\": \"" + winner + "\" }";
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

    public void getLeaderBoard() throws IOException, ParseException {
        URL url = new URL("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fightLog.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        String testValue = (content.toString());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) jsonParser.parse(testValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        JSONObject fightObject = (JSONObject) jsonObject.get("fight");
//        String winner = (String) fightObject.get("Winner");
//        System.out.println("Winner: " + winner);
    }

    public long getWins(String user) throws IOException {
        URL url = new URL("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fighters/" + user + ".json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
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
        long wins = (long) jsonObject.get("wins");
        return wins;
    }

    public void addWin(String user) throws IOException {
        long wins = getWins(user);
        wins += 1;
        URL url = new URL("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fighters/" + user + ".json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{\n" +
                "\"wins\": " + wins + "\n" +
                "}";
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

    public boolean checkIfFighterExists(String user) throws IOException {
        URL url = new URL("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fighters/" + user + ".json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
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

    public void addFighter(String user) throws IOException {
        URL url = new URL("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fighters/" + user + ".json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{\n" +
                "\"wins\": 0\n" +
                "}";
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



/*
    public void doRequest(String urlString, String method, String inputString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        if (method.equals("PUT")) {
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = inputString;
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
        if (method.equals("GET")) {
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            String contentType = con.getHeaderField("Content-Type");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            System.out.println(content.toString());
        }
    }

    public int getNumberOfFights(String urlString) throws IOException {
        URL url = new URL(urlString);
        int numberOfFights = 0;
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
            numberOfFights++;
        }
        return numberOfFights;
    }
*/


