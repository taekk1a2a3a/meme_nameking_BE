package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllPageResponseDto {

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = true)
    private String bestComment;

        @Column(nullable = true)
        private String imageUrl;

    @Column(nullable = true)
    private int postDdabong;

    public AllPageResponseDto(Post post, String bestComment){
        this.postId = post.getId();
        this.bestComment = bestComment;
        this.imageUrl = post.getImageUrl();
        this.postDdabong = post.getDdabong();
    }
}
