package com.book.library.booklibrary.library.repository;

import com.book.library.booklibrary.library.model.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library,Long> {

    Optional<Library> findFirstByUserUsername(String username);
}
