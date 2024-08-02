package com.acme.learningcenterbacksw55.iam.application.internal.commandservices;

import com.acme.learningcenterbacksw55.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.learningcenterbacksw55.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.learningcenterbacksw55.iam.domain.model.aggregates.User;
import com.acme.learningcenterbacksw55.iam.domain.model.commands.SignInCommand;
import com.acme.learningcenterbacksw55.iam.domain.model.commands.SignUpCommand;
import com.acme.learningcenterbacksw55.iam.domain.services.UserCommandService;
import com.acme.learningcenterbacksw55.iam.infraestructure.persistence.jpa.repositories.RoleRepository;
import com.acme.learningcenterbacksw55.iam.infraestructure.persistence.jpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    private final HashingService hashingService;

    private final TokenService tokenService;

    private final RoleRepository roleRepository;

    /** Handle the sign-up command
    * <p>
    *       this method handles the {@link SignUpCommand} command and return the user
    * </p>
    * @param command the sign-up command containing the username and password
    * @return the created user
    * */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByUsername(command.username())){
            throw new RuntimeException("Username already exists");
        }
        var roles = command.roles().stream().map(
                role1 -> roleRepository.findByName(role1.getName())
                        .orElseThrow(()-> new RuntimeException("Role name not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return Optional.empty();
    }

    /** Handle the sign in command
    * <p>
     *     this method handle the  {@link SignInCommand} command and return the user and the token.
    * </p>
    * @param command the sign in command containing the username and password
    * @return and optional containing the user matching the username and the generated token
    * @throws RuntimeException if the user is not found or the password is invalid
    * */

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        if (!hashingService.matches(command.password(), user.get().getPassword())){
            throw new RuntimeException("Invalid password");
        }
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
