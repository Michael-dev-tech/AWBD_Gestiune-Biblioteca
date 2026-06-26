package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Author;
import com.facultate.biblioteca.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void saveAuthorShouldReturnSavedAuthor() {
        Author author = new Author();
        author.setName("Mihail Sadoveanu");

        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.saveAuthor(author);

        assertEquals("Mihail Sadoveanu", result.getName());
        verify(authorRepository).save(author);
    }

    @Test
    void getAuthorByIdShouldReturnAuthor() {
        Author author = new Author();
        author.setName("Ion Creanga");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorById(1L);

        assertEquals("Ion Creanga", result.getName());
    }

    @Test
    void getAuthorByIdShouldThrowExceptionWhenMissing() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authorService.getAuthorById(99L));
    }

    @Test
    void findPaginatedShouldReturnAuthorsPage() {
        Author author = new Author();
        author.setName("Test Autor");

        Page<Author> page = new PageImpl<>(List.of(author));

        when(authorRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Author> result = authorService.findPaginated(1, 5, "name", "asc");

        assertEquals(1, result.getContent().size());
        assertEquals("Test Autor", result.getContent().get(0).getName());
        verify(authorRepository).findAll(any(Pageable.class));
    }
}