package com.example.blog.web;

import com.example.blog.web.form.AdminForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Value("${blog.admin.id}")
    private String adminId;
    @Value("${blog.admin.password}")
    private String adminPassword;

    @GetMapping
    public String adminHome(@ModelAttribute("admin") AdminForm form){
        return "admin/index";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("admin") AdminForm form, BindingResult bindingResult){

        // 로그인 실패
        if (!(form.getId().equals(adminId) && form.getPassword().equals(adminPassword))){
            bindingResult.reject("failed", "아이디 또는 비밀번호가 맞지 않습니다");
            return "admin/index";
        }

        return "redirect:/";
    }
}
