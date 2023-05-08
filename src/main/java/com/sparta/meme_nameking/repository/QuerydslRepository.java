package com.sparta.meme_nameking.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.meme_nameking.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuerydslRepository {

    private final JPAQueryFactory queryFactory;

    // 따봉킹 찾기 QueryDSL
    // SELECT user_id, SUM(DDABONG) FROM COMMENT group by user_id having sum(DDABONG) > 0 ORDER BY SUM(DDABONG) desc
    public Optional<Long> getDabongKingUserId() {
        QComment qComment = QComment.comment;
        Long userId = queryFactory.select(qComment.user.id)
                .from(qComment)
                .groupBy(qComment.user.id)
                .having((qComment.Ddabong.sum().gt(0)))
                .orderBy(qComment.Ddabong.sum().desc())
                .limit(1)
                .fetchOne();
        return Optional.ofNullable(userId);
    }
}
