package com.sparta.meme_nameking.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.meme_nameking.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuerydslRepository {


    private final JPAQueryFactory queryFactory;

    public Long getDabongKingUserId() {
        QComment qComment = QComment.comment;

        return queryFactory.select(qComment.user.id)
                .from(qComment)
                .groupBy(qComment.user.id)
                .orderBy(qComment.Ddabong.sum().desc())
                .limit(1)
                .fetchOne();
    }
}
