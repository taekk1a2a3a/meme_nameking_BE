package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "postController", description = "게시글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @Operation(summary = "게시글 작성 API", description = "게시글 작성")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 작성 완료")})
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsgDto<?> createPost(@RequestParam(value = "image") MultipartFile image, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.createPost(image, userDetails.getUser());
    }

    // 게시글 수정
    @Operation(summary = "게시글 수정 API", description = "게시글 수정")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 수정 완료")})
    @PutMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsgDto<?> updatePost(@PathVariable(name = "postId") Long id, @RequestParam(value = "image") MultipartFile image, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.updatePost(id, image, userDetails.getUser());
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제 API", description = "게시글 삭제 후 삭제된 아이디 반환")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 삭제 완료")})
    @DeleteMapping("/{postId}")
    public ResponseMsgDto<?> deletePost(@PathVariable(name = "postId") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getUser());
    }
}
