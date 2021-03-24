

import { User } from "./user";

export class Course{

    courseid: number;
    title: String;
    creator: User;
    description: String;
    date_created: Date;
    public_access: boolean;
    access_code: String;
    number_enrolled: number;

    constructor(courseid: number,title: String,creator: User,
        description: String,date_created: Date,public_access: boolean,
        access_code: String,number_enrolled: number){

            this.courseid = courseid;
            this.title = title;
            this.creator = creator;
            this.description = description;
            this.date_created = date_created;
            this.public_access = public_access;
            this.access_code = access_code;
            this.number_enrolled = number_enrolled;
    }
    
}