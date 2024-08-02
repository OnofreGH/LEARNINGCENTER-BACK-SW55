package com.acme.learningcenterbacksw55.iam.interfaces.rest;

import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetAllRolesQuery;
import com.acme.learningcenterbacksw55.iam.domain.services.RoleQueryService;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.RoleResource;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Role Management Endpoints")
public class RolesController {
    private final RoleQueryService roleQueryService;

    @GetMapping
    public ResponseEntity<List<RoleResource>>getAllRoles(){
        var getAllRolesQuery = new GetAllRolesQuery();
        var roles = roleQueryService.handle(getAllRolesQuery);
        var roleResource = roles.stream().map(
                RoleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(roleResource);
    }
}
