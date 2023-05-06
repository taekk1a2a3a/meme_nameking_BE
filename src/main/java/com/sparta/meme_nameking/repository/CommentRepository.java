package com.sparta.meme_nameking.repository;

import com.sparta.meme_nameking.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
