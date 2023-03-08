package com.example.blog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Tag {
    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
