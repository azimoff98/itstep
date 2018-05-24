package az.itstep.azjava.testapp.security.controller;

import az.itstep.azjava.testapp.model.Member;
import az.itstep.azjava.testapp.security.exceptions.AuthenticationException;
import az.itstep.azjava.testapp.security.model.JwtUser;
import az.itstep.azjava.testapp.security.model.dto.JwtAuthenticationRequest;
import az.itstep.azjava.testapp.security.model.dto.JwtAuthenticationResponse;
import az.itstep.azjava.testapp.security.service.AuthenticationService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private AuthenticationService authenticationService;

    @PostMapping("/api/auth")
    public Member signUp(@RequestBody Member member) {
        return authenticationService.signUp(member);
    }

    @PutMapping("/api/auth")
    public JwtAuthenticationResponse signIn(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        return authenticationService.createAuthenticationToken(authenticationRequest);
    }

    @GetMapping("/api/auth")
    public JwtAuthenticationResponse refreshToken(HttpServletRequest request) {
        val authToken = request.getHeader(tokenHeader);
        return authenticationService.refreshToken(authToken);
    }

    @GetMapping("/api/user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        val authToken = request.getHeader(tokenHeader);
        return authenticationService.getUserByToken(authToken);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
