package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.service.PageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "pageController", description = "전체 페이지 조회 API")
@RestController
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;


    // 전체 페이지 PostList 조회
    @Operation(summary = "전체 페이지 조회 API", description = "전체 페이지 조회")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 성공")})
    @GetMapping("/posts")
    public ResponseMsgDto PostList(){
        return pageService.PostList();

    }
    
      // 전체 페이지
    @GetMapping("/posts/ddabongking")
    public ResponseMsgDto ddabongKing(){
        return pageService.ddabongKing();
    }    
    
    //상세 페이지 상단 조회
    @Operation(summary = "상세 페이지 상단 조회 API", description = "상세 페이지 상단 조회")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 성공")})
    @GetMapping("/posts/{postId}")
    public ResponseMsgDto detailPageTopLoad(@PathVariable Long postId) {
        return pageService.detailPageTopLoad(postId);
    }

    //상세 페이지 하단 조회
    @Operation(summary = "상세 페이지 하단 조회 API", description = "상세 페이지 하단 조회")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 성공")})
    @GetMapping("/posts/{postId}/comments")
    public ResponseMsgDto detailPageBottomLoad(@PathVariable Long postId) {
        return pageService.detailPageBottomLoad(postId);
    }


  

}
