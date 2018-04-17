package com.book.library.booklibrary.library.service.category;

import com.book.library.booklibrary.home.exception.ConstraintViolationException;
import com.book.library.booklibrary.library.model.DTO.CategoryDTO;
import com.book.library.booklibrary.library.model.entity.Category;
import com.book.library.booklibrary.library.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface {

    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    public CategoryService(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Cacheable("all_categoires")
    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Long addCategory(CategoryDTO categoryDTO) throws ConstraintViolationException {
        Optional<Category> categoryOptional = this.categoryRepository.findByName(categoryDTO.getName());
        if (categoryOptional.isPresent()) {
            throw new ConstraintViolationException("Category already exists");
        }
        return this.categoryRepository.save(this.modelMapper.map(categoryDTO, Category.class)).getId();
    }
}
