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

        // 게시글 등록 완성되면 수정하면 될 부분
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
