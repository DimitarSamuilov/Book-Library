package com.book.library.booklibrary.library.service.author;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.library.model.DTO.AuthorDTO;
import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.repository.AuthorRepository;
import org.hibernate.annotations.Cache;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthorService implements AuthorServiceInterface {

    private ModelMapper modelMapper;
    private AuthorRepository authorRepository;

    public AuthorService(ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    @Cacheable("all_authors")
    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Slice<AuthorDTO> getAuthorsPageable(Pageable page) {
        return this.authorRepository.findAll(page).map(author -> this.modelMapper.map(author, AuthorDTO.class));
    }

    @Override
    public AuthorDTO getAuthorById(Long id) throws NoSuchResourceException {
        Optional<Author> authorOptional = this.authorRepository.findById(id);
        if(!authorOptional.isPresent()){
            throw  new NoSuchResourceException("No such author to edit");
        }
        return this.modelMapper.map(authorOptional.get(),AuthorDTO.class);
    }

    @Override
    public Author addAuthor(AuthorDTO author) {
        return this.authorRepository.save(this.modelMapper.map(author, Author.class));
    }
}
