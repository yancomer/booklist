package com.omer.booklist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.booklist.dao.model.Book;
import com.omer.booklist.dao.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @SuppressWarnings("deprecation")
    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }

    public List<Book> getAllUsers() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
}
