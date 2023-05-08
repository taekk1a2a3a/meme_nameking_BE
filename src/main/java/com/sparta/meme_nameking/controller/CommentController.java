package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.CommentRequestDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "commentController", description = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 작성 API", description = "댓글 작성")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "댓글 작성 완료")})
    @PostMapping("/")
    public ResponseMsgDto createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(requestDto, userDetails.getUser());
    }

    @Operation(summary = "댓글 수정 API", description = "댓글 수정")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "댓글 수정 완료")})
    @PutMapping("/{commentId}")
    public ResponseMsgDto updateComment(@PathVariable(name = "commentId") Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id, requestDto, userDetails.getUser());
    }

    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "댓글 삭제 완료")})
    @DeleteMapping("/{commentId}")
    public ResponseMsgDto deleteComment(@PathVariable(name = "commentId") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails.getUser());
    }
}
