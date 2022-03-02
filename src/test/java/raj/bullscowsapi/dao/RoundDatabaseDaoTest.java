/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package raj.bullscowsapi.dao;

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
import raj.bullscowsapi.dto.Round;

/**
 *
 * @author romeroalicia
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDatabaseDaoTest {
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
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
     * Test of addRound and getRoundsByGameId method, of class RoundDatabaseDao.
     */
    @Test
    public void testAddGetRoundsByGameId() {
        String result = "e:e:e:e";
        String guess = "1234";
        Round round = new Round();
        
        Game game = gameDao.addGame(guess);
        int gameId = game.getGameId();
        
        round.setGuess(guess);
        round.setGameId(gameId);
        
        round = roundDao.addRound(round, result);
        
        List<Round> rounds = roundDao.getRoundsByGameId(gameId);
        
        assertEquals(1, rounds.size());

        assertEquals(round.getRoundId(), rounds.get(0).getRoundId());
        
        assertEquals(guess, rounds.get(0).getGuess());
        assertEquals(round.getGuess(), rounds.get(0).getGuess());
        
        assertEquals(result, rounds.get(0).getResult());
        assertEquals(round.getResult(), rounds.get(0).getResult());
        
        assertEquals(gameId, rounds.get(0).getGameId());
        assertEquals(round.getGameId(), rounds.get(0).getGameId());
    }

}
