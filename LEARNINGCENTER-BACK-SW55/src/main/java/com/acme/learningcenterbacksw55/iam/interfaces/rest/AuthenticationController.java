package com.acme.learningcenterbacksw55.iam.interfaces.rest;

import com.acme.learningcenterbacksw55.iam.domain.services.UserCommandService;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.AuthenticateUserResource;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.SignInResource;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.SignUpResource;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.resources.UserResource;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.acme.learningcenterbacksw55.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class AuthenticationController {

    private final UserCommandService userCommandService;

    @PostMapping("sign-in")
    public ResponseEntity<AuthenticateUserResource> signIn(@RequestBody SignInResource signInResource){
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if(authenticatedUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler
                .toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource){
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
