package com.acme.learningcenterbacksw55.iam.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.iam.domain.model.commands.SignInCommand;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource){
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
