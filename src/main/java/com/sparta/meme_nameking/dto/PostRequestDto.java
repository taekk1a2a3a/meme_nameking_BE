package com.sparta.meme_nameking.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String originImageName;
    private String imageName;
    private String imagePath;

    @Builder
    public PostRequestDto (String originImageName, String imageName, String imagePath) {
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }
}
