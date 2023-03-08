package com.example.blog.repository;

import com.example.blog.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public Long save(Post post){
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
        return post.getId();
    }

    public void delete(Long id){

    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    public Post find(Long id) {
        return em.find(Post.class, id);
    }
}
