package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.AllPageResponseDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Post;
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


    // 전체 페이지 짤명왕 조회
    public ResponseMsgDto ddabongKing(){

        String ddabongKing = utils.getDdabongKing();
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "현재 따봉킹", ddabongKing);

    }
    // 전체 페이지 조회
    public ResponseMsgDto PostList(){

        // List ( Best 댓글, Post, PostDdabong)
        List<AllPageResponseDto> allPageResponseDtoList = new ArrayList<>();

        // 현재 Post 모두 가져오기
        for (Post post : postRepository.findAll()){
            String bestComment = utils.getBestComment(post);
            AllPageResponseDto allPage = new AllPageResponseDto(post, bestComment);
            allPageResponseDtoList.add(allPage);
        }

        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "테스트", allPageResponseDtoList);
    }
}
