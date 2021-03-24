/**
 * 
 * @author kenny Huang
 *
 */

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService:UserService, private router: Router) { }

  email:String
  password:String
  newUser:User = new User(0,"","","","",[])
  existedUser: User;
  invalidLogin = false;

  ngOnInit(): void {
  }

  checkLogin(){
    this.userService.authenticate(this.email,this.password).subscribe(
      data => {
        this.existedUser = data
        this.invalidLogin= false;
        this.router.navigate(['home']).then(()=>{
         window.location.reload();
        }
        )
      },
      error => {
        console.log('errrorr!')
        this.invalidLogin = true;
        alert("Wrong Email/Password Combination");
      }

    )
  }

  register(){
    this.userService.register(this.newUser).subscribe(
      (data) =>{
        console.log(data)
        alert("Successfully Registered");
      },
      ()=>{
        console.log('Errorrrrr!')
      }
    )
  }

  login(){
    this.userService.login(this.email,this.password).subscribe(
      (data) =>{
        console.log(data)
        window.location.reload();
      },
      ()=>{
        console.log('Errorrrrr!')
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







