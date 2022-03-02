/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.bullscowsapi.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import raj.bullscowsapi.dto.Game;
import raj.bullscowsapi.dto.Round;
import raj.bullscowsapi.service.BullsCowsServiceLayer;

/**
 *
 * @author romeroalicia
 */
@RestController
@RequestMapping("/api/bullscows")
public class BullsCowsController {
    
    private final BullsCowsServiceLayer service;
    
    public BullsCowsController(BullsCowsServiceLayer service) {
        this.service = service;
    }
    
    /*
    * starts game -> generates an answer 
    * initiates a Game object, 
    * with zero guesses and won = false
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        String answer = service.generateAnswer();
        Game game = service.addGame(answer);
        return game.getGameId();
    }
    
    /*
    * User makes a guess by passing a guess and a gameId in as JSON
    * calculates result, updates numberOfGuesses
    * if User has won, than updates won = true;
    * returnes a Roobject with results filled in
    */
    @PostMapping("/guess")
    public Round guess(@RequestBody Round round) {
        int gameId = round.getGameId();
        String result = service.calculateResult(round.getGuess(), gameId);
        
        service.updateGame(result, gameId);
 
        return service.addRound(round, result);
    }
    
    /*
    * returns a list of all Games
    * if Game is in progress answer = NULL
    */
    @GetMapping("/game")
    public List<Game> allGames() {
        return service.getAllGames();
    }
    
    /*
    * returns a Game by its gameId
    * if Game is in progress answer = NULL
    */
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> findGameById(@PathVariable int id) {
        Game game = service.findGameById(id);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }
    
    /*
    * returns a list of rounds for a specified game
    * sorted by timestamp - starting from most recent
    */
     @GetMapping("/round/{id}")
    public ResponseEntity<List<Round>> findRoundsById(@PathVariable int id) {
        List<Round> round = service.findRoundsByGameId(id);
        if (round == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(round);
    } 
}
