import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../models/comment';


@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient) {}

  makeComment(comment:Comment):Observable<Comment>{

    return this.httpClient.post<Comment>('http://18.222.184.46:8080/comment/new', comment)

  }

  getAllComments():Observable<Comment[]>{
    return this.httpClient.get('http://18.222.184.46:8080/comment/view-all-comment') as Observable<Comment[]>
  }

}
