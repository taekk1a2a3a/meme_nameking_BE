package com.sparta.meme_nameking.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.meme_nameking.entity.Comment;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.QComment;
import com.sparta.meme_nameking.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuerydslRepository {

    private final JPAQueryFactory queryFactory;

    // 따봉킹 찾기 QueryDSL
    // SELECT user_id, SUM(DDABONG) FROM COMMENT group by user_id having sum(DDABONG) > 0 ORDER BY SUM(DDABONG) desc
    public Optional<User> findDayKingUser() {
        QComment qComment = QComment.comment;
        User user = queryFactory
                .select(qComment.user)
                .from(qComment)
                .groupBy(qComment.user)
                .having(qComment.Ddabong.sum().gt(0))
                .orderBy(qComment.Ddabong.sum().desc())
                .limit(1)
                .fetchOne();
        return Optional.ofNullable(user);
    }

    // 베스트 댓글 찾기 QueryDSL
    // SELECT * FROM comment WHERE post_id = {post_id} ORDER BY ddabong DESC LIMIT 1;
    public Optional<Comment> findBestCommentByPost(Post post) {
        QComment qComment = QComment.comment;
        Comment bestComment = queryFactory
                .select(qComment)
                .from(qComment)
                .where(qComment.post.eq(post).and(qComment.Ddabong.gt(0)))
                .orderBy(qComment.Ddabong.desc())
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(bestComment);
    }
}
