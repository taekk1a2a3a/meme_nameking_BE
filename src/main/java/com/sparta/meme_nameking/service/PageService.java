package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.AllPageResponseDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.repository.PostRepository;
import com.sparta.meme_nameking.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageService {

    private final Utils utils;
    private final PostRepository postRepository;

    // 전체 페이지 조회
    public ResponseMsgDto allPageLoad(User user){
        // 짤명왕 - TOP
        String ddabongKing = utils.getDdabongKing();
        // List ( Best 댓글, Post, PostDdabong)
        List<AllPageResponseDto.PostList> PostList = new ArrayList<>();

        for (Post post : postRepository.findAll()){
            String bestComment = utils.getBestComment(post);
            AllPageResponseDto.PostList allPage = new AllPageResponseDto.PostList(bestComment, post);
            PostList.add(allPage);
        }

        AllPageResponseDto allPageResponseDto = new AllPageResponseDto(ddabongKing, PostList);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "테스트", allPageResponseDto);
    }
}
