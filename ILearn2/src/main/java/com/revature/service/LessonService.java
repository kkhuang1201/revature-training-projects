package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Course;
import com.revature.model.Lesson;
import com.revature.model.User;
import com.revature.repository.LessonRepository;

@Service(value = "lessonService")
public class LessonService {
	
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private CourseService courseService;
	
	public void addLesson(Lesson lesson) {
		this.lessonRepository.save(lesson);
	}
	
	public void updateLesson(Lesson lesson) {
		this.lessonRepository.save(lesson);
	}
	
	public void deleteLesson(Lesson lesson) {
		this.lessonRepository.delete(lesson);
	}

	public Lesson getLessonById(Integer id) {
		return this.lessonRepository.findByLessonid(id);
	}
	
	public List<Lesson> getAllLessons(){
		return this.lessonRepository.findAll();
	}
	
	public List<Lesson> getLessonsByCourse(Course course) {
		return this.lessonRepository.findByCourse(course);
	}
	
	//Use couseid as parameter to find all lessons of a course
	public List<Lesson> getAllByCourse(int courseid){
		Course course = this.courseService.getCourseById(courseid);		
		if(course!=null) {
			return this.lessonRepository.findByCourse(course);
		} else {
			return null;
		}
	}
}
