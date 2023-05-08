package com.sparta.meme_nameking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "post_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostDdabong> postDdabongList = new ArrayList<>();

    @Builder
    public Post(String imageUrl, User user) {
        this.imageUrl = imageUrl;
        this.user = user;
    }

    private int Ddabong;

    public void update(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void incDdabong() { ++Ddabong; }
    public void decDdabong() { --Ddabong; }
}
