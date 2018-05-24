package az.itstep.azjava.testapp.security.service;

import az.itstep.azjava.testapp.security.model.JwtUser;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthManager implements AuthenticationManager {

    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        val username = authentication.getPrincipal().toString();
        val password = authentication.getCredentials().toString();

        JwtUser user = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
        if (Objects.isNull(user))
            throw new BadCredentialsException("User not found");

        if (!Objects.equals(password, user.getPassword()))
            throw new BadCredentialsException("Wrong password");


        if (!user.isEnabled())
            throw new DisabledException("User is disabled");

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Autowired
    public void setJwtUserDetailsService(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }
}
