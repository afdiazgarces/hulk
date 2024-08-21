/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Andres Felipe Diaz
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/listProduct")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.listProduct());
        return "listProduct";
    }

    @GetMapping("/newProduct")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "formProduct";
    }

    @PostMapping("/saveProduct")
    public String save(@Validated Product p) {
        productService.saveProduct(p);
        return "redirect:/listProduct";
    }

    @GetMapping("/updateProduct/{id}")
    public String update(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.listProductId(id));
        return "formProduct";

    }

    @GetMapping("/deleteProduct/{id}")
    public String delete(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/listProduct";

    }

}
