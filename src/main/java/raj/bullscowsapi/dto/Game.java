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
public class Game {
    private int gameId;
    private LocalDateTime startTime;
    private int numberOfGuesses;
    private String answer;
    private boolean won;

    public int getGameId() {
        return gameId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isWon() {
        return won;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.gameId;
        hash = 79 * hash + Objects.hashCode(this.startTime);
        hash = 79 * hash + this.numberOfGuesses;
        hash = 79 * hash + Objects.hashCode(this.answer);
        hash = 79 * hash + (this.won ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.numberOfGuesses != other.numberOfGuesses) {
            return false;
        }
        if (this.won != other.won) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return Objects.equals(this.startTime, other.startTime);
    }
}
