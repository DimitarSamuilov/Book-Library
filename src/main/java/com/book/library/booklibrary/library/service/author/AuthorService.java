package com.book.library.booklibrary.library.service.author;

import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements AuthorServiceInterface {

    private ModelMapper modelMapper;
    private AuthorRepository authorRepository;

    public AuthorService(ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
//        return
//                this.authorRepository.
//                        findAll().stream().
//                        map(author -> this.modelMapper.map(author, AuthorDTO.class)).
//                        collect(Collectors.toList());
    }
}
