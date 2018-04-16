package com.book.library.booklibrary.library.controller;

import com.book.library.booklibrary.library.model.DTO.AuthorDTO;
import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.service.author.AuthorServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_LIBRARY')")
@RequestMapping("/authors")
public class AuthorController {

    private AuthorServiceInterface authorService;

    public AuthorController(AuthorServiceInterface authorService) {
        this.authorService = authorService;
    }

    @GetMapping("add")
    public String addAuthor(Model model, AuthorDTO author) {

        model.addAttribute("author", author);
        return "author/add";

    }

    @PostMapping("add")
    public String processAuthor(@Valid @ModelAttribute("author") AuthorDTO author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("author", author);
            return "author/add";
        }

        this.authorService.addAuthor(author);
        return "redirect:/";
    }
}
