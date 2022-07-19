package com.dibakar.games.guesstheword.controller;

import com.dibakar.games.guesstheword.service.GuessTheWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("/guesstheword")
@Validated
@Slf4j
public class GuessTheWordController {

    @Autowired
    private GuessTheWordService guessTheWordService;

    @PostMapping(value="/createNewGame")
    public ResponseEntity createNewGame() {
        return ResponseEntity.status(HttpStatus.CREATED).body(guessTheWordService.createGame());
    }

    @PostMapping(value="/playGame/{guessedLetter}")
    public ResponseEntity playGame(@PathVariable("guessedLetter") @NotBlank String guessedLetter)  {
         return ResponseEntity.status(HttpStatus.CREATED).body(guessTheWordService.playGame(guessedLetter));
    }
}
