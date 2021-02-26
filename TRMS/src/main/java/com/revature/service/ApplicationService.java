package com.revature.service;

import java.util.List;

import com.revature.model.Application;
import com.revature.repository.ApplicationRepository;
import com.revature.repository.ApplicationRepositoryImpl;

public class ApplicationService {
	
	private static ApplicationRepository applicationRepository;
	
	public ApplicationService() {
		applicationRepository = new ApplicationRepositoryImpl();
	}
	
	
	public void add(Application application) {
		this.applicationRepository.add(application);
	}
	
	public void update(Application application) {
		this.applicationRepository.update(application);
	}
	
	public void delete(Application application) {
		if(application != null) {
			this.applicationRepository.delete(application);
		}
	}
	
	public List<Application> getAllApplications(){
		
		return this.applicationRepository.findAll();
	}
	
	public Application getApplicationById(int id) {
		return this.applicationRepository.getApplication(id);
	}


	
	
	
}
