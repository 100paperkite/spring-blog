package com.example.blog.web.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminForm {
    @NotNull
    String id;
    @NotNull
    String password;
}
