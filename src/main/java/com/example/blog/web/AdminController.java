package com.example.blog.web;

import com.example.blog.auth.AuthService;
import com.example.blog.auth.JwtService;
import com.example.blog.web.form.AdminForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AuthService authService;
    @Autowired
    private final JwtService jwtService;

    @GetMapping
    public String adminHome(@ModelAttribute("admin") AdminForm form){
        return "admin/index";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("admin") AdminForm form, BindingResult bindingResult, HttpServletResponse response) {

        // 로그인 실패
        if (!authService.isAdmin(form.getId(), form.getPassword())){
            bindingResult.reject("failed", "아이디 또는 비밀번호가 맞지 않습니다");
            return "admin/index";
        }

        Cookie cookie = createJwtCookie(jwtService.generateToken());
        response.addCookie(cookie);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = createJwtCookie("");
        response.addCookie(cookie);

        return "redirect:/";
    }

    private Cookie createJwtCookie(String value) {
        Cookie cookie = new Cookie("jwt", value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
