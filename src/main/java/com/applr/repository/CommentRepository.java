package com.applr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applr.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

}
