package com.nickyerlangga.resttemplate.controller;

import com.nickyerlangga.resttemplate.model.Post;
import com.nickyerlangga.resttemplate.repository.PostRepository;
import com.nickyerlangga.resttemplate.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PostsController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PostRepository postRepository;

    @RequestMapping(value = "/api/posts")
    public String getAllPost(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts", HttpMethod.GET,entity,String.class).getBody();
    }

    @RequestMapping(value = "/api/posts/{id}")
    public String getPostById(@PathVariable("id") String id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts/"+id,HttpMethod.GET,entity,String.class).getBody();
    }

    @RequestMapping(value = "/api/posts/insert")
    public String addPostToDatabase(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        Long userId,id;
        String title,body;
        String jsonString = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts", HttpMethod.GET,entity,String.class).getBody();
        System.out.println(jsonString);
        JSONArray arr = new JSONArray(jsonString);
        for(int i=0;i<arr.length();i++) {
            userId = arr.getJSONObject(i).getLong("userId");
            id = arr.getJSONObject(i).getLong("id");
            title = arr.getJSONObject(i).getString("title");
            body = arr.getJSONObject(i).getString("body");
            Post post = new Post();
            post.setUserId(userId);
            post.setId(id);
            post.setTitle(title);
            post.setBody(body);
//            System.out.println("body "+i+": "+post.getBody());
            postRepository.save(post);
        }
        return "success";
    }
}
