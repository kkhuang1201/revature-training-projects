/**
 * @author kenny Huang
 */




import { UrlSerializer } from "@angular/router";
import { User } from "./user";

 export class Comment{
 
     commentid: number
     author: User
     comment: String
     comment_date:Date
     like_counter:number
     dislike_counter:number
 
 
     constructor(commentid: number, user: User, comment:String, comment_date: Date,
         like_counter:number, dislike_counter:number){
 
             this.commentid= this.commentid
             this.author = user
             this.comment = comment
             this.comment_date = comment_date
             this.like_counter = like_counter
             this.dislike_counter = dislike_counter
     }
     
 
 }
 