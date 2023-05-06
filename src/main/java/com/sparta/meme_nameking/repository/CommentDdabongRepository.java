package com.sparta.meme_nameking.repository;

import com.sparta.meme_nameking.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentDdabongRepository extends JpaRepository<CommentDdabong, Long> {

    Optional<PostDdabong> findByUserAndComment(User user, Comment comment);
}
