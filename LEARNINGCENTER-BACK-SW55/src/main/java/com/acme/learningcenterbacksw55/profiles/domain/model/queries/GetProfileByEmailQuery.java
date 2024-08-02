package com.acme.learningcenterbacksw55.profiles.domain.model.queries;

import com.acme.learningcenterbacksw55.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
