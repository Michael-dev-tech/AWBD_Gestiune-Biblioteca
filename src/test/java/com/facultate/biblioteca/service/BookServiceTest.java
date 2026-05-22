package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository; // Facem o copie falsă a bazei de date

    @InjectMocks
    private BookService bookService; // Injectăm baza falsă în serviciul nostru real

    @Test
    public void testGetAllBooks() {
        // 1. Pregătim datele false pentru test
        Book book1 = new Book();
        book1.setTitle("Cartea de Test 1");

        Book book2 = new Book();
        book2.setTitle("Cartea de Test 2");


        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));


        List<Book> books = bookService.getAllBooks();


        assertEquals(2, books.size(), "Ar trebui să găsim exact 2 cărți");
        assertEquals("Cartea de Test 1", books.getFirst().getTitle(), "Titlul primei cărți trebuie să se potrivească");
    }
}