package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Cartea de Test 1");

        Book book2 = new Book();
        book2.setTitle("Cartea de Test 2");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        assertEquals("Cartea de Test 1", books.getFirst().getTitle());
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setTitle("Ion");
        book.setIsbn("978-123");

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        assertEquals("Ion", savedBook.getTitle());
        verify(bookRepository).save(book);
    }

    @Test
    public void testGetBookByIdFound() {
        Book book = new Book();
        book.setTitle("Enigma Otiliei");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(1L);

        assertEquals("Enigma Otiliei", foundBook.getTitle());
    }

    @Test
    public void testGetBookByIdMissingThrowsException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.getBookById(99L));
    }

    @Test
    public void testDeleteBook() {
        bookService.deleteBook(1L);

        verify(bookRepository).deleteById(1L);
    }

    @Test
    public void testSearchBooks() {
        Book book = new Book();
        book.setTitle("Baltagul");

        when(bookRepository.findByTitleContainingIgnoreCase("balt")).thenReturn(List.of(book));

        List<Book> result = bookService.searchBooks("balt");

        assertEquals(1, result.size());
        assertEquals("Baltagul", result.getFirst().getTitle());
    }

    @Test
    public void testFindPaginated() {
        Book book = new Book();
        book.setTitle("Baltagul");
        Page<Book> page = new PageImpl<>(List.of(book));

        when(bookRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Book> result = bookService.findPaginated(1, 5, "title", "asc");

        assertEquals(1, result.getContent().size());
        verify(bookRepository).findAll(any(Pageable.class));
    }
}
