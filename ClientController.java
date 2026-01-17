package com.example.finalproject.controller;

import com.example.finalproject.model.Product;
import com.example.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "client/list";
    }

    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product != null && product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            productService.save(product);
            model.addAttribute("message", "Successfully bought " + product.getName());
        } else {
            model.addAttribute("error", "Product out of stock or not found");
        }
        return "redirect:/client";
    }
}
