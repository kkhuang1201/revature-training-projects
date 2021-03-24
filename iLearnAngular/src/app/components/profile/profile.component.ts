import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  loggedInUser: User =new User(0,"","","","",[])
  email:String = sessionStorage.getItem('email')

  constructor(private userService: UserService) { }

  ngOnInit(): void {

    this.getloggedInUser(this.email)
    
  }

  update(){
      console.log(this.loggedInUser)
      this.userService.updateProfile(this.loggedInUser).subscribe(
        (data)=>{
          console.log(data)
          alert("Successfully Updated Profile!");
          window.location.reload();
        },
        ()=>{
          console.log('Errorrrrr!')
        }
      )
  }

  getloggedInUser(email:String){
    this.userService.getUser(email).subscribe(
      (data) =>{
        this.loggedInUser = data;
      },
      () => {
        console.log("Something went wrong");
      }
    )
  }

  addActiveContainer(){
    document.getElementById('container').classList.add("right-panel-active");
  }
  
  removeActiveContainer(){
    document.getElementById('container').classList.remove("right-panel-active");
  }

}
