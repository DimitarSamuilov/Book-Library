package com.book.library.booklibrary.library.service.category;

import com.book.library.booklibrary.library.model.DTO.CategoryDTO;
import com.book.library.booklibrary.library.model.entity.Category;

import java.util.List;

public interface CategoryServiceInterface {

    List<Category> getAllCategories();

    Category addCategory(CategoryDTO categoryDTO);
}
