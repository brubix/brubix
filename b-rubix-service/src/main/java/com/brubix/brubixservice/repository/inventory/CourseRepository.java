package com.brubix.brubixservice.repository.inventory;

import com.brubix.entity.inventory.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
