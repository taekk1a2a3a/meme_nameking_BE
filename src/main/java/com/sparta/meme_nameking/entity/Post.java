package com.sparta.meme_nameking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.meme_nameking.dto.PostRequestDto;
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

    @Column(nullable = false)
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostDdabong> postDdabongList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto, User user) {
        this.img = postRequestDto.getImg();
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto) {
        this.img = postRequestDto.getImg();
    }
}
