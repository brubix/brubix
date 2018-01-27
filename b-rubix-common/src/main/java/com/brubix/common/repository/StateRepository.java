package com.brubix.common.repository;


import com.brubix.entity.reference.State;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StateRepository extends JpaRepository<State, Long> {
    public State findByCode(String code);
}
