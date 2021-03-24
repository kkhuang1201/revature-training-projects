/**
 * 
 * @author kenny Huang
 *
 */

import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) {}

  user:User;

  authenticate(email:String,password:String):Observable<User>{

    let params =  new HttpParams()
          .set('email', email.valueOf())
          .set('password', password.valueOf());
    return this.httpClient.get<User>('http://18.222.184.46:8080/iLearn/validateLogin',{params:params}).pipe(
      map(
        data => {
          sessionStorage.setItem('email',data.email.valueOf())
          console.log(data)
          return data;
        }
      )
    )
       
  }

  isLoggedIn():boolean{
    let user = sessionStorage.getItem('email')
    return !(user === null)
  }

  getUser(email:String){
    let params = new HttpParams()
        .set('email', email.valueOf())
    return this.httpClient.get('http://18.222.184.46:8080/iLearn/user',{params:params})as Observable<User>;
  }

  logout(){
    sessionStorage.removeItem('email')
  }

  updateProfile(user:User):Observable<User>{

    return this.httpClient.post<User>('http://18.222.184.46:8080/iLearn/updateUser', user)

  }

  register(user:User):Observable<User>{

    return this.httpClient.post<User>('http://18.222.184.46:8080/iLearn/addUser', user)

  }

  login(email:String, password: String):Observable<String> {
    
    return this.httpClient.post<String>('http://18.222.184.46:8080/iLearn/login?email=' + email + '&password=' + password, null)

  }

}
