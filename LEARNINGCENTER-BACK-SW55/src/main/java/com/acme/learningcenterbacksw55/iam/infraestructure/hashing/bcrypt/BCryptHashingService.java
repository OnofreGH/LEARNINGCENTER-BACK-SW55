package com.acme.learningcenterbacksw55.iam.infraestructure.hashing.bcrypt;

import com.acme.learningcenterbacksw55.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
