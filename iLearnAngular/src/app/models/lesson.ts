import { Course } from "./course";

export class Lesson {
    lessonid:number;
    title:String;
    courseid:number;
    file_location:String

    constructor(lessonid:number, title:String, courseid:number, file_location:String){
        this.lessonid = lessonid;
        this.title = title;
        this.courseid = courseid;
        this.file_location = file_location;
    }
}