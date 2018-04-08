package com.book.library.booklibrary.library.service.book;

import com.book.library.booklibrary.library.model.DTO.AddBook;

import java.security.Principal;

public interface BookServiceInterface {

    Long addNewBook(AddBook bookDTO, Principal principal) throws Exception;

}
