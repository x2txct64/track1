package com.ryan.f1tracker;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private int season;
    private int round;
    private String raceName;
    private String trackName;     // Circuit.circuitName
    private String locality;      // Circuit.Location.locality
    private String country;       // Circuit.Location.country
    private String date;          // "2026-03-15"
    private String time;          // "04:00:00Z" (may be empty)
    private boolean isComplete;
    private List<Driver> qualifyingOrder;
    private List<Driver> actualResults;

    public Race(int season, int round, String raceName, String trackName,
                String locality, String country, String date, String time) {
        this.season = season;
        this.round = round;
        this.raceName = raceName;
        this.trackName = trackName;
        this.locality = locality;
        this.country = country;
        this.date = date;
        this.time = time;
        this.isComplete = false;
        this.qualifyingOrder = new ArrayList<>();
        this.actualResults = new ArrayList<>();
    }

    public int getSeason() {
        return season;
    }

    public int getRound() {
        return round;
    }

    public String getRaceName() {
        return raceName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public List<Driver> getQualifyingOrder() {
        return qualifyingOrder;
    }

    public List<Driver> getActualResults() {
        return actualResults;
    }

    public void setQualifyingOrder(List<Driver> order) {
        this.qualifyingOrder = order;
    }

    public void setActualResult(List<Driver> order) {
        this.actualResults = order;
        this.isComplete = true;
    }
}
