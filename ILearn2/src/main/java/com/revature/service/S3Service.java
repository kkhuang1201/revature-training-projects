package com.revature.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class S3Service {
	
	@Value("${amazon.AWSAccessKeyId}")
	private String AWSAccessKeyId;
	
	@Value("${amazon.AWSSecretKey}")
	private String AWSSecretKey;
	
	@Value("${amazon.bucketName}")
	private String bucketName;
	
	private AmazonS3 s3client;
	
	public void viewS3() {
		System.out.println("aws_access_key_id = " + AWSAccessKeyId);
	}
	
	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.AWSAccessKeyId, this.AWSSecretKey);
		this.s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_2)
				.build();
	}
	
	public AmazonS3 getS3Client() {
		return this.s3client;
	}
	
	public File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}
	
	public String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	public void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(new PutObjectRequest(this.bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicReadWrite));
	}
	
	public String uploadFile(MultipartFile multipartFile) {
		String fileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			fileUrl = "https://" + this.bucketName + ".s3.us-east-2.amazonaws.com/" + fileName;
			uploadFileTos3bucket(fileName, file);
			file.delete();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}
	
	//Use this to mark for deletion
	public String deleteFile(String fileUrl) {
	    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
	    System.out.println(fileName);
	    try {
	    	s3client.deleteObject(new DeleteObjectRequest(this.bucketName, fileName));
	    } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process 
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
	    return "Successfully deleted";
	}
	
	//Use this to create a pre-signed url. Though you should prefer the upload file link.
	public URL getFile(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
	    URL retrievedObject = null;
	    try {
	    	retrievedObject = s3client.generatePresignedUrl(this.bucketName, fileName, DateTime.now().plusMinutes(1).toDate());
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return retrievedObject;
	}

}
