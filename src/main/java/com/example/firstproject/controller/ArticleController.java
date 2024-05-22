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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "redirect:/articles/" + saved.getId();
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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // get the data would be modified.
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // add the data in model
        model.addAttribute("article", articleEntity);

        //set view page
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        //TODO: 1. convert DTO(form) to entity(articleEntity)
        Article articleEntity = form.toEntity();
        //TODO: 2. save the entity in DB
        //TODO: 2-1. get origin data from DB
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //TODO: 2-2. modify the value over the origin data
        if(target != null){
            articleRepository.save(articleEntity);
        }
        // check it in log
        log.info(articleEntity.toString());
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("delete request!!");
        //TODO: 1. get the data would be deleted.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //TODO: 2. Delete the target entity
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "Title [" + target.getTitle() + "] Article deleted");
        }

        //TODO: 3. return the result page
        return "redirect:/articles";
    }
}
