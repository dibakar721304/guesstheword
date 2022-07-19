package com.dibakar.games.guesstheword.service;
import com.dibakar.games.guesstheword.model.GuessTheWordGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GuessTheWordServiceImpl implements GuessTheWordService {

    private static final int MAX_LIVES = 7;
    private GuessTheWordGame guessTheWordGame;
    private String[] wordArray;
    private String[] guessedWordArray;
    private Map<String,Boolean> letterStatusMap;

    @Override
    public GuessTheWordGame createGame() {

        String[] wordList = {"orange", "absolute", "apple","banana"};
        letterStatusMap = new HashMap<>();
        Random randomWord = new Random();
        guessTheWordGame=new GuessTheWordGame(wordList[randomWord.nextInt(wordList.length)],"", 0,letterStatusMap);
        wordArray = guessTheWordGame.getWord().split("");
        guessTheWordGame.setGuessedWord(guessTheWordGame.getWord().replaceAll("[a-z]", "*"));
        guessedWordArray = guessTheWordGame.getGuessedWord().split("");
        guessTheWordGame.setMessage("Game Started! Guess the word "+guessTheWordGame.getGuessedWord());
        log.info(guessTheWordGame.getMessage());
        return guessTheWordGame;

    }

    @Override
    public GuessTheWordGame playGame(String letter)  {
        if(guessTheWordGame.getNumberOfRevealedLetters()== wordArray.length)
        {
            return getWonGame();
        }
        else if(guessTheWordGame.getLetterStatus().containsKey(letter))
            {
                guessTheWordGame.setMessage("you have already tried this letter");
                log.debug(guessTheWordGame.getMessage());
                return guessTheWordGame;
            }
        else if(guessTheWordGame.getWord().contains(letter))
            {
                buildGuessTheWordGame(letter);
                guessTheWordGame.setMessage("Correct. "+ guessTheWordGame.getGuessedWord());
                if(guessTheWordGame.getWord().equals(guessTheWordGame.getGuessedWord()))
                {
                    return getWonGame();
                }
                log.debug(guessTheWordGame.getMessage());
            }

        else{
                guessTheWordGame.incrementNumberOfWrongGuesses();
                guessTheWordGame.setMessage("Incorrect " +guessTheWordGame.getWrongGuesses()+ " life lost. "+(MAX_LIVES-guessTheWordGame.getWrongGuesses())
                      +" remaining. The current word is " +guessTheWordGame.getGuessedWord());
                log.debug(guessTheWordGame.getMessage());
            }

        return guessTheWordGame;
    }


    private GuessTheWordGame getWonGame() {
        guessTheWordGame.setMessage("You have won the game. The word was "+guessTheWordGame.getWord());
        return guessTheWordGame;
    }

    private void buildGuessTheWordGame(String letter) {
        guessTheWordGame.getLetterStatus().put(letter,true);
        for (int i = 0; i <wordArray.length ; i++) {
            if(wordArray[i].equals(letter) )
            {
                guessedWordArray[i]=letter;
                guessTheWordGame.incrementNumberOfRevealedLetters();
            }

           }
        guessTheWordGame.setGuessedWord(Arrays.stream(guessedWordArray)
                .collect(Collectors.joining("")));
    }

  }
