package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public ResponseMsgDto<?> createPost(@RequestParam(value = "image") MultipartFile image, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.createPost(image, userDetails.getUser());
    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseMsgDto<?> updatePost(@PathVariable Long id, @RequestParam(value = "image") MultipartFile image, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.updatePost(id, image, userDetails.getUser());
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseMsgDto<?> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getUser());
    }
}
