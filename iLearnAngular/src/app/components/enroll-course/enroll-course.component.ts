import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { CourseServiceService } from 'src/app/services/course-service.service';
import { Course } from 'src/app/models/course';
import { Lesson } from 'src/app/models/lesson';
import { LessonService } from 'src/app/services/lesson.service';
@Component({
  selector: 'app-enroll-course',
  templateUrl: './enroll-course.component.html',
  styleUrls: ['./enroll-course.component.css']
})
export class EnrollCourseComponent implements OnInit {

  constructor(private courseService:CourseServiceService, private userService:UserService,private LessonSevice:LessonService) { }

  courses:Course[] = []
  lessons:Lesson[] =[]
  email:String = sessionStorage.getItem('email'); 
  user:User = new User(0,"","","","",[]);
  userid:String = "";
  courseid:String="";

  ngOnInit(): void {
    this.getloggedInUser(this.email);
  }

  getloggedInUser(email:String){
    this.userService.getUser(email).subscribe(
      (data)=>{
        this.user = data;
        this.userid =data.userid.toString();
        console.log(this.user);
        console.log(this.userid+ "is string");
      },() => {
              console.log("Something went wrong");
        }
    )
  }

  
 

  viewContent(id:String){
    var courseid:number = +id;
    this.findAllLessons(courseid)
     var myid:string = "form-container" +id
     var doc = document.getElementById(myid);
     if (doc.style.display==='none'){
       doc.style.display='block';
     } else{
       doc.style.display='none';
     }
  }
  
  findAllLessons(courseid:number){
    this.LessonSevice.findAllLessonsByCourseid(courseid.toString()).subscribe(
      (data) => {
        this.lessons = data
        console.log(data)
      },
      () =>{
        console.log("Errrrorrr!")
      }

    )

  }


  unenroll(courseid:number){
    console.log(this.userid +" enroll id");
    console.log(this.courseid + " enroll coureseid");
    this.courseService.unenrollCourse(this.userid.toString(),courseid.toString()).subscribe(
      (data)=>{
          console.log(data);
          console.log(this.userid+" success");
          window.location.reload();
      },
      () =>{
        console.log("problem in unenroll-course component");
        window.location.reload();
      }
    )
  }
}
