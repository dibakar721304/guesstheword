package com.dibakar.games.guesstheword.util;

public class GuessTheWordConstants {
    public static final String  REQUEST_MAPPING = "/guesstheword";
    public static final String  CREATE_GAME_MAPPING = "/createNewGame";
    public static final String  PLAY_GAME_MAPPING = "/playGame/{guessedLetter}";
    public static final String  GUESSED_LETTER = "guessedLetter";
    public static final String  ERROR_MESSAGE_FOR_FAILED_CREATE_GAME = "Failed to create word guess game, exception={}";
    public static final String  ERROR_MESSAGE_FOR_PLAYING_GAME = "Failed to play word guess game, exception={}";
    public static final String  PATTERN_ALPHABET = "[a-z]";
    public static final String  ASTERIX = "*";

    public static final String  GAME_STARTED_MESSAGE = "Game Started! Guess the word ";
    public static final String  ALREADY_TRIED_LETTER_MESSAGE = "you have already tried this letter";
    public static final String  CORRECT_GUESSED_LETTER_MESSAGE = "Correct. ";

    public static final String  EMPTY_STRING = "";
    public static final String  ERROR_MESSAGE_FOR_PLAYING_INACTIVE_GAME = "There's no active game, please create a game";
    public static final String  INVALID_INPUT_FOR_PLAYING_GAME = "Invalid input for playing game. Please enter single letter";
    public static final String  ERROR_MESSAGE_FOR_CREATING_GAME = "Error while creating a game.";

    public static final String  INCORRECT_GUESSED_LETTER_MESSAGE = "Incorrect input .";
    public static final String  LIFE_LOST_MESSAGE = " life lost. ";
    public static final String  REMAINING_LIFE_MESSAGE = " Remaining life is ";
    public static final String  CURRENT_WORD = " . The current word is ";
    public static final String  GAME_WON_MESSAGE = "You have won the game. The word was ";
    public static final String  GAME_LOST_COMPUTER_WON = "You have lost the game. The computer has won.";

    public static final int MAX_LIVES = 7;
    public static final int ZERO = 0;
}
