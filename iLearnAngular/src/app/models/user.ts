/**
 * @author kenny Huang
 */

import { Course } from "./course";

export class User{

    userid: number;
    email: String;
    user_password: String;
    first_name: String;
    last_name: String;
    enrolled_courses: Course[];


    constructor(userid: number, email: String, user_password: String, first_name: String,
        last_name:String, enrolled_courses:Course[]){

            this.userid = userid;
            this.email = email;
            this.user_password = user_password;
            this.first_name = first_name;
            this.last_name = last_name;
            this.enrolled_courses = enrolled_courses;
    }
    

}

