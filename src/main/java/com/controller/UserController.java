/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.User;
import com.service.UserService;

/**
 *
 * @author Andres Felipe Diaz
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/listUser")
    public String listUser(Model model) {
        model.addAttribute("users", userService.listUser());
        return "listUser";
    }

    @GetMapping("/newUser")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "formUser";
    }

    @PostMapping("/saveUser")
    public String save(@Validated User u) {
        u.setPassword(Base64.getEncoder().encodeToString(u.getPassword().getBytes()));
        userService.saveUser(u);
        return "redirect:/listUser";
    }

    @GetMapping("/updateUser/{id}")
    public String update(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.listarUserId(id));
        return "formUser";

    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable int id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
        }

        return "redirect:/listUser";

    }

}
