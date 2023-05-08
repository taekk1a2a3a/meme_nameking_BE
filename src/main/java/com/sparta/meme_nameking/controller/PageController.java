package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    // 전체 페이지 PostList 조회
    @GetMapping("/posts")
    public ResponseMsgDto PostList(){
        return pageService.PostList();
    }

    // 전체 페이지
    @GetMapping("/posts/ddabongking")
    public ResponseMsgDto ddabongKing(){
        return pageService.ddabongKing();
    }

}
