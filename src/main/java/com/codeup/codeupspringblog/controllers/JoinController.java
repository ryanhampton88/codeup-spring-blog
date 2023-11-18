package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class JoinController {

    @GetMapping("/join")
    public String showJoinForm(Model model){
        ArrayList<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Apples");
        shoppingCart.add("Honey Buns");
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("cohort", "");
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model){
        model.addAttribute("cohort", cohort);
        return "join";
    }




}
