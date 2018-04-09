package com.book.library.booklibrary.library.service.author;

import com.book.library.booklibrary.library.model.DTO.AuthorDTO;
import com.book.library.booklibrary.library.model.entity.Author;

import java.util.List;

public interface AuthorServiceInterface {

    List<Author> getAllAuthors();

    Long addAuthor(AuthorDTO author);

}
