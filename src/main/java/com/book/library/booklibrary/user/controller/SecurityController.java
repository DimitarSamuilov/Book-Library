package com.book.library.booklibrary.user.controller;

import com.book.library.booklibrary.user.model.DTO.UserDTO;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SecurityController {


    private UserServiceInterface userService;

    @Autowired
    public SecurityController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerUser(UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);
        return "security/register";
    }

    @PostMapping("/users/register")
    public String registerUser(@RequestParam(name = "confirmPassword") String confirmPassword, @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) {

        if (!confirmPassword.equals(userDTO.getPassword())) {
            bindingResult.addError(new ObjectError("password", "password miss match"));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "security/register";
        }

        this.userService.registerUser(userDTO);
        return "redirect:/users/login";
    }


    @GetMapping("/users/login")
    public String login() {
        return "security/login";

    }


}
