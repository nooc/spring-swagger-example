package space.nixus.swaggex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import space.nixus.swaggex.model.Token;
import space.nixus.swaggex.service.AuthenticationService;

@RestController
public class AuthenticationController {
    
    @Autowired
    AuthenticationService authService;

    @PostMapping("/token/request")
    private Token requestToken(
        @RequestParam("email") String email,
        @RequestParam("password") String password) {
        
        var token = authService.requestToken(email, password);
        if(token==null) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "User not found.");
        }
        return token;
    }

    @PostMapping("/token/validate")
    private String validateToken(
        @RequestParam("token") String token) {
        
        if(!authService.validateToken(token)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Token not found.");
        }
        return "OK";
    }
}
