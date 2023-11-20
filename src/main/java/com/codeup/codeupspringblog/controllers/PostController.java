package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postRepositoryDataObject;

    public PostController(PostRepository postRepositoryDataObject) {
        this.postRepositoryDataObject = postRepositoryDataObject;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String post(Model model) {
       ArrayList<Post> allPosts = new ArrayList<>(postRepositoryDataObject.findAll());
       model.addAttribute("AllPosts", allPosts);
        return "index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(@PathVariable long id, Model model) {
        Post singlePost = postRepositoryDataObject.getPostById(id);
        model.addAttribute("singlePost", singlePost);
        return "singlepost";
    }

    @RequestMapping(path = "/posts/delete", method = RequestMethod.GET)
        public String deletePost(@RequestParam long id) {
        Post selectedPost = postRepositoryDataObject.getPostById(id);
        postRepositoryDataObject.delete(selectedPost);
        return "redirect: /posts";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String createPost() {
        return "create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createNewPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
        Post newPost = new Post(title, body);
        postRepositoryDataObject.save(newPost);
        return "redirect: /posts";
    }


}
