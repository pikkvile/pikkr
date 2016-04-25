package com.pikkvile.pikkr.controllers;

import com.pikkvile.pikkr.enums.PostType;
import com.pikkvile.pikkr.model.NewPost;
import com.pikkvile.pikkr.services.CategoriesService;
import com.pikkvile.pikkr.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    PostsService postsService;

    @RequestMapping("/login")
    public String login() {
        return "editor/login";
    }

    @RequestMapping({"", "/"})
    public String main(Model model) {
        return "redirect:/editor/new_post";
    }

    @RequestMapping("/new_post")
    public String newPostForm(Model model) {
        model.addAttribute("types", PostType.values());
        model.addAttribute("cats", categoriesService.getCategories());
        return "editor/new_post";
    }

    @RequestMapping(value="/new_post", method = RequestMethod.POST)
    public String newPost(NewPost newPost, MultipartHttpServletRequest request) throws Exception {
        postsService.createPost(newPost, request.getFileMap());
        return "redirect:/editor/new_post";
    }

}
