package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "rolldice";
    }

    @GetMapping("/roll-dice/{number}")
    public String getResult(@PathVariable int number, Model model) {
        int random = (int) (Math.floor(Math.random() * 6) + 1);

        if (random == number) {
            String correctGuess = "you guessed " + number + " correctly!";
            model.addAttribute("message", correctGuess);
        } else {
            String incorrectGuess = "You guessed incorrectly. The correct guess is " + random;
            model.addAttribute("message", incorrectGuess);
        }
        return "result";
    }



}
