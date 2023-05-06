package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

//    // 게시글 수정
//    @PutMapping("/posts/{post-id}")
//    public ResponseMsgDto<?> updatePost(@PathVariable(name = "{post-id}") Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return postService.updatePost(id, postRequestDto, userDetails.getUser());
//    }
//
//    // 게시글 삭제
//    @DeleteMapping("/posts/{post-id}")
//    public ResponseMsgDto<?> deletePost(@PathVariable(name = "{post-id}") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return postService.deletePost(id, userDetails.getUser());
//    }
}
