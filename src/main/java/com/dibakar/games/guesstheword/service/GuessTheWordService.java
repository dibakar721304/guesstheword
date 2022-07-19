package com.dibakar.games.guesstheword.service;

import com.dibakar.games.guesstheword.model.GuessTheWordGame;

public interface GuessTheWordService {
    /**
     * date:19/07/2022
     * @auther Dibakar
     * This method creates a new game and returns the GuessTheWordGame object
     * @return guessTheWordGame
     */
    GuessTheWordGame createGame();
    /**
     * date:19/07/2022
     * @auther Dibakar
     * This method holds the play logic and returns the GuessTheWordGame object
     * @return guessTheWordGame
     */
    GuessTheWordGame playGame(String letter);

}
