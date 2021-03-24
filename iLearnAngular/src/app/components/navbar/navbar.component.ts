import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username:String

  constructor(private userService: UserService,) { }


  ngOnInit(): void {
    this.username = sessionStorage.getItem('email')
  }

  getIsLoggedIn(){
    return this.userService.isLoggedIn()
  }
  

}
 