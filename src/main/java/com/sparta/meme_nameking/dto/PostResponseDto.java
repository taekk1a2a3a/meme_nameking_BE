package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String imageName;
    private LocalDateTime createAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.imageName = post.getImageName();
        this.createAt = post.getCreatedAt();
    }

}