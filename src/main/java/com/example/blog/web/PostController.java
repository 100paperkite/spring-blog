package com.example.blog.web;

import com.example.blog.domain.Post;
import com.example.blog.domain.form.PostForm;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "post";
    }

    @GetMapping("/write")
    public String writePostForm(){
        return "form/writePost";
    }


    @PostMapping("/write")
    public String savePost(@ModelAttribute PostForm form, RedirectAttributes redirectAttributes) {
        Post post = Post.createPost(form.getTitle(), form.getContent(), null);

        Long postId = postService.publish(post);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

}
