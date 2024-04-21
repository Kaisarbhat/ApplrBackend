package com.applr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applr.Entity.Reels;
import com.applr.Entity.User;
import com.applr.repository.ReelsRepository;
@Service
public class ReelServiceImpl implements ReelService {
	@Autowired
	private ReelsRepository reelsRepo;
	@Autowired 
	private UserService userService;
	@Override
	public Reels createReel(Reels reel, User user) {
		Reels createReel =  new Reels();
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		
		return reelsRepo.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
			
		return reelsRepo.findAll();
	}

	@Override
	public List<Reels> findUsersReels(int userId) throws Exception {
		userService.findUserById(userId);		
		return reelsRepo.findByUserId(userId);
	}

}
