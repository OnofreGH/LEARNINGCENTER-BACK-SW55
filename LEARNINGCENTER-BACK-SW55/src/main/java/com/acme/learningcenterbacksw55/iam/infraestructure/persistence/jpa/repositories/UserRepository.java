package com.acme.learningcenterbacksw55.iam.infraestructure.persistence.jpa.repositories;

import com.acme.learningcenterbacksw55.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


   boolean existsByUsername(String username);
}
