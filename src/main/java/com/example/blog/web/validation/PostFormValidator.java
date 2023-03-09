package com.example.blog.web.validation;

import com.example.blog.domain.form.PostForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PostFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostForm post = (PostForm) target;

        if (!StringUtils.hasText(post.getTitle())){
            errors.rejectValue("title", "required", "제목이 비어있습니다.");
        }
        if (!StringUtils.hasText(post.getContent())){
            errors.rejectValue("content", "required", "내용이 비어있습니다.");
        }
    }
}
