package com.acme.learningcenterbacksw55.iam.infraestructure.authorization.sfs.configuration;

import com.acme.learningcenterbacksw55.iam.infraestructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.acme.learningcenterbacksw55.iam.infraestructure.hashing.bcrypt.BCryptHashingService;
import com.acme.learningcenterbacksw55.iam.infraestructure.token.jwts.BearerTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final BearerTokenService bearerTokenService;
    private final BCryptHashingService bCryptHashingService;
    private final AuthenticationEntryPoint unauthorizedRequestHandler;

    public WebSecurityConfiguration(UserDetailsService userDetailsService, BearerTokenService bearerTokenService,
                                    BCryptHashingService bCryptHashingService, AuthenticationEntryPoint unauthorizedRequestHandler) {
        this.userDetailsService = userDetailsService;
        this.bearerTokenService = bearerTokenService;
        this.bCryptHashingService = bCryptHashingService;
        this.unauthorizedRequestHandler = unauthorizedRequestHandler;
    }

    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(bearerTokenService, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptHashingService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return bCryptHashingService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, BearerAuthorizationRequestFilter bearerAuthorizationRequestFilter) throws Exception {
       http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
               .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                       .requestMatchers(
                               "/api/v1/authentication/**",
                               "/login",
                               "/webjars/**").permitAll()
                       .anyRequest().authenticated());
       http.authenticationProvider(authenticationProvider());
       http.addFilterBefore(bearerAuthorizationRequestFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
    }

}