package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Application;
import com.revature.repository.ApplicationRepository;
import com.revature.repository.ApplicationRepositoryImpl;

public class ApplicationService {
	
	private ApplicationRepository applicationRepository;
	
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
	
	
	//Get applicant's all applications by applicant emplId
	public List<Application> getAllByApplicant(int emplId) {
		
		List<Application> applications = this.applicationRepository.findAll();
		applications.removeIf(a -> a.getApplicant().getEmpId() != emplId);
		return applications;
	}
	

	//Get applicant's all applications by applicant'd username
		public List<Application> getAllByApplicant(String username) {
			
			List<Application> applications = this.applicationRepository.findAll();
			applications.removeIf(a -> !(a.getApplicant().getUserName()).equals(username));
			return applications;
		}
		
	
	
	
}
