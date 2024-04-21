package com.applr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.applr.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	public List<User> findAll();
	public User findByEmail(String email);
	@Query(value = "SELECT * FROM users WHERE first_name LIKE %:query% OR last_name LIKE %:query% OR email LIKE %:query%", nativeQuery = true)
    List<User> searchUser(@Param("query") String query);
}
