package com.book.library.booklibrary.library.controller;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.library.model.DTO.AuthorDTO;
import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.service.author.AuthorServiceInterface;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/authors/all";
    }

    @GetMapping("/all")
    public String allAuthors(@PageableDefault(size = 2) Pageable pageable, Model model) {
        model.addAttribute("authors", this.authorService.getAuthorsPageable(pageable));
        model.addAttribute("pageable", pageable);
        return "author/list";
    }

    @GetMapping("/editAuthor/{id}")
    public String editAuthor(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("author", this.authorService.getAuthorById(id));
        return "author/add";
    }

    @PostMapping("/editAuthor/{id}")
    public String processEditAuthor(
            @Valid @ModelAttribute("author") AuthorDTO authorDTO,
            BindingResult bindingResult, @PathVariable(name = "id") Long id,
            Model model
    ) throws NoSuchResourceException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("author", authorDTO);
            return "author/add";
        }
        authorDTO.setId(id);
        this.authorService.getAuthorById(id);
        this.authorService.addAuthor(authorDTO);
        return "redirect:/authors/all";
    }


}
