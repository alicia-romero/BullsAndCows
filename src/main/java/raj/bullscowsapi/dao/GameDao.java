/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raj.bullscowsapi.dao;

import java.util.List;
import raj.bullscowsapi.dto.Game;

/**
 *
 * @author romeroalicia
 */
public interface GameDao {
    
    // adds new Game
    public Game addGame(String answer);
    
    // gets all Games
    public List<Game> getAllGames();
    
    // finds Game by gameId
    public Game findGameById(int id);
    
    // finds Game by gameId
    // includes answer even if game is in-progress
    public Game findGameByIdWithAnswer(int id);
    
    // updates won = true
    public boolean updateWon(Game game);
    
    // updates numberOfGuesses - adds one to it
    public boolean updateNumberOfGuesses(Game game);
    
    // deletes Game by gameId
    // used for testing to clear BullsCowsTest database
    public void deleteGameById(int id);
    
}
