package com.applr.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.applr.Entity.User;
import com.applr.config.JwtProvider;
import com.applr.exceptions.UserException;
import com.applr.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepo;
	@Override
	public User registerUser(User user) {
		User newUser =  new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		User savedUser = userRepo.save(user);
		return (savedUser);
	}

	@Override
	public User findUserById(int userId) throws UserException  {
		Optional<User>user = userRepo.findById(userId);
		if(user.isEmpty()) {
			throw new UserException("User doesn't exist with userId : "+userId);
		}

		return user.get();
		
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(int reqUserId, int userId2) throws UserException{
		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userId2);
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		
		userRepo.save(reqUser);
		userRepo.save(user2);
		
		return reqUser;
		
	}

	@Override
	public User updateUser(User user,int id) throws UserException{
		 Optional<User> u = userRepo.findById(id);
		if(u.isEmpty()) {
			throw new UserException("User doesn't exixt");
		}
		User oldUser = u.get();
		if(user.getFirstName()!= null)
			oldUser.setFirstName(user.getFirstName());
		if(user.getLastName()!= null)
			oldUser.setLastName(user.getLastName());
		if(user.getEmail()!= null)
			oldUser.setEmail(user.getEmail());
		if(user.getPassword()!= null)
			oldUser.setPassword(user.getPassword());
		
		User updatedUser = userRepo.save(oldUser);
		return updatedUser;
			
	}
	@Override
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	@Override
	public List<User> searchUser(String query) {
 		return userRepo.searchUser(query);
	}

	@Override
	public void deleteUserById(int id) throws UserException {
		Optional<User> user = userRepo.findById(id);
		 if(user.isEmpty()) {
			 throw new UserException("User Doesn't Exist with userId : "+id);
		 }else {
			 userRepo.deleteById(id);			 
		 }
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepo.findByEmail(email);
		return user;
	}

}
