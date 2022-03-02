/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.bullscowsapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import raj.bullscowsapi.dto.Round;

/**
 *
 * @author romeroalicia
 */
@Repository
@Profile("database")
public class RoundDatabaseDao implements RoundDao {
    
    private final JdbcTemplate jdbc;

    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Round addRound(Round round, String result) {
        final String sql = "INSERT INTO "
                + "round(guess, timestamp, result, gameId) "
                + "VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        round.setTimestamp(LocalDateTime.now());
        round.setResult(result);
        
        
        jdbc.update((Connection conn) -> {
            PreparedStatement stat = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, round.getGuess());
            stat.setTimestamp(2, Timestamp.valueOf(round.getTimestamp()));
            stat.setString(3, round.getResult());
            stat.setInt(4, round.getGameId());
            
            return stat;
            
        }, keyHolder);
        
        round.setRoundId(keyHolder.getKey().intValue());  
        return round;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        final String sql = "SELECT * FROM round "
                + "WHERE gameId = ? "
                + "ORDER BY timestamp DESC;";
        return jdbc.query(sql, new RoundMapper(), gameId);
    }
    
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGuess(rs.getString("guess"));
            round.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
            round.setResult(rs.getString("result"));
            round.setGameId(rs.getInt("gameId"));
            return round;
        }
        
    } 
}
