package com.example.football_championship.service;

import com.example.football_championship.model.Match;
import com.example.football_championship.model.Team;
import com.example.football_championship.repository.MatchRepository;
import com.example.football_championship.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FootballService {

    private final MatchRepository matchRepository;

    public FootballService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }


//    public Match addMatch(Match match) {
//        // Update team statistics based on match results
//        Team teamA = match.getTeamA();
//        Team teamB = match.getTeamB();
//
//        // Update match points and total goals logic here
//        teamRepository.save(teamA);
//        teamRepository.save(teamB);
//
//        return matchRepository.save(match);
//    }
}
