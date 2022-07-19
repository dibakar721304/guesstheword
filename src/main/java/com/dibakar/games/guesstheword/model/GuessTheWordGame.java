package com.dibakar.games.guesstheword.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class GuessTheWordGame {
    @JsonIgnore
    private String word;
    @JsonIgnore
    private String guessedWord;
    @JsonIgnore
    private Map<String,Boolean> letterStatus;
    @JsonIgnore
    private int wrongGuesses;
    private String message;
    @JsonIgnore
    private int numberOfRevealedLetters;
    public GuessTheWordGame(String word,String guessedWord,int wrongGuesses,Map<String,Boolean> letterStatus)
    {
        this.word=word;
        this.wrongGuesses=wrongGuesses;
        this.letterStatus=letterStatus;
        this.guessedWord=guessedWord;
    }

    public GuessTheWordGame() {

    }

    public void incrementNumberOfRevealedLetters() {
        setNumberOfRevealedLetters(getNumberOfRevealedLetters() + 1);
    }
    public void incrementNumberOfWrongGuesses() {
        setWrongGuesses(getWrongGuesses() + 1);
    }
}
