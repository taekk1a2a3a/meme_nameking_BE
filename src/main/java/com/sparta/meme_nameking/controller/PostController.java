package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.PostRequestDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/post/create")
    public ResponseMsgDto<?> createPost(@RequestPart(required = false, value = "file") MultipartFile multipartFile, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(multipartFile, postRequestDto, userDetails.getUser());
    }

    // 게시글 수정
    @PutMapping("/detail/post/update/{post-id}")
    public ResponseMsgDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(id, postRequestDto, userDetails.getUser());
    }

    // 게시글 삭제
    @DeleteMapping("/detail/post/delete/{post-id}")
    public ResponseMsgDto<?> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getUser());
    }
}
