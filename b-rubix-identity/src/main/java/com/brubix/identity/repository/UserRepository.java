package com.brubix.identity.repository;

import com.brubix.entity.identity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u JOIN u.phones p WHERE p.number = ?1")
    User findByPhoneNumber(String phone);

    @Query("select u from User u JOIN u.emails e WHERE e.email = ?1")
    User findByEmailId(String email);
}
