package az.itstep.azjava.testapp.security.config;

import az.itstep.azjava.testapp.security.controller.JwtUnauthorizedHandleController;
import az.itstep.azjava.testapp.security.filters.JwtAuthorizationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private JwtUnauthorizedHandleController unauthorizedHandler;

    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth").permitAll()
                .anyRequest().authenticated();

        httpSecurity
                .addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter will ignore the below paths
        web.ignoring().antMatchers(
                HttpMethod.PUT,
                "/api/auth"
        );
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/api/auth"
        );

        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/",
                "/webjars/**",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        );

    }


    @Autowired
    public void setJwtAuthorizationTokenFilter(JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter) {
        this.jwtAuthorizationTokenFilter = jwtAuthorizationTokenFilter;
    }

    @Autowired
    public void setUnauthorizedHandler(JwtUnauthorizedHandleController unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }
}
