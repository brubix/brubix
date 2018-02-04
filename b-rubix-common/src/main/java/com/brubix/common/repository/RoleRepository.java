package com.brubix.common.repository;

import com.brubix.entity.identity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByNameIn(List<String> roles);

    Role findByName(String role);
}
