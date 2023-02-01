package space.nixus.swaggex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import space.nixus.swaggex.service.AuthenticationService;
import space.nixus.swaggex.service.BookService;


@Configuration
public class SwaggexConfig {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    AuthenticationService getAuthenticationService() {
        return new AuthenticationService();
    }

    @Bean
    BookService getDataService() {
        return new BookService();
    }
}
