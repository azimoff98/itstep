package az.itstep.azjava.testapp.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class JwtAuthenticationResponse {
    @Getter
    private final String token;

}
