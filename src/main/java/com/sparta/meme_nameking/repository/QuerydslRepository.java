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
    public Optional<Long> getDabongKingUserId() {
        QComment qComment = QComment.comment;
        Long userId = queryFactory.select(qComment.user.id)
                .from(qComment)
                .groupBy(qComment.user.id)
                .orderBy(qComment.Ddabong.sum().desc())
                .limit(1)
                .fetchOne();
        return Optional.ofNullable(userId);
    }
}
