package com.applr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applr.Entity.Reels;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {
		public List<Reels> findByUserId(int userId);
}
