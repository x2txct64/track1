package com.ryan.f1tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/f1")
public class F1Controller {
    private final JolpicaClient jolpicaClient;

    public F1Controller(JolpicaClient jolpicaClient) {
        this.jolpicaClient = jolpicaClient;
    }

    @GetMapping("/{season}/races/{round}")
    public Race getRace(@PathVariable int season, @PathVariable int round)
        throws IOException, InterruptedException {
            return jolpicaClient.fetchRace(season, round);
        }

        
    
}
