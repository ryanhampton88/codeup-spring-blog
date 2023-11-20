package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "rolldice";
    }

        @PostMapping("/roll-dice")
        public String getNumbers(@RequestParam(name = "number") int number,
    @RequestParam(name = "number2") int number2, Model model) {
        System.out.println(number);
        System.out.println(number2);
        model.addAttribute("number", number);
        model.addAttribute("number2", number2);

            int random1 = (int) (Math.floor(Math.random() * 6) + 1);
            int random2 = (int) (Math.floor(Math.random() * 6) + 1);

            if (number == random1 && number2 == random2) {
                String correctGuess = "you guessed " + number + " and " + number2 + " correctly!";
            model.addAttribute("message", correctGuess);
            } else {
                String incorrectGuess = "You guessed incorrectly with " + number + " and " +
                    number2 + ". The correct guess is " + random1 + " and " + random2 + ".";
                model.addAttribute("message", incorrectGuess);
            }
        return "result";
        }

}
