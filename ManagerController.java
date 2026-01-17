package com.example.finalproject.controller;

import com.example.finalproject.model.Product;
import com.example.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "manager/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "manager/add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "manager/add";
        }
        productService.save(product);
        return "redirect:/manager";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/manager";
        }
        model.addAttribute("product", product);
        return "manager/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product,
            BindingResult result) {
        if (result.hasErrors()) {
            return "manager/edit";
        }
        product.setId(id); // Ensure ID is preserved
        productService.save(product);
        return "redirect:/manager";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/manager";
    }
}
