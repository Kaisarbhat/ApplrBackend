package com.applr.service;

import java.util.List;
import com.applr.Entity.User;
import com.applr.exceptions.UserException;

public interface UserService {
		public User registerUser(User user);
		public List<User> findAllUsers();
		public User findUserById (int userId) throws UserException;
		public User findUserByEmail(String email);
		public User followUser(int userId1,int userId2)throws UserException;
		public User updateUser(User user,int id) throws UserException;
		public List<User> searchUser(String query);
		public void deleteUserById(int id)throws UserException;
		public User findUserByJwt(String jwt);
}
