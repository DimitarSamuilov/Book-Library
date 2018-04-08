package com.book.library.booklibrary.library.service.category;

import com.book.library.booklibrary.library.model.entity.Category;
import com.book.library.booklibrary.library.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {

    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    public CategoryService(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
//        return
//                this.categoryRepository.
//                        findAll().
//                        stream().
//                        map(category -> this.modelMapper.map(category, CategoryDTO.class)).
//                        collect(Collectors.toList());
    }
}
