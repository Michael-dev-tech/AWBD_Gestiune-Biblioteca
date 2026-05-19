package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    // Returnează toate cărțile în format JSON
    @GetMapping
    public List<Book> getAllBooksApi() {
        return bookService.getAllBooks();
    }

    // Returnează o singură carte după ID în format JSON
    @GetMapping("/{id}")
    public Book getBookByIdApi(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
}