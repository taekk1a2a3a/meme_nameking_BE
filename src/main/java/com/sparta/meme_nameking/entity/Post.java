package com.sparta.meme_nameking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.meme_nameking.dto.PostRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String originImageName;

    @Column(length = 500, nullable = false)
    private String imageName;

    @Column(length = 1000, nullable = false)
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

//    @JsonBackReference
//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    private List<Comment> commentList = new ArrayList<>();
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    private List<PostDdabong> postDdabongList = new ArrayList<>();

    @Builder
    public Post(String originImageName, String imagePath, String imageName, User user) {
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.user = user;
    }

//    public void update(PostRequestDto postRequestDto) {
//        this.originImageName = postRequestDto.getOriginImageName();
//        this.imageName = postRequestDto.getImageName();
//        this.imagePath = postRequestDto.getImagePath();
//    }
}
