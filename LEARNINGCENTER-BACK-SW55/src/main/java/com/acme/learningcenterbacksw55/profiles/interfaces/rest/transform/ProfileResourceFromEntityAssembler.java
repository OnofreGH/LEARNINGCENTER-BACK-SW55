package com.acme.learningcenterbacksw55.profiles.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterbacksw55.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {

    public static ProfileResource toResourceFromEntity(Profile profile) {
        return new ProfileResource(profile.getId(), profile.getFullName(), profile.getEmailAddress(),
                profile.getStreetAddress());
    }
}
