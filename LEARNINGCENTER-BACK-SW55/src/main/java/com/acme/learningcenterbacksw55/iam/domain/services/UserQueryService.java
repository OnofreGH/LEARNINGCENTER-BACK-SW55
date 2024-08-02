package com.acme.learningcenterbacksw55.iam.domain.services;

import com.acme.learningcenterbacksw55.iam.domain.model.aggregates.User;
import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetAllUserQuery;
import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    List<User> handle(GetAllUserQuery query);

    Optional<User> handle(GetUserByIdQuery query);
}
