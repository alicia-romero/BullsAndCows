/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package raj.bullscowsapi.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import raj.bullscowsapi.TestApplicationConfiguration;
import raj.bullscowsapi.dto.Game;

/**
 *
 * @author romeroalicia
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDatabaseDaoTest {
    
    @Autowired
    GameDao gameDao;
    
    /*
    * cleanes up the database before each test 
    * deletes all Games and Rounds (because of Foregin Key gameId
    */
    @Before
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }

    /**
     * Test of addGame and getAllGames method, of class GameDatabaseDao.
     */
    @Test
    public void testAddGetAllGames() {
        Game game = gameDao.addGame("1234");
        
        List<Game> games = gameDao.getAllGames();
        
        assertEquals(1, games.size());
        assertEquals(game.getGameId(), games.get(0).getGameId());
        assertEquals(game.getNumberOfGuesses(), games.get(0).getNumberOfGuesses());
        assertNull(games.get(0).getAnswer()); // shold be null since .getAllItems() returns null for answer, if won=false
        assertEquals(game.isWon(), games.get(0).isWon());
    }


    /**
     * Test of findGameById method, of class GameDatabaseDao.
     */
    @Test
    public void testFindGameById() {
        Game game = gameDao.addGame("1234");
        
        Game gameReturned = gameDao.findGameById(game.getGameId());
        
        assertEquals(game.getGameId(), gameReturned.getGameId());
        assertEquals(game.getNumberOfGuesses(), gameReturned.getNumberOfGuesses());
        assertNull(gameReturned.getAnswer()); 
        assertEquals(game.isWon(), gameReturned.isWon());
    }

    /**
     * Test of findGameByIdWithAnswer method, of class GameDatabaseDao.
     */
    @Test
    public void testFindGameByIdWithAnswer() {
        Game game = gameDao.addGame("1234");
        
        Game gameReturned = gameDao.findGameByIdWithAnswer(game.getGameId());
        
        assertEquals(game.getGameId(), gameReturned.getGameId());
        assertEquals(game.getNumberOfGuesses(), gameReturned.getNumberOfGuesses());
        assertEquals(game.getAnswer(), gameReturned.getAnswer()); 
        assertEquals(game.isWon(), gameReturned.isWon());
    }

    /**
     * Test of updateWon method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdateWon() {
        Game game = gameDao.addGame("1234");
        
        Game gameReturned = gameDao.findGameById(game.getGameId());
        
        assertNull(gameReturned.getAnswer()); 
        assertFalse(gameReturned.isWon());
        
        gameDao.updateWon(game);
        gameReturned = gameDao.findGameById(game.getGameId());
        
        assertNotNull(gameReturned.getAnswer()); 
        assertTrue(gameReturned.isWon());
    }

    /**
     * Test of updateNumberOfGuesses method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdateNumberOfGuesses() {
        Game game = gameDao.addGame("1234");
        
        assertEquals(0, game.getNumberOfGuesses());
        
        gameDao.updateNumberOfGuesses(game);
        
        assertEquals(1, game.getNumberOfGuesses());
    }
    
}
