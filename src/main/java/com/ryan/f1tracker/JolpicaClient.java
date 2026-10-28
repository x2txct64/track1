package com.ryan.f1tracker;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class JolpicaClient {
    private final HttpClient httpClient;
    // CHANGED: made final like httpClient; the mapper is never reassigned
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL = "https://api.jolpi.ca/ergast/f1/";


    public JolpicaClient() {
        httpClient = HttpClient.newHttpClient();
    }

    private String fetchJson(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + path))
            .header("accept", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            // CHANGED: added ": " so the message reads "failed: 429" not "failed429"
            throw new IOException("Jolpica request failed: " + response.statusCode());
        }
        return response.body();
    }

    private JsonNode fetchTree(String path) throws IOException, InterruptedException {
        return mapper.readTree(fetchJson(path));
    }

    public List<Race> getRaces(int season) throws IOException, InterruptedException {
        JsonNode tree = fetchTree(season + ".json");
        List<Race> races = new ArrayList<>();

        for (JsonNode r : tree.at("/MRData/RaceTable/Races")) {
            races.add(new Race(
                r.path("season").asInt(),
                r.path("round").asInt(),
                r.path("raceName").asText(""),
                r.at("/Circuit/circuitName").asText(""),
                r.at("/Circuit/Location/locality").asText(""),
                r.at("/Circuit/Location/country").asText(""),
                r.path("date").asText(""),
                r.path("time").asText("")));
        }
        return races;
    }

    public List<Driver> getDrivers(int season) throws IOException, InterruptedException {
        JsonNode tree = fetchTree(season + "/drivers.json");
        List<Driver> drivers = new ArrayList<>();

        for (JsonNode d : tree.at("/MRData/DriverTable/Drivers")) {
            drivers.add(new Driver(
                d.path("driverId").asText(""),
                d.path("givenName").asText(""),
                d.path("familyName").asText(""),
                d.path("code").asText(""),
                d.path("nationality").asText(""),
                d.path("permanentNumber").asInt(0)));
        }

        return drivers;
    }

    public Standings getDriverStandings(int season) throws IOException, InterruptedException {
        JsonNode tree = fetchTree(season + "/driverStandings.json");
        JsonNode list = tree.at("/MRData/StandingsTable/StandingsLists/0");

        Standings standings = new Standings(
            list.path("season").asInt(season),
            list.path("round").asInt(0));

        for (JsonNode n : list.path("DriverStandings")) {
            JsonNode d = n.path("Driver");

        Driver driver = new Driver(
            d.path("driverId").asText(""),
            d.path("givenName").asText(""),
            d.path("familyName").asText(""),
            d.path("code").asText(""),
            d.path("nationality").asText(""),
            d.path("permanentNumber").asInt(0));

        driver.setTeam(n.at("/Constructors/0/name").asText(""));
        driver.setCurrentPoints(n.path("points").asDouble());
        driver.setWins(n.path("wins").asInt());
        driver.setPosition(n.path("position").asInt());

        standings.addDriver(driver);

        }

        return standings;
    }

}