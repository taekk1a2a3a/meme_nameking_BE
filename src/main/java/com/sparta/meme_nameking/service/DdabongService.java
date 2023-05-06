package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.PostDdabong;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.repository.CommentDdabongRepository;
import com.sparta.meme_nameking.repository.PostDdabongRepository;
import com.sparta.meme_nameking.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DdabongService {

    private final Utils utils;
    private final PostDdabongRepository postDdabongRepository;
    private final CommentDdabongRepository commentDdabongRepository;

    // 게시글 좋아요
    public ResponseMsgDto postDdabong(Long id, User user){
        Post post = utils.findPostById(id);
        Optional<PostDdabong> postDdabong = postDdabongRepository.findByUserAndPost(user, post);
        if (postDdabong.isPresent()){

        }
    }
}
