package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String originImageName;
    private String imageName;
    private String imagePath;
    private LocalDateTime createAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.originImageName = post.getOriginImageName();
        this.imageName = post.getImageName();
        this.imagePath = post.getImagePath();
        this.createAt = post.getCreatedAt();
    }

}