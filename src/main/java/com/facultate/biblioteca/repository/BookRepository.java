package com.facultate.biblioteca.repository;

import com.facultate.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Cerința: Custom JPA Query
    List<Book> findByTitleContainingIgnoreCase(String keyword);

}