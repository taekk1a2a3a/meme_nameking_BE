package com.sparta.meme_nameking.util;

import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.exception.CustomException;
import com.sparta.meme_nameking.exception.ErrorCode;
import com.sparta.meme_nameking.repository.CommentRepository;
import com.sparta.meme_nameking.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class Utils {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시글 확인
    public Post findPostById(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

}
