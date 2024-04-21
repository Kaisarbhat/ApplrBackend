package com.applr.service;

import com.applr.Entity.Comment;

public interface CommentService {
	public Comment createComment(Comment comment,
			int postId,int userId) throws Exception;
	public Comment likeComment(int commentId,int userId) throws Exception;
	public Comment findCommentById(int commentId) throws Exception;
	
}
