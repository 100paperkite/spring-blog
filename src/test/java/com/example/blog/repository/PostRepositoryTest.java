package com.example.blog.repository;

import com.example.blog.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired PostRepository postRepository;

    @Transactional
    @Rollback(value = false)
    @Test
    public void 포스팅이_저장되어야_한다() throws Exception {
        //given
        Post post = Post.createPost("test", "content", null);

        //when
        Long savedId = postRepository.save(post);
        Post foundPost = postRepository.find(savedId);

        //then
        assertThat(savedId).isEqualTo(foundPost.getId());
    }

}
