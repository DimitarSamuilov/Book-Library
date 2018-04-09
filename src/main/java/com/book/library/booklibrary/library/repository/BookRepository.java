package com.book.library.booklibrary.library.repository;

import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

    List<Book> findAllByAuthorName(String author);
}
