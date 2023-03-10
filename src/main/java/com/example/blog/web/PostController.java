package com.example.blog.web;

import com.example.blog.domain.Post;
import com.example.blog.service.PostService;
import com.example.blog.web.form.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public String allPosts(Model model){
        List<Post> posts = postService.findAllPosts();

        model.addAttribute("posts", posts);
        return "index";
    }
    @GetMapping("{postId}")
    public String post(@PathVariable long postId, Model model){
        Post post = postService.findOne(postId);
        if (post == null) {
            return "404";
        }

        model.addAttribute("post", post);
        return "posts/info";
    }

    @GetMapping("/write")
    public String writePostForm(Model model){
        model.addAttribute("post", new PostForm());
        return "posts/write";
    }


    @PostMapping("/write")
    public String savePost(@Validated @ModelAttribute("post") PostForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "posts/write";
        }
        Post post = Post.createPost(form.getTitle(), form.getContent(), null);

        Long postId = postService.publish(post);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

}
