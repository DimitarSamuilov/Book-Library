package com.book.library.booklibrary.library.controller;

import com.book.library.booklibrary.library.model.DTO.CategoryDTO;
import com.book.library.booklibrary.library.service.category.CategoryServiceInterface;
import jdk.jfr.Category;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("categories")
public class CategoryController {

    private CategoryServiceInterface categoryService;

    public CategoryController(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("add")
    public String addCategory(CategoryDTO category, Model model) {

        model.addAttribute("category", category);
        return "category/add";

    }

    @PostMapping("add")
    public String processCategory(@Valid @ModelAttribute("category") CategoryDTO category, BindingResult bindingResult, Model model) throws ConstraintViolationException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            return "category/add";
        }
        this.categoryService.addCategory(category);
        return "redirect:/";
    }
}
