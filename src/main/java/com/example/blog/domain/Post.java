package com.example.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private String content;

    @ManyToMany
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public static Post createPost(String title, String content, Category category, Tag... tags) {
        Post post = new Post();

        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);

        for (Tag tag: tags){
            post.getTags().add(tag);
        }

        post.setCreatedAt(LocalDateTime.now());

        return post;
    }
}
