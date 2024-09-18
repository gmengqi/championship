package com.example.football_championship.DTO;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class UpdateTeamDTO {
    private String teamName;
    private String newName=null;
    private LocalDate newRegistrationDate=null;
    private Integer groupNumber=-1;
    public Integer totalGoals=0;
    public Integer matchPoints=0;
    public Integer alternatePoints=0;
    public Integer matchesPlayed=0;

    // Getters and setters
    public String getTeamName() {
        return teamName;
    }
    public String getNewName() {
        return newName;
    }

    public void setTeamName(String teamName) { this.teamName = teamName; }

    public LocalDate getNewRegistrationDate() {
        return newRegistrationDate;
    }

    public int getMatchesPlayed() {
        return this.matchesPlayed;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public Integer getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) { this.totalGoals = totalGoals + this.totalGoals; }

    public Integer getMatchPoints() {
        return matchPoints;
    }
    public void setMatchPoints(int matchPoints) { this.matchPoints = matchPoints + this.matchPoints; }

    public Integer getAlternatePoints() {
        return alternatePoints;
    }

    public void setNewName(String name) {
        this.newName = name;
    }

    public void setGroupNumber(int number) {
        this.groupNumber = number;
    }

    public void setMatchesPlayed(int matches) {
        this.matchesPlayed = matches;
    }

    public void setAlternatePoints(int points) {
        this.alternatePoints = points;
    }
}
