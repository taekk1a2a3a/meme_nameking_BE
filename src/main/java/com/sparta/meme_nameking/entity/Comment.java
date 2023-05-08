package com.sparta.meme_nameking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.meme_nameking.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    // 다대일 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    @JsonBackReference
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentDdabong> commentDdabongList = new ArrayList<>();

    private int Ddabong;


    public Comment(CommentRequestDto commentRequestDto, User user, Post post) {
        this.content = commentRequestDto.getContent();
        this.user = user;
        this.post = post;
    }
    public void update(CommentRequestDto requestDto, User user){
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void incDdabong() { ++Ddabong; }
    public void decDdabong() { --Ddabong; }
}
