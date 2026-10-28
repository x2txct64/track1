package com.ryan.f1tracker;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    // Fields that come straight from Jolpica
    private String driverId;      // "max_verstappen"
    private String givenName;
    private String familyName;
    private String code;          // "VER"
    private String nationality;
    private int driverNum;        // permanentNumber
    private String team;          // constructor name (from standings)
    private double currentPoints;
    private int wins;
    private int position;         // championship position

    // Fields for your own tracking
    private List<Integer> recentFinishes;
    private int qualifyingPosition;

    public Driver(String driverId, String givenName, String familyName, String code,
                  String nationality, int driverNum) {
        this.driverId = driverId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.code = code;
        this.nationality = nationality;
        this.driverNum = driverNum;
        this.team = "";
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

    public String getDriverId() {
        return driverId;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getName() {
        return givenName + " " + familyName;
    }

    public String getCode() {
        return code;
    }

    public String getNationality() {
        return nationality;
    }

    public int getDriverNum() {
        return driverNum;
    }

    public String getTeam() {
        return team;
    }

    public double getCurrentPoints() {
        return currentPoints;
    }

    public int getWins() {
        return wins;
    }

    public int getPosition() {
        return position;
    }

    public int getQualifyingPosition() {
        return qualifyingPosition;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setCurrentPoints(double currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setQualifyingPosition(int position) {
        this.qualifyingPosition = position;
    }
}
