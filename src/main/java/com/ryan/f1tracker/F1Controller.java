package com.ryan.f1tracker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class F1Controller {
    private final JolpicaClient client;

    public F1Controller(JolpicaClient client) {
        this.client = client;
    }

    @GetMapping("/races/{season}")
    public List<Race> races(@PathVariable int season) throws IOException, InterruptedException {
        return client.getRaces(season);
    }

    @GetMapping("/drivers/{season}")
    public List<Driver> drivers(@PathVariable int season) throws IOException, InterruptedException {
        return client.getDrivers(season);
    }

    @GetMapping("/standings/{season}")
    public Standings standings(@PathVariable int season) throws IOException, InterruptedException {
        return client.getDriverStandings(season);
    }

    @ExceptionHandler({ IOException.class, InterruptedException.class })
    public ResponseEntity<Map<String, String>> upstreamFailed(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
            .body(Map.of("error", "Could not load data from Jolpica: " + e.getMessage()));
    }
}
