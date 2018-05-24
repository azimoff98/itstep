package az.itstep.azjava.testapp.security.service;


import az.itstep.azjava.testapp.dao.MemberDao;
import az.itstep.azjava.testapp.model.Member;
import az.itstep.azjava.testapp.security.exceptions.AuthenticationException;
import az.itstep.azjava.testapp.security.exceptions.TokenNotFoundException;
import az.itstep.azjava.testapp.security.exceptions.TokenRefreshException;
import az.itstep.azjava.testapp.security.model.JwtUser;
import az.itstep.azjava.testapp.security.model.dto.JwtAuthenticationRequest;
import az.itstep.azjava.testapp.security.model.dto.JwtAuthenticationResponse;
import az.itstep.azjava.testapp.security.utils.JwtTokenUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    private MemberDao memberRepository;


    public JwtAuthenticationResponse createAuthenticationToken(JwtAuthenticationRequest authenticationRequest) {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        val token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());

        return new JwtAuthenticationResponse(token);
    }

    public JwtAuthenticationResponse refreshToken(String oldToken) {

        if (Objects.isNull(oldToken) || oldToken.length() < 7)
            throw new TokenNotFoundException("No old token");

        val token = oldToken.substring(7);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String newToken = jwtTokenUtil.refreshToken(token);
            return new JwtAuthenticationResponse(newToken);
        }
        throw new TokenRefreshException("Token can't be refreshed");
    }

    public JwtUser getUserByToken(String authToken) {
        if (Objects.isNull(authToken) || authToken.length() < 7)
            throw new TokenNotFoundException("Can't refresh user");

        val token = authToken.substring(7);

        val username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }

    public Member signUp(Member member) {
        return memberRepository.save(member);
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }


    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    @Qualifier("jwtUserDetailsService")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
