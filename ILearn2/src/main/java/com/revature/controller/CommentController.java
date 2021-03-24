package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Comment;
import com.revature.model.Lesson;
import com.revature.model.User;
import com.revature.service.CommentService;

@RestController("commentController")
@RequestMapping(path = "/comment")
@CrossOrigin(origins = {"http://kennyhuangrevaturebucket.s3-website.us-east-2.amazonaws.com", "http://localhost:4200"})
public class CommentController {
	
	private CommentService commentService;
	
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping(path = "/view-comment-by-id", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Comment findById(@RequestParam int id) {
		return this.commentService.findById(id);
	}
	
	@GetMapping(path = "/view-all-comment", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Comment> findAllComments() {
		return this.commentService.findAllcomments();
	}
	
	@GetMapping(path = "/view-comment-by-author", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public List<Comment> findByAuthor(@RequestBody User author) {
		return this.commentService.findByAuthor(author);
	}
	
	@PostMapping(path = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createComment(@RequestBody Comment comment) {
		this.commentService.createComment(comment);
	}
	
	@PostMapping(path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void updateComment(@RequestBody Comment comment) {
		if(comment.getAuthor()!=null) {
			this.commentService.updateComment(comment);
		}
		
	}
	
	@PostMapping(path = "/like")
	public void likeComment(@RequestParam int commentId) {
		Comment retrievedComment = this.commentService.findById(commentId);
		int retrievedLikeCounter = retrievedComment.getLike_counter();
		retrievedComment.setLike_counter(retrievedLikeCounter + 1);
		this.commentService.updateComment(retrievedComment);
	}
	
	@PostMapping(path = "/dislike")
	public void dislikeComment(@RequestParam int commentId) {
		Comment retrievedComment = this.commentService.findById(commentId);
		int retrievedDislikeCounter = retrievedComment.getDislike_counter();
		retrievedComment.setDislike_counter(retrievedDislikeCounter + 1);
		this.commentService.updateComment(retrievedComment);
	}
	
	@PostMapping(path = "/delete", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteComment(@RequestBody Comment comment) {
		this.commentService.deleteComment(comment);
	}

}