package com.facultate.biblioteca;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            Book b1 = new Book();
            b1.setTitle("Baltagul");
            b1.setIsbn("978-606-123");
            bookRepository.save(b1);

            Book b2 = new Book();
            b2.setTitle("Micul Print");
            b2.setIsbn("978-015-456");
            bookRepository.save(b2);

            System.out.println("Datele de test au fost inserate cu succes!");
        }
    }
}