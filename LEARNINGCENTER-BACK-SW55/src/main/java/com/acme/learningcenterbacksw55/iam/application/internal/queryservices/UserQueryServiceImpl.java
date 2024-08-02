package com.acme.learningcenterbacksw55.iam.application.internal.queryservices;

import com.acme.learningcenterbacksw55.iam.domain.model.aggregates.User;
import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetAllUserQuery;
import com.acme.learningcenterbacksw55.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.learningcenterbacksw55.iam.domain.services.UserQueryService;
import com.acme.learningcenterbacksw55.iam.infraestructure.persistence.jpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public List<User> handle(GetAllUserQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }
}
