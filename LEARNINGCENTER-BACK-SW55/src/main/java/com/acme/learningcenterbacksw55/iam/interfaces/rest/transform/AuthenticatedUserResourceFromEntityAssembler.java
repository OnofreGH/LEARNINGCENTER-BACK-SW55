package com.acme.learningcenterbacksw55.iam.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.iam.domain.model.aggregates.User;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.AuthenticateUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticateUserResource toResourceFromEntity(User user, String token){
        return new AuthenticateUserResource(user.getId(), user.getUsername(), token);
    }
}
