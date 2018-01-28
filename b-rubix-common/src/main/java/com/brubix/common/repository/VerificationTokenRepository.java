package com.brubix.common.repository;

import com.brubix.entity.identity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 28/01/18.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
