package com.bookstore.booksapplication.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books", schema = "bookstore")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    // Many users can read many books
    @ManyToMany(mappedBy = "books")
    private Set<user> users = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
