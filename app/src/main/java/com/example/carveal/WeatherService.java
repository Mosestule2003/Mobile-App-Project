package com.example.carveal;

import android.os.AsyncTask;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class WeatherService {
    private static final String API_KEY = "50ee6b6417ee4ac583f04828252703";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    public interface WeatherCallback {
        void onWeatherResult(boolean isFavorable);
    }

    public static void checkWeather(String location, WeatherCallback callback) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    String urlString = BASE_URL + "?key=" + API_KEY + "&q=" + location;
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JSONObject json = new JSONObject(response.toString());
                    String condition = json.getJSONObject("current")
                            .getJSONObject("condition")
                            .getString("text")
                            .toLowerCase();
                    return !(condition.contains("rain") || condition.contains("snow"));
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }

            @Override
            protected void onPostExecute(Boolean isFavorable) {
                callback.onWeatherResult(isFavorable);
            }
        }.execute();
    }
}
