package com.pikkvile.pikkr.controllers;

import com.pikkvile.pikkr.model.entities.Category;
import com.pikkvile.pikkr.services.CategoriesService;
import com.pikkvile.pikkr.services.PostsService;
import org.apache.velocity.tools.generic.MathTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")
public class PostsController {

    @Autowired
    PostsService postsService;

    @Autowired
    CategoriesService categoriesService;

    @Value("${pikkr.files-url-base}")
    String filesUrlBase;

    @RequestMapping("/")
    public String allPosts(Model model, @RequestParam(name="page", required = false) Integer page) {
        page = page != null ? page : 1;
        model.addAttribute("active", "all");
        model.addAttribute("filesUrlBase", filesUrlBase);
        model.addAttribute("categories", categoriesService.getCategories());
        model.addAttribute("posts", postsService.getAllPosts(page - 1));
        model.addAttribute("math", new MathTool());
        return "posts";
    }

    @RequestMapping("/c/{categoryName}")
    public String postsByCategory(Model model,
                                  @PathVariable("categoryName") String categoryName,
                                  @RequestParam(name="page", required = false) Integer page) {
        page = page != null ? page : 1;
        List<Category> categories = categoriesService.getCategories();
        if (categories.stream().noneMatch(c -> c.getTitle().equals(categoryName))) {
            return "404";
        }
        model.addAttribute("filesUrlBase", filesUrlBase);
        model.addAttribute("active", categoryName);
        model.addAttribute("categories", categories);
        model.addAttribute("posts", postsService.getPostsInCategory(categoryName, page - 1));
        model.addAttribute("math", new MathTool());
        return "posts";
    }
}
