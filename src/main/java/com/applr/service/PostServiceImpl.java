package com.applr.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applr.Entity.Post;
import com.applr.Entity.User;
import com.applr.repository.PostRepository;
import com.applr.repository.UserRepository;
@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostRepository postRepo;
	@Autowired  
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	@Override
	public Post createNewPost(Post post, int userId) throws Exception {
		User user = userService.findUserById(userId);
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		newPost.setCreatedAt(LocalDateTime.now());
		return postRepo.save(newPost);
		
	}

	@Override
	public String deletePost(int postId, int userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception ("You can't delete another user's post");
		}
		postRepo.delete(post);
		return "POST DELETED SUCCESSFULLY";
	}

	@Override
	public List<Post> findPostByuserId(int userId) {
		
		return postRepo.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(int postId)throws Exception {
		Optional<Post> opt = postRepo.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("Post not found with postId: "+postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPosts() {
		
		return postRepo.findAll();
	}

	@Override
	public Post savedPost(int postId, int userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		userRepo.save(user);
		return post;
	}

	@Override
	public Post likedPost(int postId, int userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		if(post.getLikedPost().contains(user)) {
			post.getLikedPost().remove(user);
		}else {
			post.getLikedPost().add(user);
			System.out.print("running");			
		}
		
		return postRepo.save(post);
	}

}
