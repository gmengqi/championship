package com.example.football_championship.service;

import com.example.football_championship.DTO.CreateTeamDTO;
import com.example.football_championship.DTO.TeamProcessingResult;
import com.example.football_championship.DTO.UpdateTeamDTO;
import com.example.football_championship.comparator.TeamRankingComparator;
import com.example.football_championship.model.Team;
import com.example.football_championship.repository.TeamRepository;
import com.example.football_championship.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;


    public TeamProcessingResult addTeams(List<CreateTeamDTO> teamDTOs) {
        List<Team> validTeams = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        teamDTOs.forEach(dto -> {
            try {
                // Validate date and create the team object
                LocalDate date = DateUtils.validateDate(dto.getRegistrationDate());
                Team team = new Team();
                team.setName(dto.getName());
                team.setRegistrationDate(date);
                team.setGroupNumber(dto.getGroupNumber());

                // Add to the list of valid teams
                validTeams.add(team);

            } catch (IllegalArgumentException e) {
                // Log the error or collect the error message for later reporting
                errorMessages.add("Error processing team: " + dto.getName() + " - " + e.getMessage());
            }
        });

        TeamProcessingResult result = new TeamProcessingResult();
        teamRepository.saveAll(validTeams);
        result.setValidTeams(validTeams);
        result.setErrors(errorMessages);
        return result;
    }

    public Team getTeamDetails(String name) {
        System.out.println("retrieving info for team: " + name);
        Optional<Team> team = teamRepository.findByName(name);

        if (team.isEmpty()) {
            throw new NoSuchElementException(name + " do not exists");
        }

        return team.get();
    }

    public boolean deleteTeamByName(String name) {
        Optional<Team> team = teamRepository.findByName(name);

        if (team.isPresent()) {
            teamRepository.deleteByName(name);
            return true;
        } else {
            throw new NoSuchElementException("Team do not exists");
        }
    }

    public Team updateTeamDetails(UpdateTeamDTO updateTeamDTO) {
        Optional<Team> team = teamRepository.findByName(updateTeamDTO.getTeamName());

        if (team.isPresent()) {
            Team existingTeam = team.get();
            try {
                // Update the team details with the new values
                existingTeam.setName(updateTeamDTO.getNewName() != null ? updateTeamDTO.getNewName() : existingTeam.getName());
                existingTeam.setRegistrationDate(updateTeamDTO.getNewRegistrationDate() != null ? updateTeamDTO.getNewRegistrationDate() : existingTeam.getRegistrationDate());
                existingTeam.setGroupNumber(updateTeamDTO.getGroupNumber() != -1 ? updateTeamDTO.getGroupNumber() : existingTeam.getGroupNumber());
                existingTeam.setTotalGoals(updateTeamDTO.getTotalGoals() + existingTeam.totalGoals);
                existingTeam.setMatchPoints(updateTeamDTO.getMatchPoints() + existingTeam.matchPoints);
                existingTeam.setAlternatePoints(updateTeamDTO.getAlternatePoints() + existingTeam.alternatePoints);
                existingTeam.setMatchesPlayed(updateTeamDTO.getMatchesPlayed() + existingTeam.matchesPlayed);
                // Save the updated team back to the database
                return teamRepository.save(existingTeam);
            } catch (Exception e) {
                throw new RuntimeException("Team not found with name: " + existingTeam.name);

            }
        } else {
            throw new NoSuchElementException("The team does not exists");
        }
    }

    public List<Team> getRankingsByGroup(int groupNumber) {
        List<Team> teams = teamRepository.findByGroupNumber(groupNumber).orElseThrow(
                () -> new NoSuchElementException("Such group number does not exist"));

        if (teams.isEmpty()) {
            throw new NoSuchElementException("Such group number does not exist");
        } else {
            Collections.sort(teams, new TeamRankingComparator());
            return teams;
        }
    }

    public boolean getOutcomeForTeam(String teamName, int groupNumber) {
        List<Team> teams = getRankingsByGroup(groupNumber);

        for (int i = 0; i < teams.size(); i++) {
            Team currTeam = teams.get(i);
            if (currTeam.name.equals(teamName) && i < 4) {
                return true;
            }
        }
        return false;
    }
}
