package com.sparta.meme_nameking.util;

import com.sparta.meme_nameking.entity.Comment;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.exception.CustomException;
import com.sparta.meme_nameking.exception.ErrorCode;
import com.sparta.meme_nameking.repository.CommentRepository;
import com.sparta.meme_nameking.repository.PostRepository;
import com.sparta.meme_nameking.repository.QuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class Utils {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final QuerydslRepository querydslRepository;

    // 게시글 찾기
    public Post findPostById(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    // 댓글 찾기
    public Comment findCommentById(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }
    public void isUserComment(User user, Comment comment){
        if(!comment.getUser().getId().equals(user.getId())){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    // 작성자 찾기
    public Post findAuthorById(Long id, Long userId){
        return postRepository.findByIdAndUser_id(id, userId).orElseThrow(
                () -> new CustomException(ErrorCode.AUTHOR_NOT_SAME_MOD));
    }

    // 베스트 댓글 찾기
    public String getBestComment(Post post) {
        Optional<Comment> optBestComment = querydslRepository.findBestCommentByPost(post);
        return optBestComment.map(Comment::getContent).orElse("아직 베스트 댓글이 없습니다.");
    }

    // 짤명왕(따봉킹) 찾기
    public String getDdabongKing() {
        Optional<User> optDabongKingUser = querydslRepository.findDayKingUser();
        return optDabongKingUser.map(User::getNickname).orElse(null);
    }
}
