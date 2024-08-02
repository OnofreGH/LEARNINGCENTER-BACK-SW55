package com.acme.learningcenterbacksw55.profiles.domain.services;

import com.acme.learningcenterbacksw55.profiles.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
}
