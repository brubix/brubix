package com.brubix.identity.repository;

        import com.brubix.entity.inventory.School;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
