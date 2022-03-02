/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raj.bullscowsapi.dao;

import java.util.List;
import raj.bullscowsapi.dto.Round;

/**
 *
 * @author romeroalicia
 */
public interface RoundDao {
    
    // adds new Round
    public Round addRound(Round round, String result);
    
    // gets all Rounds by gameId
    public List<Round> getRoundsByGameId(int gameId); 
    
}
