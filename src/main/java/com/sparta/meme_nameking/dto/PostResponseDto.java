package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String img;
    private LocalDateTime createAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.img = post.getImg();
        this.createAt = post.getCreatedAt();
    }

}