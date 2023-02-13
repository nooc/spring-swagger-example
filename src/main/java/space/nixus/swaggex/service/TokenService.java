package space.nixus.swaggex.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import space.nixus.swaggex.model.Token;
import space.nixus.swaggex.model.User;
import space.nixus.swaggex.repository.TokenRepository;
import space.nixus.swaggex.repository.UserRepository;


@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepo;
    
    @Autowired
    private UserRepository userRepo;

    /**
     * Request new token.
     * @param email
     * @param password
     * @return token or null
     */
    public Token requestToken(@NonNull String email, @NonNull String password) {
        User user = userRepo.getUserByCredentials(email, password);
        if(user != null) {
            var token = UUID.randomUUID().toString();
            Long expires = Instant.now().plus(48, ChronoUnit.HOURS).toEpochMilli();
            return tokenRepo.save(new Token(null, user.getId(), token, expires));
        }
        return null;
    }

    /**
     * Validate a token.
     * 
     * @param value Token string.
     * @return true or false
     */
    public boolean validateToken(@NonNull String value) {
        var now = Instant.now().toEpochMilli();
        var token = tokenRepo.findByValue(value);
        return token != null && token.getExpires() > now;
    }

    /**
     * Clean expired tokens.
     */
    public void cleanTokens() {
        var now = Instant.now().toEpochMilli();
        tokenRepo.deleteByExpiresLessThan(now);
    }
}
