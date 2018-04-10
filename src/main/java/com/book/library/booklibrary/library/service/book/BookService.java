package com.book.library.booklibrary.library.service.book;

import com.book.library.booklibrary.library.model.DTO.AddBook;
import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.library.model.entity.Library;
import com.book.library.booklibrary.library.repository.BookRepository;
import com.book.library.booklibrary.library.service.author.AuthorServiceInterface;
import com.book.library.booklibrary.library.service.category.CategoryServiceInterface;
import com.book.library.booklibrary.library.service.library.LibraryServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    private BookRepository bookRepository;
    private ModelMapper modelMapper;
    private CategoryServiceInterface categoryService;
    private AuthorServiceInterface authorService;
    private LibraryServiceInterface libraryService;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper, CategoryServiceInterface categoryService, AuthorServiceInterface authorService, LibraryServiceInterface libraryService) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.libraryService = libraryService;
    }

    @Override
    public Long addNewBook(AddBook bookDTO, Principal principal) throws Exception {

        Book insertBook = this.modelMapper.map(bookDTO, Book.class);
        Library ownerLibrary = this.libraryService.getLibraryByUsername(principal.getName());

        insertBook.setLibrary(ownerLibrary);
        return this.bookRepository.save(insertBook).getId();
    }

    @Override
    public Slice<Book> getAllBookPages(Pageable pageable, String category) {
        System.out.println(category);
        if ( category.equals("")) {

            System.out.println("all");
            return this.bookRepository.findAll(pageable);
        } else {
            return this.bookRepository.findAllByCategoriesName(category, pageable);
        }
    }
}
