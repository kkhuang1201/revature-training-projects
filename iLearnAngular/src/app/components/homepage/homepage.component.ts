import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { Lesson } from 'src/app/models/lesson';
import { User } from 'src/app/models/user';
import { CourseServiceService } from 'src/app/services/course-service.service';
import { LessonService } from 'src/app/services/lesson.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private courseService:CourseServiceService, private userService: UserService,private LessonSevice:LessonService) { }

  courses:Course[] = []
  lessons:Lesson[] =[]
  email:String = sessionStorage.getItem('email'); 
  user:User = new User(0,"","","","",[]);
  userid:String="";
  courseid:String=""; 


  ngOnInit(): void {
    
    this.findAllCourses();
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

  enroll(courseid:number){
    console.log(courseid + "is courseid")
    this.courseService.enrollCourse(this.userid.toString(),courseid.toString()).subscribe(
      (data) => {
        console.log(data)
        alert("Successfully Enrolled");
      },
      () => {
        console.log("Errrrorr!")
        alert("Cannot Enroll At This Time");
       
      }
    )
  }

  findAllCourses(){
    this.courseService.findAllCourses().subscribe(
      (data) => {
        this.courses = data
        console.log(data)
      },
      () =>{
        console.log("Errrrrorr!")
      }

    )
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

  isSame(creator:number):boolean{
    if(this.user.userid === creator){
      return true
    }else{
      return false
    }
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
  

}
