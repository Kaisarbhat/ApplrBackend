package com.applr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.applr.Entity.Reels;
import com.applr.Entity.User;
import com.applr.service.ReelService;
import com.applr.service.UserService;

@RestController
public class ReelsController {
	@Autowired
	private ReelService reelService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReelsHandler(@RequestBody Reels reel,
			@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJwt(jwt);
		Reels createdReels = reelService.createReel(reel, user);
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReelsHandler() {
		List<Reels> reels = reelService.findAllReels();
		return reels;
	}
	@GetMapping("/api/reels/users/{userId}")
	public List<Reels> findReelsByUserHandler(@PathVariable int userId ) throws Exception {
		List<Reels> reels = reelService.findUsersReels(userId);
		return reels;
	}
	
}
