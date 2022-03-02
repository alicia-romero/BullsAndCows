/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.bullscowsapi.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author romeroalicia
 */

public class Round {
    
    private int roundId;
    private String guess;
    private LocalDateTime timestamp;
    private String result;
    private int gameId;
    
    public Round() {
    }

    public Round(String guess, int gameId) {
        this.guess = guess;
        this.gameId = gameId;
    }

    public int getRoundId() {
        return roundId;
    }
    
    public String getGuess() {
        return guess;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getResult() {
        return result;
    }
    
    public int getGameId() {
        return gameId;
    }
    
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.roundId;
        hash = 17 * hash + Objects.hashCode(this.guess);
        hash = 17 * hash + Objects.hashCode(this.timestamp);
        hash = 17 * hash + Objects.hashCode(this.result);
        hash = 17 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return Objects.equals(this.timestamp, other.timestamp);
    }
}
