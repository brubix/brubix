package com.brubix.entityservice.repository.inventory;

import com.brubix.model.inventory.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
