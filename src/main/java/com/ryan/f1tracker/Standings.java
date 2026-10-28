package com.ryan.f1tracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Standings {
    private int season;
    private int round;   // standings after this round
    private List<Driver> drivers;

    public Standings(int season, int round) {
        this.season = season;
        this.round = round;
        this.drivers = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public int getSeason() {
        return season;
    }

    public int getRound() {
        return round;
    }

    public List<Driver> getSortedStandings() {
        List<Driver> sorted = new ArrayList<>(drivers);
        sorted.sort(Comparator.comparingInt(Driver::getPosition));
        return sorted;
    }
}
