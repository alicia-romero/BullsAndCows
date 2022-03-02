/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.bullscowsapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import raj.bullscowsapi.dao.GameDao;
import raj.bullscowsapi.dao.RoundDao;
import raj.bullscowsapi.dto.Game;
import raj.bullscowsapi.dto.Round;

/**
 *
 * @author romeroalicia
 */
@Repository
public class BullsCowsServiceLayerImpl implements BullsCowsServiceLayer {
    
    private final GameDao gameDao;
    private final RoundDao roundDao;

    @Autowired
    public BullsCowsServiceLayerImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }   

    @Override
    public String generateAnswer() {
        Random rng = new Random();
        final int LENGTH = 4;
        
        List<String> answerList = new ArrayList<>();
       
        String randomNumber = "";
        for (int i = 0; i < LENGTH; i++) {
            do {
                randomNumber = String.valueOf(rng.nextInt(10));
            } while (answerList.contains(randomNumber));

            answerList.add(randomNumber);
        }
        
        return String.join("", answerList);
    }

    @Override
    public String calculateResult(String guess, int gameId) {
        final int LENGTH = 4;
        String answer = gameDao.findGameByIdWithAnswer(gameId).getAnswer();
        List<String> resultList = new ArrayList<>();
        
        String[] guessArray = guess.split("");
        List<String> guessList = Arrays.asList(guessArray);
        
        String[] answerArray = answer.split("");
        List<String> answerList = Arrays.asList(answerArray);
        
        for (int i = 0; i < LENGTH; i++) {
            if (answerList.get(i).equals(guessList.get(i))) {
                resultList.add("e");
            } else if (answerList.contains(guessList.get(i))) {
                resultList.add("p");
            } else {
                resultList.add("0");
            }
        }
        
        String result = String.join(":", resultList);
        
        return result;
    }
    
    @Override
    public void updateGame(String result, int gameId) {
        final String exactMatch = "e:e:e:e";
        Game game = gameDao.findGameByIdWithAnswer(gameId);
        
        gameDao.updateNumberOfGuesses(game);
    
        if (result.equals(exactMatch)) {
            gameDao.updateWon(game);
        }
    }
    
    
    @Override
    public String findAnswerById(int gameId) {
        return gameDao.findGameByIdWithAnswer(gameId).getAnswer();
    }
    
    @Override
    public Game addGame(String answer) {
        return gameDao.addGame(answer);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public Game findGameById(int id) {
        return gameDao.findGameById(id);
    }
    
    @Override
    public Round addRound(Round round, String result) {
        return roundDao.addRound(round, result);
    }

    @Override
    public List<Round> findRoundsByGameId(int gameId) {
        return roundDao.getRoundsByGameId(gameId);
    }
}
