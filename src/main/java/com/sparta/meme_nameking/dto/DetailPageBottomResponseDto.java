package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class DetailPageBottomResponseDto {
    private final List<CommentResponseDto> comments;

    public DetailPageBottomResponseDto(List<Comment> bestComments, List<Comment> otherComments) {
        this.comments = new ArrayList<>();

        // 베스트 댓글 3개 먼저 목록에 추가
        comments.addAll(bestComments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList()));

        // 일반 댓글 목록에 추가
        comments.addAll(otherComments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList()));
    }
}