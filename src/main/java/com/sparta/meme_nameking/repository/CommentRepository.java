package com.sparta.meme_nameking.repository;

import com.sparta.meme_nameking.entity.Comment;
import com.sparta.meme_nameking.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findAllByPost(Post post);

}
