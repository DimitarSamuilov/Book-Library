package com.book.library.booklibrary.library.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthorDTO {
    private static final String EMPTY_MESSAGE = "Field cannot be empty";
    private static final String SIZE_NAME_MESSAGE = "Name must be between 3 and 100 chars ";

    private Long id;

    @NotNull(message = EMPTY_MESSAGE)
    @Size(min = 3, max = 100, message = SIZE_NAME_MESSAGE)
    private String name;

    private String description;

    public AuthorDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
