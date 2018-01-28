package com.brubix.service.repository.social;

import com.brubix.entity.communication.Social;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 28/01/18.
 */
public interface SocialRepository extends JpaRepository<Social, Long> {

    Social findByFaceBook(String faceBook);

    Social findByTwitter(String twitter);

    Social findByGPlus(String gPlus);

    Social findByLinkedin(String linkedIn);
}