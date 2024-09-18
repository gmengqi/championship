package com.example.football_championship.DTO;

import com.example.football_championship.model.Team;

import java.util.List;

public class TeamProcessingResult {
    private List<Team> validTeams;
    private List<String> errors;

    // Getters and setters for serialization
    public List<Team> getValidTeams() {
        return validTeams;
    }

    public void setValidTeams(List<Team> validTeams) {
        this.validTeams = validTeams;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
