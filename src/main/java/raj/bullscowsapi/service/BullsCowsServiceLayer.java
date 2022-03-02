/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raj.bullscowsapi.service;

import java.util.List;
import raj.bullscowsapi.dto.Game;
import raj.bullscowsapi.dto.Round;

/**
 *
 * @author romeroalicia
 */
public interface BullsCowsServiceLayer {
    
    /*
    * generates a random sequence of numbers between 0-9 inclusive
    * with length of 4 and no duplicates
    */
    public String generateAnswer();    
    
    /*
    * calculates the result of a guess
    */
    public String calculateResult(String guess, int gameId);
    
    /*
    * updates numberOfGuesses and won, if user has won
    */
    public void updateGame(String result, int gameId);
    
    /*
    * find the answer of a specific game
    */
    public String findAnswerById(int gameId);
    
    
    // methods from DAOs
    public Game addGame(String answer);
    
    public List<Game> getAllGames();
    
    public Game findGameById(int id);
    
    public Round addRound(Round round, String result);
    
    public List<Round> findRoundsByGameId(int gameId);
}
