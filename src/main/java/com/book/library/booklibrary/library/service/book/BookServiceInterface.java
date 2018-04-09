package com.book.library.booklibrary.library.service.book;

import com.book.library.booklibrary.library.model.DTO.AddBook;
import com.book.library.booklibrary.library.model.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.security.Principal;

public interface BookServiceInterface {

    Long addNewBook(AddBook bookDTO, Principal principal) throws Exception;

    Slice<Book> getAllBookPages(Pageable pageable);
}
