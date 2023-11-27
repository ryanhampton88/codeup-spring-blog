package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postRepositoryDataObject;
    private final UserRepository userRepositoryDAO;

    private final EmailService emailService;

    public PostController(PostRepository postRepositoryDataObject, UserRepository userRepositoryDAO, EmailService emailService) {
        this.postRepositoryDataObject = postRepositoryDataObject;
        this.userRepositoryDAO = userRepositoryDAO;
        this.emailService = emailService;
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

    @RequestMapping(path = "/posts/delete/{id}", method = RequestMethod.GET)
        public String deletePost(@PathVariable long id, Model model) {
        Post selectedPost = postRepositoryDataObject.getPostById(id);
        postRepositoryDataObject.delete(selectedPost);
        return "redirect:/posts";
    }

    @RequestMapping(path = "/posts/edit/{id}", method = RequestMethod.GET)
    public String getUpdate(@PathVariable long id, Model model) {
        Post selectedPost = postRepositoryDataObject.getPostById(id);
        model.addAttribute("selectedPost", selectedPost);
        return "edit";
    }

    @RequestMapping(path = "/posts/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @RequestParam(name = "id") String id, Model model) {
        long postID = Long.parseLong(id);
        Post updatedPost = new Post(postID, title, body);
        postRepositoryDataObject.save(updatedPost);
        return "redirect:/posts";
    }

//    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
//    public String createPost() {
//        return "create";
//    }

//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
//    public String createNewPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
//        User loggedInUser = userRepositoryDAO.getUsersById(1);
//        Post newPost = new Post(loggedInUser, title, body);
//        postRepositoryDataObject.save(newPost);
//        return "redirect:/posts";
//    }

    @GetMapping("/posts/create")
    public String setAdModel(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post) {
        User user = userRepositoryDAO.getUsersById(1L);
        post.setUser(user);
        postRepositoryDataObject.save(post);
        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
        return "redirect:/posts";
    }


}
