package com.applr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.applr.Entity.Comment;
import com.applr.Entity.User;
import com.applr.service.CommentService;
import com.applr.service.UserService;

@RestController
public class CommentController {
    @Autowired
	private CommentService commentService;
    @Autowired
    private UserService userService;
    
    @PostMapping("/api/comment/post/{postId}")
    public Comment createCommentHandler(@RequestBody Comment comment,
    		@RequestHeader("Authorization") String jwt,
    		@PathVariable int postId) throws Exception {
    	User user = userService.findUserByJwt(jwt);
    	Comment createdComment = commentService.createComment(comment,
    			postId, user.getId());
    	
    	return createdComment;
    }
    @PutMapping("/api/comment/like/{commentId}")
    public Comment likeCommentHandler(
    	    @RequestHeader("Authorization") String jwt,
    		@PathVariable int commentId) throws Exception {
    	User user = userService.findUserByJwt(jwt);
    	Comment likedComment = commentService.likeComment(commentId, user.getId());
    	return likedComment;
    }
    
}
