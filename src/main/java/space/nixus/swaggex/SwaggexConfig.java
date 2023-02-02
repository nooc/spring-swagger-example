package space.nixus.swaggex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.nixus.swaggex.service.TokenService;
import space.nixus.swaggex.service.BookService;


@Configuration
public class SwaggexConfig {

    @Bean
    TokenService getTokenService() {
        return new TokenService();
    }

    @Bean
    BookService getBookService() {
        return new BookService();
    }
}

