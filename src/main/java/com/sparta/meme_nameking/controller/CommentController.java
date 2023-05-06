package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.CommentRequestDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Comment;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{post-id}")
    public ResponseMsgDto createComment(@PathVariable(name = "post-id") Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id,requestDto, userDetails.getUser());
    }
    @PutMapping("/{comment-id}")
    public ResponseMsgDto updateComment(@PathVariable(name = "comment-id") Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id, requestDto, userDetails.getUser());
    }
    @DeleteMapping("/{comment-id}")
    public ResponseMsgDto deleteComment(@PathVariable(name = "comment-id") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails.getUser());
    }



}
