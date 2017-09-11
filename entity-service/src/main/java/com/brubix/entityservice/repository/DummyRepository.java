package com.brubix.entityservice.repository;


import com.brubix.entityservice.entity.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<Dummy, Long> {
}
