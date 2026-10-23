package com.ryan.f1tracker;

import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.net.URI;


@Service
public class JolpicaClient {
    private static final String BASE_URL = "https://api.jolpi.ca/ergast/f1";

    private final HttpClient httpClient;

    public JolpicaClient() {
        httpClient = HttpClient.newHttpClient();
    }

    private JsonObject getJson(String url) 
        throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Accept", "application/json")
            .GET()
            .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException(
                     "Jolpica request failed. Status: "
                    + response.statusCode()
                );
            }

            return JsonParser.parseString(response.body())
                .getAsJsonObject();
            }

            public Race fetchRace(int season, int round)
                throws IOException, InterruptedException {
                    String url = BASE_URL + "/" + season + "/" + round + "/results.json?limit=100";

                    JsonObject root = getJson(url);

                    JsonObject mrData = root.getAsJsonObject("MRData");

                    JsonObject raceTable = mrData.getAsJsonObject("RaceTable");
                    
                    JsonArray races = raceTable.getAsJsonArray("Races");

                    if (races == null || races.isEmpty()) {
                        throw new IOException ("No race found for season " + season + ", round " 
                        + round + ".");
                    }

                    JsonObject raceData = races.get(0).getAsJsonObject();

                    String raceName = raceData.get("raceName").getAsString();

                    String trackName = raceData
                        .getAsJsonObject("Circuit")
                        .get("circuitName")
                        .getAsString();

                    int actualRound = raceData.get("round").getAsInt();

                    Race race = new Race(raceName, trackName, actualRound);

                    JsonArray results = raceData.getAsJsonArray("Results");

                    List<Driver> drivers = new ArrayList<>();

                    for (JsonElement element : results) {
                        JsonObject result = element.getAsJsonObject();

                        JsonObject driverData =
                                result.getAsJsonObject("Driver");

                        JsonObject constructorData =
                                result.getAsJsonObject("Constructor");

                        String name =
                                driverData.get("givenName").getAsString()+ " " 
                                + driverData.get("familyName").getAsString();

                        String team = constructorData.get("name").getAsString();

                        int driverNum = result.get("number").getAsInt();

                        int finishPosition = result.get("position").getAsInt();

                        double points = result.get("points").getAsDouble();

                        Driver driver = new Driver(name, team, driverNum);

                        driver.addRaceResult(finishPosition, points);

                        drivers.add(driver);
                    }

                    race.setActualResult(drivers);

                    return race;
                }

            public Standings fetchDriverStandings(int season) 
                throws IOException, InterruptedException {
                    String url = BASE_URL + ""
            }

            

}


