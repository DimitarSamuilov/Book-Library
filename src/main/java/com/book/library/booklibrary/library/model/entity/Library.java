package com.book.library.booklibrary.library.model.entity;

import com.book.library.booklibrary.user.model.entity.User;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "library_details")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "library_description", nullable = false, columnDefinition = "text")
    private String libraryDescription;

    private Double longitude;

    private Double latitude;

    @ManyToMany(targetEntity = Book.class)
    @JoinTable(
            name = "library_book",
            joinColumns = {@JoinColumn(name = "library_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )
    private Set<Book> books;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public Library() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibraryDescription() {
        return libraryDescription;
    }

    public void setLibraryDescription(String libraryDescription) {
        this.libraryDescription = libraryDescription;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
