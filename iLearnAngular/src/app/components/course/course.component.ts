import { Component, OnInit } from '@angular/core';
import { CourseServiceService } from 'src/app/services/course-service.service';
import { Course } from 'src/app/models/course'
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';



@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  constructor(private courseService:CourseServiceService, private userService:UserService) { }

  course:Course[] = [];
  email:String = sessionStorage.getItem('email');
  id:String ="";

  user:User = new User(0,"","","","",[]);

  newCourse:Course = new Course(0,"",this.user,"",new Date(),true,'',0);
  getCourse:Course = new Course(0,"",this.user,"",new Date(),true,'',0);
  emptyCourse:Course = new Course(0,"",this.user,"",new Date(),true,'',0);

  myCourses:Course[]

  ngOnInit(): void {
   this.findAllCourseByEmail(this.email);
   this.getloggedInUser(this.email);
   
  }

  getloggedInUser(email:String){
    this.userService.getUser(email).subscribe(
      (data)=>{
        this.user = data;
      },() => {
              console.log("Something went wrong");
        }
    )
  }
 
  findAllCourseByEmail(email:String){
    this.courseService.findAllCourseByEmail(email).subscribe(
    (data)=>{
        this.myCourses = data;
    },
    () =>{
      console.log("Something went wrong");
    }
  )
}

  addCourse(){
    this.newCourse.creator=this.user;
    this.courseService.addCourse(this.newCourse).subscribe(
      (data)=>{
        console.log(data+"course");
        window.location.reload();
        alert("Successfully Added Course!");
      },
      () =>{
        console.log("error in course component")
      }
    )
  }

  findCourseById(){
    this.changeEditFormVisibility();
    this.courseService.findCourseById(this.id).subscribe(
      (data)=>{
        this.getCourse = data;
      },
      () =>{
        console.log("error in course component")
      }
    )
  }

  editCourse(){
    this.courseService.editCourse(this.getCourse).subscribe(
      (data)=>{
        console.log(data); 
        window.location.reload();
        alert("Successfully Edited Course!");
      },
      () =>{
        console.log("error in course component")
      }
    )
  }

  deleteCourse(){
    this.courseService.deleteCourse(this.getCourse).subscribe(
      (data)=>{
        console.log(data);
        window.location.reload();
        alert("Successfully Deleted Course!",);
      },
      () =>{
        console.log("error in course component")
      }
    )
  }

  

  changeCourseVisibility(){
    var doc = document.getElementById("courseSubmit");
    if (doc.style.visibility==='hidden'){
      doc.style.visibility='visible';
    } else{
      doc.style.visibility='hidden';
    }
  }
  changeEditCourseVisibility(){
    var doc = document.getElementById("editCourse");
    if (doc.style.visibility==='hidden'){
      doc.style.visibility='visible';
    } else{
      doc.style.visibility='hidden';
    }
  }
  changeEditFormVisibility(){
    var doc = document.getElementById("editForm");
    if (JSON.stringify(this.getCourse) ===JSON.stringify(this.emptyCourse)){
      console.log("empty course");
      console.log(this.getCourse);
      console.log(this.emptyCourse);
      doc.style.visibility='hidden';
      alert("Enter a valid Course ID");
    }else{
      console.log("filled course");
      doc.style.visibility='visible';
      
    }
  }
}
