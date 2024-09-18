package com.example.football_championship.service;

import com.example.football_championship.DTO.CreateMatchDTO;
import com.example.football_championship.DTO.TeamProcessingResult;
import com.example.football_championship.DTO.UpdateTeamDTO;
import com.example.football_championship.model.Match;
import com.example.football_championship.model.Team;
import com.example.football_championship.repository.MatchRepository;
import com.example.football_championship.utils.DateUtils;
import org.hibernate.sql.Update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamService teamService;

    public List<Match> addMatch(List<CreateMatchDTO> matchDTOList) {

        List<Match> validMatch = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        matchDTOList.forEach(dto -> {
            Team teamA = teamService.getTeamDetails(dto.getTeamA());
            Team teamB = teamService.getTeamDetails(dto.getTeamB());

            int teamAGoals = dto.getTeamAScore();
            int teamBGoals = dto.getTeamBScore();

            if (teamAGoals < 0 || teamBGoals < 0) {
                throw new IllegalArgumentException("score must be more than 0");
            }

            int teamAPoints = 0;
            int teamAAltPoints = 0;

            int teamBPoints = 0;
            int teamBAltPoints = 0;


            if (teamAGoals > teamBGoals) {
                // Team A wins
                teamAPoints = 3;
            } else if (teamAGoals < teamBGoals) {
                // Team B wins
                teamBPoints = 3;
            } else {
                // Draw
                teamAPoints = 1;
                teamBPoints = 1;
            }

            if (teamAGoals > teamBGoals) {
                // Team A wins
                teamAAltPoints = 5;
                teamBAltPoints = 1;
            } else if (teamAGoals < teamBGoals) {
                // Team B wins
                teamBAltPoints = 5;
                teamAAltPoints = 1;
            } else {
                // Draw
                teamAAltPoints = 3;
                teamBAltPoints = 3;
            }

            // Save updated teams to the database
            UpdateTeamDTO updateTeamDTOA = new UpdateTeamDTO();
            updateTeamDTOA.setTeamName(teamA.name);
            updateTeamDTOA.matchPoints = teamAPoints;
            updateTeamDTOA.totalGoals = teamAGoals;
            updateTeamDTOA.alternatePoints = teamAAltPoints;
            updateTeamDTOA.matchesPlayed = 1;

            UpdateTeamDTO updateTeamDTOB = new UpdateTeamDTO();
            updateTeamDTOB.setTeamName(teamB.name);
            updateTeamDTOB.matchPoints = teamBPoints;
            updateTeamDTOB.totalGoals = teamBGoals;
            updateTeamDTOB.alternatePoints = teamBAltPoints;
            updateTeamDTOB.matchesPlayed = 1;

            teamService.updateTeamDetails(updateTeamDTOA);
            teamService.updateTeamDetails(updateTeamDTOB);

            Match match = new Match();
            match.setTeamA(dto.getTeamA());
            match.setTeamB(dto.getTeamB());
            match.setTeamBScore(dto.getTeamBScore());
            match.setTeamAScore(dto.getTeamAScore());
            matchRepository.save(match);
            validMatch.add(match);
        });
        return validMatch;
    }
}
