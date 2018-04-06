package com.book.library.booklibrary.library.repository;

import com.book.library.booklibrary.library.model.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library,Long> {
}
