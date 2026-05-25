package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void listBooksShowsExistingBooks() throws Exception {
        Book book = new Book();
        book.setTitle("Baltagul");
        book.setIsbn("978-606-123");
        bookRepository.save(book);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(content().string(containsString("Baltagul")));
    }

    @Test
    void validBookFormCreatesBookAndRedirects() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Ion")
                        .param("isbn", "978-973-46"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        assertEquals(1, bookRepository.count());
        assertEquals("Ion", bookRepository.findAll().getFirst().getTitle());
    }

    @Test
    void invalidBookFormReturnsValidationErrors() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "")
                        .param("isbn", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"))
                .andExpect(model().attributeHasFieldErrors("book", "title", "isbn"));

        assertEquals(0, bookRepository.count());
    }

    @Test
    void apiBooksReturnsJsonBooks() throws Exception {
        Book book = new Book();
        book.setTitle("Micul Print");
        book.setIsbn("978-015-456");
        bookRepository.save(book);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Micul Print"));
    }

    @Test
    void searchBooksFiltersByTitle() throws Exception {
        Book matchingBook = new Book();
        matchingBook.setTitle("Baltagul");
        matchingBook.setIsbn("978-606-123");
        bookRepository.save(matchingBook);

        Book otherBook = new Book();
        otherBook.setTitle("Ion");
        otherBook.setIsbn("978-973-46");
        bookRepository.save(otherBook);

        mockMvc.perform(get("/books/search").param("keyword", "balt"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(content().string(containsString("Baltagul")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString(">Ion<"))));

        assertTrue(bookRepository.count() >= 2);
    }
}
