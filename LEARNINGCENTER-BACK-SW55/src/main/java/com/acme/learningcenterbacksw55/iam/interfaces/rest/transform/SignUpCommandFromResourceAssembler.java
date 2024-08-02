package com.acme.learningcenterbacksw55.iam.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.iam.domain.model.commands.SignUpCommand;
import com.acme.learningcenterbacksw55.iam.domain.model.entities.Role;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource){
        var roles = resource.roles() != null ? resource.roles().stream().map(
                name -> Role.toRoleFromName(name)).toList(): new ArrayList<Role>();

        return new SignUpCommand(resource.username(), resource.password(), roles);
    }
}
