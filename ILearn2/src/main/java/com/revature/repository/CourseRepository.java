package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Course;
import com.revature.model.User;


/**
 * This interface extends JpaRepository, 
 * then we can user the spring JPA method to implement our method for service layer.
 * @author kenny
 *
 */

@Repository(value = "courseRepository")
public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	Course save(Course course);
	
	void delete(Course course);
	
	Course findByCourseid(Integer id);
	
	List<Course> findAll();
	
	boolean existsByCourseid(int id);
	
	List<Course> findByCreator(User user);

}
