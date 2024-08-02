package com.acme.learningcenterbacksw55.profiles.interfaces.acl;

import com.acme.learningcenterbacksw55.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learningcenterbacksw55.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.learningcenterbacksw55.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learningcenterbacksw55.profiles.domain.services.ProfileCommandService;
import com.acme.learningcenterbacksw55.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

/**
 * Service Facade for the Profile context
 *
 * <p>
 *     It is used by other context to interact with the profile context.
 *     It is implemented as part a anticorruption layer(ACL) to be consumed by other contexts
 * </p>
 */

@Service
public class ProfilesContextFacade {

    private final ProfileCommandService profileCommandService;

    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService,
                                 ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Create a new Profile
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param street
     * @param number
     * @param city
     * @param state
     * @param zipCode
     * @return
     */
    public Long createProfile(String firstName, String lastName, String email, String street, String number,
                              String city, String state, String zipCode, String country) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, street, number, city,
                                                            state, zipCode, country);
        return profileCommandService.handle(createProfileCommand);
    }

    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();

    }
}
