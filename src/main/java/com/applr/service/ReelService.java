package com.applr.service;

import java.util.List;

import com.applr.Entity.Reels;
import com.applr.Entity.User;

public interface ReelService {
	public Reels createReel(Reels reel ,User user);
	public List<Reels> findAllReels();
	public List<Reels> findUsersReels(int userId) throws Exception;
}
