package com.ryan.f1tracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Standings {
    private List<Driver> drivers;

    public Standings() {
        this.drivers = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public List<Driver> getSortedStandings() {
        List<Driver> sorted = new ArrayList<>(drivers);
        sorted.sort(Comparator.comparingInt(Driver :: getCurrentPoints).reversed());
        return sorted;
    }
}
