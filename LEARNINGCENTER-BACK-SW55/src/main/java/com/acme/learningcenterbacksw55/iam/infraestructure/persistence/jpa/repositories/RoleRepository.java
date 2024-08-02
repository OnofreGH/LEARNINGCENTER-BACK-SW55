package com.acme.learningcenterbacksw55.iam.infraestructure.persistence.jpa.repositories;

import com.acme.learningcenterbacksw55.iam.domain.model.entities.Role;
import com.acme.learningcenterbacksw55.iam.domain.model.valuesobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);


    boolean existsByName(Roles name);
}
