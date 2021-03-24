import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Course } from '../models/course';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CourseServiceService {

  constructor(private httpClient: HttpClient) { }

  findAllCourses(): Observable<Course[]> {
    return this.httpClient.get('http://18.222.184.46:8080/iLearn/allCourses') as Observable<Course[]>
  }
  findAllCourseByEmail(email: String): Observable<Course[]> {
    let params = new HttpParams()
      .set('email', email.valueOf())
    return this.httpClient.get('http://18.222.184.46:8080/iLearn/my-courses', { params: params }) as Observable<Course[]>;

  }
  enrollCourse(userid: String, courseid: String): Observable<String> {
    let params = new HttpParams()
      .set('userid', userid.valueOf())
      .set('courseid', courseid.valueOf());
    return this.httpClient.get('http://18.222.184.46:8080/iLearn/enrollCourse', { params: params }) as Observable<String>
  }

  unenrollCourse(userid: String, courseid: String): Observable<String> {
    let params = new HttpParams()
      .set('userid', userid.valueOf())
      .set('courseid', courseid.valueOf());
    return this.httpClient.get('http://18.222.184.46:8080/iLearn/unenrollCourse', { params: params }) as Observable<String>
  }

  addCourse(course: Course): Observable<Course> {
    return this.httpClient.post<Course>('http://18.222.184.46:8080/iLearn/addCourse', course)
  }

  findCourseById(id: String): Observable<Course> {
    let params = new HttpParams()
      .set('id', id.valueOf())
    return this.httpClient.get('http://18.222.184.46:8080/iLearn/courseid', { params: params }) as Observable<Course>;
  }

  editCourse(course: Course): Observable<Course> {
    return this.httpClient.post<Course>('http://18.222.184.46:8080/iLearn/updateCourse', course)
  }

  deleteCourse(course: Course): Observable<Course> {
    return this.httpClient.post<Course>('http://18.222.184.46:8080/iLearn/deleteCourse', course)
  }

}


