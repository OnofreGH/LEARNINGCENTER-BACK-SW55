package com.acme.learningcenterbacksw55.iam.domain.services;

import com.acme.learningcenterbacksw55.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {

    void handle(SeedRolesCommand command);
}
