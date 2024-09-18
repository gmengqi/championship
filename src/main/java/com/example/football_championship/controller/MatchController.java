package com.example.football_championship.controller;

import com.example.football_championship.model.Match;
import com.example.football_championship.model.Team;
import com.example.football_championship.service.FootballService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/football")
public class FootballController {

    private final FootballService footballService;

    public FootballController(FootballService footballService) {
        this.footballService = footballService;
    }

//    @PostMapping("/match")
//    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
//        return ResponseEntity.ok(footballService.addMatch(match));
//    }
}
