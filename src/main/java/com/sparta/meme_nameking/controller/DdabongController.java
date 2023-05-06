package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.DdabongService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DdabongController {

    private final DdabongService ddabongService;

    // Post 따봉
    @PostMapping("/posts/ddabongs/{post-id}")
    public ResponseMsgDto postDdabongToFull(@PathVariable(name = "post-id")Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ddabongService.postDdabong(id, userDetails.getUser());
    }

    // Comment 따봉
    @PostMapping("/comments/ddabongs/{comment-id}")
    public ResponseMsgDto commentDdabongToDetail(@PathVariable(name = "comment-id")Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ddabongService.commentDdabong(id, userDetails.getUser());
    }

}
