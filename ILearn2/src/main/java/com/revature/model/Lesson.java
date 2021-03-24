package com.revature.model;
import javax.persistence.*;


/**
 * 
 * @author kenny Huang
 *
 */

@Entity
@Table(name="lesson")
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int lessonid;
	
	@Column
	private String title;
	
	@JoinColumn(name = "courseid")
	@ManyToOne
	private Course course;
	
	@Column
	private String file_location;

	public Lesson() {
		super();
	}
	

	public Lesson(int lessonId, String title, Course course, String file_location) {
		super();
		this.lessonid = lessonId;
		this.title = title;
		this.course = course;
		this.file_location = file_location;
	}


	//Override the constructor without lessonId parameter since we use serial type in our table
	public Lesson(String title, Course course, String file_location) {
		super();
		this.title = title;
		this.course = course;
		this.file_location = file_location;
	}


	public int getLessonid() {
		return lessonid;
	}


	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public String getFile_location() {
		return file_location;
	}


	public void setFile_location(String file_location) {
		this.file_location = file_location;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((file_location == null) ? 0 : file_location.hashCode());
		result = prime * result + lessonid;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (file_location == null) {
			if (other.file_location != null)
				return false;
		} else if (!file_location.equals(other.file_location))
			return false;
		if (lessonid != other.lessonid)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Lesson [lessonid=" + lessonid + ", title=" + title + ", course=" + course + ", file_location="
				+ file_location + "]";
	}

}
