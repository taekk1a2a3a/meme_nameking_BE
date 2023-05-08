package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.CommentRequestDto;
import com.sparta.meme_nameking.dto.CommentResponseDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Comment;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.entity.UserRoleEnum;
import com.sparta.meme_nameking.repository.CommentRepository;
import com.sparta.meme_nameking.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final Utils utils;

    public ResponseMsgDto createComment(Long id, CommentRequestDto requestDto, User user) {
        Post post = utils.findPostById(id);
        Comment comment = new Comment(requestDto, user, post);
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "댓글 작성 완료", commentResponseDto);
    }

    public ResponseMsgDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = utils.findCommentById(id);
        if (user.getRole().equals(UserRoleEnum.USER)) {
            utils.isUserComment(user, comment);
        }
        comment.update(requestDto, user);
        CommentResponseDto commentResponseDto = new CommentResponseDto((comment));
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "댓글 수정 완료", commentResponseDto);
    }

    public ResponseMsgDto deleteComment(Long id, User user) {
        Comment comment = utils.findCommentById(id);
        if (user.getRole().equals(UserRoleEnum.USER)) {
            utils.isUserComment(user, comment);
        }
        commentRepository.delete(comment);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "댓글 삭제 완료", null);
    }
}
