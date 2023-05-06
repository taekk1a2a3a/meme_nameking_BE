package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.*;
import com.sparta.meme_nameking.repository.CommentDdabongRepository;
import com.sparta.meme_nameking.repository.PostDdabongRepository;
import com.sparta.meme_nameking.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    // 게시글 따봉
    public ResponseMsgDto postDdabong(Long id, User user){
        Post post = utils.findPostById(id);
        Optional<PostDdabong> optPostDdabong = postDdabongRepository.findByUserAndPost(user, post);
        if (optPostDdabong.isEmpty()){
            PostDdabong postDdabong = new PostDdabong(user, post);
            postDdabongRepository.save(postDdabong);
            post.incDdabong();
            return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 따봉 !", null);
        } else {
            postDdabongRepository.deleteById(optPostDdabong.get().getId());
            post.decDdabong();
            return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 따봉 취소", null);
        }
    }

    // 댓글 따봉
    public ResponseMsgDto commentDdabong(Long id, User user){
        Comment comment = utils.findCommentById(id);
        Optional<CommentDdabong> optCommentDdabong = commentDdabongRepository.findByUserAndComment(user, comment);
        if (optCommentDdabong.isEmpty()){
            CommentDdabong commentDdabong = new CommentDdabong(user, comment);
            commentDdabongRepository.save(commentDdabong);
            comment.incDdabong();
            return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "댓글 따봉 !", null);
        } else {
            postDdabongRepository.deleteById(optCommentDdabong.get().getId());
            comment.decDdabong();
            return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "댓글 따봉 취소", null);
        }
    }
}
