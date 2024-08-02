package com.acme.learningcenterbacksw55.iam.application.internal.commandservices;

import com.acme.learningcenterbacksw55.iam.domain.model.commands.SeedRolesCommand;
import com.acme.learningcenterbacksw55.iam.domain.model.entities.Role;
import com.acme.learningcenterbacksw55.iam.domain.model.valuesobjects.Roles;
import com.acme.learningcenterbacksw55.iam.domain.services.RoleCommandService;
import com.acme.learningcenterbacksw55.iam.infraestructure.persistence.jpa.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@AllArgsConstructor
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    /** this method will handle the {@link SeedRolesCommand} and will create the roles if not exists
     * @param command {@link SeedRolesCommand}
     * @see SeedRolesCommand
     * */

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(
                role -> {
                    if(!roleRepository.existsByName(role)){
                        roleRepository.save(new Role(Roles.valueOf(role.name())));
                    }
                }
        );
    }
}
