package com.dhirajprakash.vm.book;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;


@Entity
public class Author {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
 
    @Column(nullable = false)
    private String name;
 
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name = "book_author", 
      joinColumns = @JoinColumn(
        name = "book_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(
        name = "author_id", referencedColumnName = "id"))
    private List<Book> books;
}
