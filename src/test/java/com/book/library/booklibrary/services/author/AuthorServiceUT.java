package com.book.library.booklibrary.services.author;

import com.book.library.booklibrary.library.model.DTO.AuthorDTO;
import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.repository.AuthorRepository;
import com.book.library.booklibrary.library.service.author.AuthorService;
import com.book.library.booklibrary.library.service.author.AuthorServiceInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthorServiceUT {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorDTO authorDTO;

    private ModelMapper modelMapper=new ModelMapper();

    @InjectMocks
    private AuthorService authorService = new AuthorService(this.modelMapper,this.authorRepository);
    @Before
    public void setUp(){
        this.authorDTO=new AuthorDTO();
        this.authorDTO.setDescription("description");
        this.authorDTO.setName("AuthorName");

        when(this.authorRepository.save(any())).thenAnswer(a->a.getArgument(0));
    }


    @Test
    public void saveAuthorInDatabase(){
        Author createdAuthor = this.authorService.addAuthor(this.authorDTO);
        Assert.assertNotEquals("authors dont match",null ,createdAuthor);
    }

}
