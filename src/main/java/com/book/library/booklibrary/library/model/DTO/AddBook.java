package com.book.library.booklibrary.library.model.DTO;

import com.book.library.booklibrary.library.model.entity.Author;
import com.book.library.booklibrary.library.model.entity.Category;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddBook {

    private static final String NOT_EMPTY_MESSAGE = "Field cannot be empty";
    private static final String MIN_NUMBER_SIZE_MESSAGE = "Number cannot be 0";
    private static final String PAGES_NUMBER_MESSAGE = "Number of pages invalid";
    private Long id;

    @NotEmpty(message = NOT_EMPTY_MESSAGE)
    @Size(min = 5)
    private String name;

    @NotNull(message = NOT_EMPTY_MESSAGE)
    @Min(value = 1, message = PAGES_NUMBER_MESSAGE)
    private Integer numberOfPages;

    private Integer copiesAvailable;

    @NotNull(message = NOT_EMPTY_MESSAGE)
    @Min(value = 0, message = MIN_NUMBER_SIZE_MESSAGE)
    private Double buyPrice;

    @NotNull(message = NOT_EMPTY_MESSAGE)
    @Min(value = 0, message = MIN_NUMBER_SIZE_MESSAGE)
    private Double rentPrice;

    @NotNull(message = NOT_EMPTY_MESSAGE)
    private Author author;

    @NotNull(message = NOT_EMPTY_MESSAGE)
    private Category category;

    private String bookDescription;

    public AddBook() {
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(Integer copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", copiesAvailable=" + copiesAvailable +
                ", buyPrice=" + buyPrice +
                ", rentPrice=" + rentPrice +
                ", author=" + author +
                ", category=" + category +
                ", bookDescription='" + bookDescription + '\'' +
                '}';
    }
}
