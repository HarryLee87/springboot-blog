package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {

    @GetMapping("/random-quote")
    public String randomQuote(Model modle) {
        String[] quotes = {
                "You’ve got to be in it to win it. " +
                        "—Tony Robbins",
                "I can accept failure, everyone fails at something. But I can’t accept not trying. " +
                        "-Michael Jordan",
                "Don’t let yesterday take up too much of today. " +
                        "—Will Rogers",
                "Be persistent and never give up hope. " +
                        "—George Lucas",
                "A problem is a chance for you to do your best. " +
                        "—Duke Ellington"
        };
        int ranInt = (int) (Math.random() * quotes.length);
        modle.addAttribute("ramdomQuote", quotes[ranInt]);
        return "quote";
    }
}
