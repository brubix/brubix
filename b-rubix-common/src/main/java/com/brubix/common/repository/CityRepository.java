package com.brubix.common.repository;

import com.brubix.entity.reference.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 26/01/18.
 */
public interface CityRepository extends JpaRepository<City, Long> {
    City findByCode(String code);
}
