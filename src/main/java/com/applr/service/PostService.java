package com.applr.service;

import java.util.List;

import com.applr.Entity.Post;

public interface PostService {
		Post createNewPost(Post post,int userId)throws Exception;
		String deletePost(int postId,int userId)throws Exception;
		List<Post> findPostByuserId(int userId);
		Post findPostById(int postId) throws Exception;
		List<Post> findAllPosts();
		Post savedPost(int postId,int userId) throws Exception;
		Post likedPost(int postId,int userId) throws Exception;
}
