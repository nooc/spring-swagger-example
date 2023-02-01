package space.nixus.swaggex.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.NonNull;
import space.nixus.swaggex.model.Token;
import space.nixus.swaggex.model.User;

public class AuthenticationService {

    private static final String GET_USER = "SELECT * FROM User WHERE email = ? and password = ?";
    private static final String CHECK_TOKEN = "SELECT 1 FROM Token WHERE token = ? and expires > ?";
    private static final String CLEAN_TOKENS = "DELETE FORM Token WHERE expires < ?";
    private static final String NEW_TOKENS = "INSERT INTO Token (userId, token, expires) VALUES (?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Request new token.
     * @param email
     * @param password
     * @return token or null
     */
    public Token requestToken(@NonNull String email, @NonNull String password) {
        User user = jdbcTemplate.queryForObject(GET_USER, User.class, email, password);
        if(user != null) {
            var token = UUID.randomUUID().toString();
            var expires = Instant.now().plus(48, ChronoUnit.HOURS).toEpochMilli();
            var result = jdbcTemplate.update(NEW_TOKENS, user.getId(), token, expires);
            if(result==1) {
                return new Token(token, expires);
            }
        }
        return null;
    }

    /**
     * Validate a token.
     * 
     * @param token Token string.
     * @return true or false
     */
    public boolean validateToken(@NonNull String token) {
        return validateToken(jdbcTemplate, token);
    }

    /**
     * Validate a token.
     * 
     * @param token Token string.
     * @return true or false
     */
    public static boolean validateToken(JdbcTemplate jdbc, @NonNull String token) {
        try {
            var result = jdbc.queryForObject(CHECK_TOKEN, AuthenticationService::validateTokenMapper, token);
            return result!=null ? result : false;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Clean expired tokens.
     */
    public void cleanTokens() {
        var now = Instant.now().toEpochMilli();
        jdbcTemplate.update(CLEAN_TOKENS, now);
    }

    /**
     * Just check if row exists.
     * 
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private static Boolean validateTokenMapper(ResultSet rs, int rowNum) throws SQLException {
        return rs.getRow()!=0;
    }
}
