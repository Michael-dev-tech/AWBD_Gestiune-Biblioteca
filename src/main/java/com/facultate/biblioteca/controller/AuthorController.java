package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Author;
import com.facultate.biblioteca.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("author")
public class AuthorController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    @GetMapping("all")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(params = "id")
    public Author getAuthorById(@RequestParam final Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PutMapping
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping
    public void deleteAuthor(@RequestParam final Long id) {
        authorService.deleteAuthor(id);
    }
}
