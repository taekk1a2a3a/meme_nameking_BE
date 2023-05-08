package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DetailPageTopResponseDto {
    private final PostResponseDto postData;
    private final String bestComment;
    private final int postDdabong;

    public DetailPageTopResponseDto(Post post, String bestComment) {
        this.postData = new PostResponseDto(post);
        this.bestComment = bestComment;
        this.postDdabong = post.getDdabong();
    }
}