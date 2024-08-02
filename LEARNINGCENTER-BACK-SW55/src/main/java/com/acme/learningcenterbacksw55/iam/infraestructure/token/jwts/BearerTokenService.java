package com.acme.learningcenterbacksw55.iam.infraestructure.token.jwts;

import com.acme.learningcenterbacksw55.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {

    String generateToken(Authentication authentication);

    String getBearerTokenFrom(HttpServletRequest token);
}
