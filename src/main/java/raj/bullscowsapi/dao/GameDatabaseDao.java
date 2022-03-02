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
import org.springframework.transaction.annotation.Transactional;
import raj.bullscowsapi.dto.Game;

/**
 *
 * @author romeroalicia
 */
@Repository
@Profile("database")
public class GameDatabaseDao implements GameDao {
    
    private final JdbcTemplate jdbc;

    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Game addGame(String answer) {
        Game game = new Game();
        final String sql = "INSERT INTO "
                + "game(startTime, numberOfGuesses, answer) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        game.setStartTime(LocalDateTime.now());
        game.setNumberOfGuesses(0);
        game.setAnswer(answer);
        
        
        jdbc.update((Connection conn) -> {
            PreparedStatement stat = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);
            stat.setTimestamp(1, Timestamp.valueOf(game.getStartTime()));
            stat.setInt(2, game.getNumberOfGuesses());
            stat.setString(3, game.getAnswer());
            
            return stat;
            
        }, keyHolder);
        
        game.setGameId(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT gameId, "
                + "startTime, "
                + "numberOfGuesses, "
                + "IF(won, answer, NULL) answer, "
                + "won "
                + "FROM game;";
        
        return jdbc.query(sql, new GameMapper());
    }

    @Override
    public Game findGameById(int id) {
        final String sql = "SELECT gameId, "
                + "startTime, "
                + "numberOfGuesses, "
                + "IF(won, answer, NULL) answer, "
                + "won "
                + "FROM game "
                + "WHERE gameId = ?;";
        
        return jdbc.queryForObject(sql, new GameMapper(), id);
    }
    
    @Override
    public Game findGameByIdWithAnswer(int id) {
        final String sql = "SELECT gameId, "
                + "startTime, "
                + "numberOfGuesses, "
                + "answer, "
                + "won "
                + "FROM game "
                + "WHERE gameId = ?;";
        
        return jdbc.queryForObject(sql, new GameMapper(), id);
    }
    
    @Override
    public boolean updateWon(Game game) {        
        final String sql = "UPDATE game SET "
                + "won = ? "
                + "WHERE gameId = ?;";

        return jdbc.update(sql,
                !game.isWon(),
                game.getGameId()) > 0;
    }

    @Override
    public boolean updateNumberOfGuesses(Game game) {
        final String sql = "UPDATE game SET "
                + "numberOfGuesses = ? "
                + "WHERE gameId = ?;";

        game.setNumberOfGuesses(game.getNumberOfGuesses() + 1);
        return jdbc.update(sql,
                game.getNumberOfGuesses(),
                game.getGameId()) > 0;
    }
    
    @Override
    @Transactional
    public void deleteGameById(int id) {
        final String DELETE_ROUNDS = "DELETE FROM round "
                + "WHERE gameId = ?";
        jdbc.update(DELETE_ROUNDS, id);
        
        final String DELETE_GAME = "DELETE FROM game "
                + "WHERE gameId = ?";
        jdbc.update(DELETE_GAME, id);
        
    }
    
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
            game.setNumberOfGuesses(rs.getInt("numberOfGuesses"));
            game.setAnswer(rs.getString("answer"));
            game.setWon(rs.getBoolean("won"));
            return game;
        }
        
    }  
}
