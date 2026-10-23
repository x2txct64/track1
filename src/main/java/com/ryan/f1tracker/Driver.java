package com.ryan.f1tracker;

import java.util.ArrayList;
import java.util.List;
public class Driver {
    private String name;
    private String team;
    private int driverNum;
    private double currentPoints;
    private List<Integer> recentFinishes;
    private int qualifyingPosition;

    public Driver(String name, String team, int driverNum) {
        this.name = name;
        this.team = team;
        this.driverNum = driverNum;
        this.currentPoints = 0;
        this.recentFinishes = new ArrayList<>();
    }

    public void addRaceResult(int finishPosition, double pointsEarned) {
        recentFinishes.add(finishPosition);
        currentPoints += pointsEarned;
    }

    public double getRecentForm() {
        if (recentFinishes.isEmpty()) return 0;
        int sum = 0;
        for (int pos : recentFinishes) {
            sum += pos;
        }
        return (double) sum / recentFinishes.size();
    }

    public List<Integer> getRecentFinishes() {
        return recentFinishes;
    }

    public int getQualifyingPosition() {
        return qualifyingPosition;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getDriverNum() {
        return driverNum;
    }

    public double getCurrentPoints() {
        return currentPoints;
    }

    public void setQualifyingPosition(int position) {
        this.qualifyingPosition = position;
    }
}