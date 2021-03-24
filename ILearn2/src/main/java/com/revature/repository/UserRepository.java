package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;


/**
 * This interface extends JpaRepository, 
 * then we can user the spring JPA method to implement our method for service layer.
 * 
 * 
 * @author Kenny Huang
 *
 */

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User save(User user);
	void delete(User user);
	User findByUserid(int id);
	User findByEmail(String email);
	boolean existsByEmail(String email);
	List<User> findAll();

}
