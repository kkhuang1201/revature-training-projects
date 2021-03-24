package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Course;
import com.revature.model.Lesson;

/**
 * 
 * @author Jason Arias
 *
 */

@Repository(value = "lessonRepository")
public interface LessonRepository extends JpaRepository<Lesson, Integer>{

	Lesson save(Lesson lesson);
	void delete(Lesson lesson);
	Lesson findByLessonid(Integer id);
	List<Lesson> findAll();
	List<Lesson> findByCourse(Course course);
	
}
