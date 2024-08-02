package com.acme.learningcenterbacksw55.profiles.application.internal.commandservices;

import com.acme.learningcenterbacksw55.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterbacksw55.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learningcenterbacksw55.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learningcenterbacksw55.profiles.domain.services.ProfileCommandService;
import com.acme.learningcenterbacksw55.profiles.domain.services.ProfileQueryService;
import com.acme.learningcenterbacksw55.profiles.infraestructure.persistence.repositories.ProfileRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        });

        var profile = new Profile(command.firstName(), command.LastName(), command.email(), command.street(), command.number(),
                command.city(), command.zipCode(), command.state(), command.country());
        profileRepository.save(profile);
        return profile.getId();
    }
}
