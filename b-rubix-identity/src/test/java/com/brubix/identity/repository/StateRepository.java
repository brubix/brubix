package com.brubix.identity.repository;


import com.brubix.entity.reference.State;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sanjeev.singh1 on 14/09/17.
 */
public interface StateRepository extends JpaRepository<State, Long> {
    State findByCode(String code);
}
