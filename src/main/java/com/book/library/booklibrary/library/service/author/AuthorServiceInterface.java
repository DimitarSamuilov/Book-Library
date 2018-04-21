package com.book.library.booklibrary.library.service.author;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.library.model.DTO.AuthorDTO;
import com.book.library.booklibrary.library.model.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AuthorServiceInterface {

    List<Author> getAllAuthors();

    Slice<AuthorDTO> getAuthorsPageable(Pageable page);
    AuthorDTO getAuthorById(Long id) throws NoSuchResourceException;
    Long addAuthor(AuthorDTO author);
}
