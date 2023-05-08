package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String imageUrl;
    private LocalDateTime createAt;

    @Builder
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
        this.createAt = post.getCreatedAt();
    }

}