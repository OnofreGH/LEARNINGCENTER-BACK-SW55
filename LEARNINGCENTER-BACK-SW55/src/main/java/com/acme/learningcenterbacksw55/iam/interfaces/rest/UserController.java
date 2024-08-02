package com.acme.learningcenterbacksw55.iam.interfaces.rest;

import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetAllUserQuery;
import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.learningcenterbacksw55.iam.domain.services.UserQueryService;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.UserResource;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users Management Endpoints")
public class UserController {

    private final UserQueryService userQueryService;

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUser(){
        var getAllUsersQuery = new GetAllUserQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResource = users.stream().map(
                UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId){
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

}
