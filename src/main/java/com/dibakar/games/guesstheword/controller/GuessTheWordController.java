package com.dibakar.games.guesstheword.controller;

import com.dibakar.games.guesstheword.exception.GuessTheWordException;
import com.dibakar.games.guesstheword.service.GuessTheWordService;
import com.dibakar.games.guesstheword.util.GuessTheWordConstants;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping(GuessTheWordConstants.REQUEST_MAPPING)
@Validated
@Slf4j
@ControllerAdvice
public class GuessTheWordController {

    @Autowired
    private GuessTheWordService guessTheWordService;

    @PostMapping(value=GuessTheWordConstants.CREATE_GAME_MAPPING)
    public ResponseEntity createNewGame() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(guessTheWordService.createGame());
        }
        catch(GuessTheWordException guessTheWordException)
        {
            log.error(GuessTheWordConstants.ERROR_MESSAGE_FOR_FAILED_CREATE_GAME, guessTheWordException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(guessTheWordException.getMessage());
        }
    }

    @PostMapping(value=GuessTheWordConstants.PLAY_GAME_MAPPING)
    public ResponseEntity playGame(@PathVariable(GuessTheWordConstants.GUESSED_LETTER) @NotBlank @NonNull String guessedLetter)  {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(guessTheWordService.playGame(guessedLetter));
           }
             catch(GuessTheWordException guessTheWordException)
            {
                log.error(GuessTheWordConstants.ERROR_MESSAGE_FOR_PLAYING_GAME, guessTheWordException);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(guessTheWordException.getMessage());
            }

    }
}
