package com.example.blog.domain.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForm {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
