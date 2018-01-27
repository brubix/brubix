package com.brubix.common.repository;


import com.brubix.entity.reference.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sanjeev.singh1 on 14/09/17.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
     Country findByCode(String code);
}
