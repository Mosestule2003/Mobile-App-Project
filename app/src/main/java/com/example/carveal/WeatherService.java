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
        void onWeatherResult(String location, String condition, double temperature, boolean isFavorable);
    }

    public static void checkWeather(String location, WeatherCallback callback) {
        new AsyncTask<Void, Void, WeatherData>() {
            @Override
            protected WeatherData doInBackground(Void... voids) {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    String urlString = BASE_URL + "?key=" + API_KEY + "&q=" + location;
                    URL url = new URL(urlString);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(15000);
                    connection.setReadTimeout(15000);

                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    JSONObject json = new JSONObject(response.toString());
                    String locationName = json.getString("location");
                    String condition = json.getJSONObject("current")
                            .getJSONObject("condition")
                            .getString("text")
                            .toLowerCase();
                    double temperature = json.getJSONObject("current").getDouble("temp_c");

                    boolean isFavorable = !(condition.contains("rain") || condition.contains("snow"));
                    return new WeatherData(locationName, condition, temperature, isFavorable);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onPostExecute(WeatherData weatherData) {
                if (weatherData != null) {
                    callback.onWeatherResult(weatherData.location, weatherData.condition, weatherData.temperature, weatherData.isFavorable);
                }
            }
        }.execute();
    }

    static class WeatherData {
        String location;
        String condition;
        double temperature;
        boolean isFavorable;

        WeatherData(String location, String condition, double temperature, boolean isFavorable) {
            this.location = location;
            this.condition = condition;
            this.temperature = temperature;
            this.isFavorable = isFavorable;
        }
    }
}
