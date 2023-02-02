package space.nixus.swaggex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.nixus.swaggex.model.Token;
import space.nixus.swaggex.service.TokenService;

@RestController
@RequestMapping("/token")
public class AuthenticationController {
    
    @Autowired
    TokenService tokenService;

    @PostMapping("/request")
    private ResponseEntity<Token> requestToken(
        @RequestParam("email") String email,
        @RequestParam("password") String password) {
        var token = (email!=null && password!=null) ? 
            tokenService.requestToken(email, password) : null;
        return ResponseEntity.status(
            token==null ? HttpStatus.UNAUTHORIZED : HttpStatus.OK
            ).body(token);
    }

    @PostMapping("/validate")
    private ResponseEntity<String> validateToken(
        @RequestParam("token") String token) {
        if(token==null || !tokenService.validateToken(token)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
