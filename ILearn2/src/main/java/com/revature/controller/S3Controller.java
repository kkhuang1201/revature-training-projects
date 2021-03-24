package com.revature.controller;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.revature.service.S3Service;

@RestController("s3controller")
@RequestMapping(path = "/s3")
@CrossOrigin(origins = {"http://kennyhuangrevaturebucket.s3-website.us-east-2.amazonaws.com", "http://localhost:4200"})
public class S3Controller {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private S3Service s3Service;
	
	@GetMapping("/view")
	public String viewS3() {
		return "bucketName = " + env.getProperty("amazon.bucketName");
	}
	
	@PostMapping(path = "/new", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
		return ResponseEntity.status(HttpStatus.OK).body(this.s3Service.uploadFile(file));
	}
	
	@DeleteMapping("/delete")
	public String deleteFile(@RequestPart(value = "url") String url) {
		return this.s3Service.deleteFile(url);
	}

}
