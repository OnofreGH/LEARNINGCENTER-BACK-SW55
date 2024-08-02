package com.acme.learningcenterbacksw55.profiles.domain.services;

import com.acme.learningcenterbacksw55.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterbacksw55.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.learningcenterbacksw55.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.Optional;

public interface ProfileQueryService {

    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfileByIdQuery query);
}
