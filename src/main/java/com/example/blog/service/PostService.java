package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Long publish(Post post) {
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findOne(Long id) {
        return postRepository.find(id);
    }
}
