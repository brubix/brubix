package com.brubix.identity.repository;

        import com.brubix.entity.inventory.Institution;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
