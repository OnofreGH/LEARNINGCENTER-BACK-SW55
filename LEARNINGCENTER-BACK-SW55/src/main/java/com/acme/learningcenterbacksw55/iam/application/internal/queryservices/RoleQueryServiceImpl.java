package com.acme.learningcenterbacksw55.iam.application.internal.queryservices;

import com.acme.learningcenterbacksw55.iam.domain.model.entities.Role;
import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetAllRolesQuery;
import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetRoleByNameQuery;
import com.acme.learningcenterbacksw55.iam.domain.services.RoleQueryService;
import com.acme.learningcenterbacksw55.iam.infraestructure.persistence.jpa.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
