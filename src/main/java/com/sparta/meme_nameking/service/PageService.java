package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.AllPageResponseDto;
import com.sparta.meme_nameking.dto.DetailPageBottomResponseDto;
import com.sparta.meme_nameking.dto.DetailPageTopResponseDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Comment;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.repository.PostRepository;
import com.sparta.meme_nameking.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageService {

    private final Utils utils;
    private final PostRepository postRepository;

    // 전체 페이지 조회
    public ResponseMsgDto allPageLoad(User user) {
        // 짤명왕 - TOP
        String ddabongKing = utils.getDdabongKing();
        // List ( Best 댓글, Post, PostDdabong)
        List<AllPageResponseDto.PostList> postList = new ArrayList<>();

        for (Post post : postRepository.findAll()) {
            String bestComment = utils.getBestComment(post);
            AllPageResponseDto.PostList allPage = new AllPageResponseDto.PostList(bestComment, post);
            postList.add(allPage);
        }

        AllPageResponseDto allPageResponseDto = new AllPageResponseDto(ddabongKing, postList);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "테스트", allPageResponseDto);
    }

    // 상세 페이지 조회
    public ResponseMsgDto detailPageLoad(Long postId, User user) {
        // 게시물 찾기
        Post post = utils.findPostById(postId);

        // 베스트 댓글 3개 추출 (따봉 순)
        List<Comment> bestComments = post.getCommentList().stream()
                .sorted(Comparator.comparing(Comment::getDdabong).reversed())
                .limit(3)
                .collect(Collectors.toList());

        // 베스트 댓글을 제외한 나머지 댓글 정렬 (최신순)
        List<Comment> otherComments = post.getCommentList().stream()
                .filter(comment -> !bestComments.contains(comment))
                .sorted(Comparator.comparing(Comment::getCreatedAt).reversed())
                .collect(Collectors.toList());

        // 상단 응답 DTO 생성
        DetailPageTopResponseDto detailPageTopResponseDto = new DetailPageTopResponseDto(post, bestComments.get(0).getContent());

        // 하단 응답 DTO 생성
        DetailPageBottomResponseDto detailPageBottomResponseDto = new DetailPageBottomResponseDto(bestComments, otherComments);

        // 상세 페이지 조회 성공 메시지와 함께 응답
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "상세 페이지 조회 성공", detailPageTopResponseDto, detailPageBottomResponseDto);
    }





}
