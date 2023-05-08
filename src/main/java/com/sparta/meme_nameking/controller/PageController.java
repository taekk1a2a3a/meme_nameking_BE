package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.security.UserDetailsImpl;
import com.sparta.meme_nameking.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    // 전체 페이지 조회
    @GetMapping("/posts")
    public ResponseMsgDto allPageLoad(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return pageService.allPageLoad(userDetails.getUser());
    }

    // 상세 페이지 조회
    @GetMapping("/posts/{postId}")
    public ResponseMsgDto detailPageLoad(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return pageService.detailPageLoad(postId, userDetails.getUser());
    }


}
