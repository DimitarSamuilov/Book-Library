package com.book.library.booklibrary.library.controller;

import com.book.library.booklibrary.library.model.DTO.AddBook;
import com.book.library.booklibrary.library.service.author.AuthorServiceInterface;
import com.book.library.booklibrary.library.service.book.BookServiceInterface;
import com.book.library.booklibrary.library.service.category.CategoryService;
import com.book.library.booklibrary.library.service.category.CategoryServiceInterface;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookServiceInterface bookService;
    private AuthorServiceInterface authorService;
    private CategoryServiceInterface categoryService;

    public BookController(BookServiceInterface bookService, AuthorServiceInterface authorService, CategoryServiceInterface categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String addBook(AddBook addBook, Model model) {
        //@todo redirect if libdetails are missing
        model.addAttribute("book", addBook);
        model.addAttribute("authors", this.authorService.getAllAuthors());
        model.addAttribute("categories", this.categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String processNewBook(@Valid @ModelAttribute("book") AddBook book, BindingResult bindingResult, Model model, Principal principal) throws Exception {
        //@todo redirect if libdetails are missing
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", this.authorService.getAllAuthors());
            model.addAttribute("categories", this.categoryService.getAllCategories());

            return "book/add";
        }
        this.bookService.addNewBook(book, principal);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String bookList(@RequestParam(name = "category",required = false,defaultValue = "") String category,@PageableDefault(size = 2) Pageable pageable, Model model) {
        model.addAttribute("categories",this.categoryService.getAllCategories());
        model.addAttribute("pageable", pageable);
        model.addAttribute("books", this.bookService.getAllBookPages(pageable,category));
        return "book/list";
    }
}
