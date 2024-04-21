package com.applr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.applr.Entity.Post;
import com.applr.Entity.User;
import com.applr.response.ApiResponse;
import com.applr.service.PostService;
import com.applr.service.UserService;

@RestController
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired 
	private UserService userService;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPostHandler(
			@RequestHeader("Authorization") String jwt,
			@RequestBody Post post) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		Post createdPost = postService.createNewPost(post, reqUser.getId());
		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostHandler(
			@PathVariable int postId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		String message = postService.deletePost(postId, reqUser.getId());
		ApiResponse response = new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable int postId) throws Exception{
		Post post = postService.findPostById(postId);
		return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/api/posts/users/{userId}")
	public ResponseEntity<List<Post>> findUserPostsHandler(@PathVariable int userId){
		List<Post> posts = postService.findPostByuserId(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPostsHandler(){
		List<Post> posts = postService.findAllPosts();
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savePostHandler(
			@PathVariable int postId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		Post post = postService.savedPost(postId, reqUser.getId());
		return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
	}
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(
			@PathVariable int postId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		Post post = postService.likedPost(postId, reqUser.getId());
		return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
	}
	
	
	
}
