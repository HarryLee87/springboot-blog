package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Iterator;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
//        System.out.println(form.toString());

        //TODO: 1. Transfer the DTO to an Entity
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        //TODO: 2. Store the Entity into the DB via the repository
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        //TODO: 1. fetch data through the id
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //TODO: 2. register the data in model
        model.addAttribute("article", articleEntity);

        //TODO: 3. return view page
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //TODO: 1. Fetch all data
        Iterable<Article> articleEntityList = articleRepository.findAll();

        //TODO: 2. store the data in model
        model.addAttribute("articleList", articleEntityList);

        //TODO: 3. set view page
        return "articles/index";
    }
}
