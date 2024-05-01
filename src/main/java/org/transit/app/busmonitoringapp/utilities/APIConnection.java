package org.transit.app.busmonitoringapp.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIConnection extends Throwable {
    public static JsonElement fetchArticles() {
        String apiKey = "d9f546c2edfb4a71aaf9244ce4931ff6";
        String apiUrl = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=" + apiKey;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JsonParser parser = new JsonParser();
                return parser.parse(response.toString()).getAsJsonObject();
            } else {
                System.out.println("Error fetching articles: HTTP error code " + responseCode);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error fetching articles: " + e.getMessage());
            return null;
        }
    }


    // for TESTING
    public static void main(String[] args) {
        JsonObject articles = (JsonObject) fetchArticles();
            if (articles != null) {
                System.out.println(articles);
            }
        }
    }

