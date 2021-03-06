package com.book.library.booklibrary.library.repository;

import com.book.library.booklibrary.library.model.entity.Author;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
