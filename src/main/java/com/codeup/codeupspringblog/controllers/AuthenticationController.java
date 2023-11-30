package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
    public class AuthenticationController {
        @GetMapping("/login")
        public String showLoginForm(Model model) {
            return "users/login";
        }

}
