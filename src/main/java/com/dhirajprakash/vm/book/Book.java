package com.dhirajprakash.vm.book;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
public class Book {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
 
    @Column(nullable = false)
    private String title;
     
    private String isbn;
 
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Author> authors;
}
