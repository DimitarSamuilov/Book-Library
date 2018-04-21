package com.book.library.booklibrary.services.category;

import com.book.library.booklibrary.library.model.DTO.CategoryDTO;
import com.book.library.booklibrary.library.model.entity.Category;
import com.book.library.booklibrary.library.repository.CategoryRepository;
import com.book.library.booklibrary.library.service.category.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceUT {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ModelMapper modelMapper = new ModelMapper();

    private CategoryDTO categoryDTO;

    @InjectMocks
    private CategoryService categoryService;

    @Before
    public void setUp(){
        this.categoryDTO=new CategoryDTO();
        this.categoryDTO.setName("aaaaaaaaaaaaaaaaaaaaaaaaa");
        this.categoryDTO.setWeight(1);

        when(this.categoryRepository.save(any())).thenAnswer(a->a.getArgument(0));
    }

    @Test
    public void createCategory_ValidFields(){
        System.out.println(this.categoryService);
//        Category createdCategory = this.categoryService.addCategory(this.categoryDTO);
//        Assert.assertNotEquals("Categories save error",this.categoryDTO,createdCategory);
//        Category category = this.categoryService.addCategory(this.categoryDTO);
//        Assert.assertEquals("cats did not save",null,category);
    }

}
