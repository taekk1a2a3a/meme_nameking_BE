package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.PostRequestDto;
import com.sparta.meme_nameking.dto.PostResponseDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.*;
import com.sparta.meme_nameking.exception.CustomException;
import com.sparta.meme_nameking.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.sparta.meme_nameking.exception.ErrorCode.AUTHOR_NOT_SAME_MOD;
import static com.sparta.meme_nameking.exception.ErrorCode.POST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    Post post;

    // 게시글 작성
    @Transactional
    public ResponseMsgDto<?> createPost(MultipartFile multipartFile, PostRequestDto postRequestDto, User user) {

        String img = null;

//        if(!multipartFile.isEmpty()) {
//
//        }

        post = new Post(postRequestDto, user);
        postRepository.save(post);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 작성 완료", new PostResponseDto(post));
    }

    // 게시글 수정
    @Transactional
    public ResponseMsgDto<?> updatePost(Long id, PostRequestDto postRequestDto, User user) {

        post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(POST_NOT_FOUND)
        );
        post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                () -> new CustomException(AUTHOR_NOT_SAME_MOD)
        );
        post.update(postRequestDto);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 수정 완료", new PostResponseDto(post));
    }

    // 게시글 삭제
    @Transactional
    public ResponseMsgDto<?> deletePost(Long id, User user) {
        post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(POST_NOT_FOUND)
        );
        post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                () -> new CustomException(AUTHOR_NOT_SAME_MOD)
        );
        postRepository.deleteById(id);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 삭제 완료", id);
    }

}
