package com.websiteshop.HomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.dao.CategoryDAO;

@Controller
public class ShoppingCartController {
    @Autowired
    CategoryDAO dao;

    @RequestMapping("/cart/view")
    public String view(Model model) {
        model.addAttribute("cates", dao.findAll());
        return "cart/view";

    }
}
