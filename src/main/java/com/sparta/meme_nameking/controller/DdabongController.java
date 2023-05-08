package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.DdabongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ddabongController", description = "따봉 API")
@RestController
@RequiredArgsConstructor
public class DdabongController {

    private final DdabongService ddabongService;

    // Post 따봉
    @Operation(summary = "게시글 따봉 API", description = "게시글 따봉")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 따봉 완료")})
    @PostMapping("/posts/ddabongs/{post-id}")
    public ResponseMsgDto postDdabongToFull(@PathVariable(name = "post-id")Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ddabongService.postDdabong(id, userDetails.getUser());
    }

    // Comment 따봉
    @Operation(summary = "댓글 따봉 API", description = "댓글 따봉")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "댓글 따봉 완료")})
    @PostMapping("/comments/ddabongs/{comment-id}")
    public ResponseMsgDto commentDdabongToDetail(@PathVariable(name = "comment-id")Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ddabongService.commentDdabong(id, userDetails.getUser());
    }

}
