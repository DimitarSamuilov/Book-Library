package com.book.library.booklibrary.library.model.entity;

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
}
