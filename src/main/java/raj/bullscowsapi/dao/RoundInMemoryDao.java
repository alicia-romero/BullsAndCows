/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.bullscowsapi.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import raj.bullscowsapi.dto.Round;

/**
 *
 * @author romeroalicia
 */
@Repository
@Profile("memory")
public class RoundInMemoryDao implements RoundDao {
    
    private static final List<Round> rounds = new ArrayList<>();

    @Override
    public Round addRound(Round round, String result) {
        int nextIndex = rounds.stream()
                .mapToInt(i -> i.getRoundId())
                .max()
                .orElse(0) + 1;
        
        round.setRoundId(nextIndex);
        round.setTimestamp(LocalDateTime.now());
        round.setResult(result);
        rounds.add(round);
        return round;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        List<Round> roundsById = rounds.stream()
                .filter(i -> i.getGameId() == gameId)
                .collect(Collectors.toList());
        
        return roundsById;
    }  
}
