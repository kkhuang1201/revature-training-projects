package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Course;
import com.revature.model.User;
import com.revature.service.CourseService;
import com.revature.service.UserService;

@RestController(value = "appController")
@RequestMapping(path = "/iLearn")
@CrossOrigin(origins = {"http://kennyhuangrevaturebucket.s3-website.us-east-2.amazonaws.com", "http://localhost:4200"})
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping(path = "/login")
	public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpServletRequest request){
		if(this.userService.login(email, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", this.userService.getUserByEmail(email));
			return ResponseEntity.status(HttpStatus.OK).body("Successufully login! ");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect email or password!");
	}
	
	@PostMapping(path = "/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		if(request.getSession(false) != null) {
			request.getSession(false).invalidate();
			return ResponseEntity.status(HttpStatus.OK).body("Successufully logout! ");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user logged in!");
		}
	}
	
	@GetMapping(path = "/allUsers")
	public List<User> getAllUsers(){
		return this.userService.getAllUsers();
	}
	
	@GetMapping(path = "/getUserById")
	public User getUserById(int id) {
		return this.userService.getUserById(id);
	}
	
	//This is the method that sent User object back to client
	//When user request one by user's email
	@GetMapping(path = "/user")
	public User getUserByEmail(@RequestParam String email) {
		return this.userService.getUserByEmail(email);
	}
	
	@GetMapping(path = "/validateLogin")
	public User getUserById(@RequestParam String email, @RequestParam String password) {
		if(this.userService.login(email, password)) {
			return this.userService.getUserByEmail(email);
		}else {
			return null;
		}
		
	}
	
	@PostMapping(path = "/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void addUser(@RequestBody User user) {
		
		if(!(user.getEmail().equals("") || user.getUser_password().equals("") || user.getFirst_name().equals("")||user.getLast_name().equals(""))) {
			if(!this.userService.existsByEmail(user.getEmail())) {
				this.userService.addUser(user);
			}
		}
		
	}
	
	@PostMapping(path = "/updateUser",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void updateUser(@RequestBody User user) {
		User updatedUser = this.userService.getUserById(user.getUserid());
		if(updatedUser != null) {
			this.userService.updateUser(user);
		}
		
	}
	
	@GetMapping(path = "/enrollCourse")
	public void enrollCourse(@RequestParam String userid, @RequestParam String courseid) {
		int tuserid = Integer.parseInt(userid);
		int tcourseid = Integer.parseInt(courseid);
		this.userService.enrollCourse(tuserid, tcourseid);
		
	}
	
	@GetMapping(path = "/unenrollCourse")
	public void unenrollCourse(@RequestParam String userid, @RequestParam String courseid) {
		int tuserid = Integer.parseInt(userid);
		int tcourseid = Integer.parseInt(courseid);
		this.userService.unenrollCourse(tuserid, tcourseid);
		
	}
	
	@GetMapping(path = "/allCourses")
	public List<Course> getAllCourse(){
		return this.courseService.getAllCourses();
	}
	
	@GetMapping(path = "/allCoursesByCreator")
	public List<Course> getAllCoursesByCreator(@RequestParam int userid){
		return this.courseService.getAllByCreator(userid);
	}
	
	@PostMapping(path = "/addCourse")
	public void addCourse(@RequestBody Course course) {
		User user = userService.getUserById(course.getCreator().getUserid());
		course.setCreator(user);
		this.courseService.addCourse(course);
	}
	
	@GetMapping(path = "/my-courses")
	public List<Course> getAllByCreator(@RequestParam String email){
		return this.courseService.getAllByCreator(email);
	}
	
	@GetMapping(path = "/courseid")
	public Course getByCourseid(@RequestParam String id){
		int t = 0;
		try{
			t = Integer.parseInt(id);
		}catch(NumberFormatException e) {
			System.out.println("Invalid Number Input");
		}
		return this.courseService.getCourseById(t);
	}
	
	@PostMapping(path = "/updateCourse")
	public void updateCourse(@RequestBody Course course) {
		this.courseService.updateCourse(course);
	}
	
	@PostMapping(path = "/deleteCourse")
	public String deleteCourse(@RequestBody Course course) {
		if(this.courseService.existsByCourseid(course.getCourseid())) {
			this.courseService.deleteCourse(course);
			return course.getCourseid() + " Successfully deleted this course " + course; 
		}else {
			return  course.getCourseid() + " This course doesn't not exist!!! " + course;
		}
		
	}
}
