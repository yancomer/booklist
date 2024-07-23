package com.omer.booklist.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omer.booklist.dao.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    
}
