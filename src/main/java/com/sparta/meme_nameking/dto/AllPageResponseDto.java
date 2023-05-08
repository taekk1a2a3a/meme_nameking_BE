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

    private String ddabongKing;
    private List<PostList> allPageList;

    @Getter
    @AllArgsConstructor
    public static class PostList {

        @Column(nullable = true)
        private String bestComment;

        @Column(nullable = true)
        private String imageName;

        @Column(nullable = true)
        private int postDdabong;

        public PostList(String bestComment, Post post){
            this.bestComment = bestComment;
            this.imageName = post.getImageName();
            this.postDdabong = post.getDdabong();
        }
    }
}
