package com.sparta.meme_nameking.dto;

import com.sparta.meme_nameking.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class AllPageResponseDto {

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = true)
    private String bestComment;

    // 게시글 등록 완성되면 수정하면 될 부분
    @Column(nullable = true)
    private String imageName;

    @Column(nullable = true)
    private int postDdabong;

    public AllPageResponseDto(Post post, String bestComment){
        this.postId = post.getId();
        this.bestComment = bestComment;
        this.imageName = post.getImageName();
        this.postDdabong = post.getDdabong();
    }
}
