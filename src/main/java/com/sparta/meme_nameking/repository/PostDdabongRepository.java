package com.sparta.meme_nameking.repository;

import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.PostDdabong;
import com.sparta.meme_nameking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostDdabongRepository extends JpaRepository<PostDdabong, Long> {

    Optional<PostDdabong> findByUserAndPost(User user, Post post);
}
