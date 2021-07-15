package com.example.Projekt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HelloController {

    Logger logger = LogManager.getLogger(HelloController.class);

    @Autowired
    GameRepo gameRepo;

    @GetMapping("/hello")
    public String get() {
        return "hello";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/search")
    public String contact(Model model) {

        Iterable<GameItem> games = gameRepo.findAll();
        model.addAttribute("games", games);

        return "search";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        logger.info("go to details, id=" + id);
        Optional<GameItem> game = gameRepo.findById(id);
        model.addAttribute("game", game);

        return "details";
    }
}
