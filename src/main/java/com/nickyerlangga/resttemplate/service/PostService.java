package com.nickyerlangga.resttemplate.service;

import com.nickyerlangga.resttemplate.model.Post;
import com.nickyerlangga.resttemplate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void addPost(Post post){
        postRepository.save(post);
    }

}
