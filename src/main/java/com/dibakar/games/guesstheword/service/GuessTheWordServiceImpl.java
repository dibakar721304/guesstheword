package com.dibakar.games.guesstheword.service;
import com.dibakar.games.guesstheword.exception.GuessTheWordException;
import com.dibakar.games.guesstheword.model.GuessTheWordGame;
import com.dibakar.games.guesstheword.util.GuessTheWordConstants;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GuessTheWordServiceImpl implements GuessTheWordService {


    private GuessTheWordGame guessTheWordGame;
    private String[] wordArray;
    private String[] guessedWordArray;
    private Map<String,Boolean> letterStatusMap;

    @Override
    public GuessTheWordGame createGame() throws GuessTheWordException{
            String[] wordList = {"orange", "absolute", "apple", "banana"};
            letterStatusMap = new HashMap<>();
            Random randomWord = new Random();
            guessTheWordGame = new GuessTheWordGame(wordList[randomWord.nextInt(wordList.length)], GuessTheWordConstants.EMPTY_STRING, GuessTheWordConstants.ZERO, letterStatusMap);
            if (null==guessTheWordGame) {
                throw new GuessTheWordException(String.format(GuessTheWordConstants.ERROR_MESSAGE_FOR_CREATING_GAME));
            }
            wordArray = guessTheWordGame.getWord().split(GuessTheWordConstants.EMPTY_STRING);
            guessTheWordGame.setGuessedWord(guessTheWordGame.getWord().replaceAll(GuessTheWordConstants.PATTERN_ALPHABET, GuessTheWordConstants.ASTERIX));
            guessedWordArray = guessTheWordGame.getGuessedWord().split(GuessTheWordConstants.EMPTY_STRING);
            guessTheWordGame.setMessage(GuessTheWordConstants.GAME_STARTED_MESSAGE + guessTheWordGame.getGuessedWord());
            log.info(guessTheWordGame.getMessage());
            return guessTheWordGame;
    }

    @Override
    public GuessTheWordGame playGame(String letter) throws GuessTheWordException {
        if (null==guessTheWordGame) {
            throw new GuessTheWordException(String.format(GuessTheWordConstants.ERROR_MESSAGE_FOR_PLAYING_INACTIVE_GAME));
        }
        else if(letter.length()!=1)
        {
            throw new GuessTheWordException(String.format(GuessTheWordConstants.INVALID_INPUT_FOR_PLAYING_GAME));
        }
        else if(guessTheWordGame.getNumberOfRevealedLetters()== wordArray.length)
        {
            return getWonGame();
        }
        else if(guessTheWordGame.getLetterStatus().containsKey(letter))
            {
                guessTheWordGame.setMessage(GuessTheWordConstants.ALREADY_TRIED_LETTER_MESSAGE);
                log.debug(guessTheWordGame.getMessage());
                return guessTheWordGame;
            }
        else if(guessTheWordGame.getWord().contains(letter))
            {
                buildGuessTheWordGame(letter);
                guessTheWordGame.setMessage(GuessTheWordConstants.CORRECT_GUESSED_LETTER_MESSAGE+ guessTheWordGame.getGuessedWord());
                if(guessTheWordGame.getWord().equals(guessTheWordGame.getGuessedWord()))
                {
                    return getWonGame();
                }
                log.debug(guessTheWordGame.getMessage());
            }

        else{
                guessTheWordGame.incrementNumberOfWrongGuesses();
                guessTheWordGame.setMessage(GuessTheWordConstants.INCORRECT_GUESSED_LETTER_MESSAGE +guessTheWordGame.getWrongGuesses()+
                        GuessTheWordConstants.LIFE_LOST_MESSAGE+GuessTheWordConstants.REMAINING_LIFE_MESSAGE+(GuessTheWordConstants.MAX_LIVES -guessTheWordGame.getWrongGuesses())
                      + GuessTheWordConstants.CURRENT_WORD +guessTheWordGame.getGuessedWord());
                if(guessTheWordGame.getWrongGuesses()==GuessTheWordConstants.MAX_LIVES)
                {
                    guessTheWordGame=null;
                    throw new GuessTheWordException(String.format(GuessTheWordConstants.GAME_LOST_COMPUTER_WON));
                }
                log.debug(guessTheWordGame.getMessage());
            }

        return guessTheWordGame;
    }


    private GuessTheWordGame getWonGame() {
        guessTheWordGame.setMessage(GuessTheWordConstants.GAME_WON_MESSAGE+guessTheWordGame.getWord());
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
                .collect(Collectors.joining(GuessTheWordConstants.EMPTY_STRING)));
    }

  }
