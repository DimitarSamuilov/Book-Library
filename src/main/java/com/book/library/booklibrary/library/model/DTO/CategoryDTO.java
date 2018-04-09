package com.book.library.booklibrary.library.model.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {
    private static final String NOT_EMPTY_CATEGORY_MESSAGE = "Category name cannot be empty";
    private static final String NAME_SIZE_MESSAGE = "Category name must be between 5 and 100 characters";

    private Long id;

    @NotNull(message = NOT_EMPTY_CATEGORY_MESSAGE)
    @Size(min = 5, max = 100, message = NAME_SIZE_MESSAGE)
    private String name;

    @Min(0)
    private int weight;

    public CategoryDTO() {
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
