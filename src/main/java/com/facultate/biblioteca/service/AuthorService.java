package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Author;
import com.facultate.biblioteca.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eroare: Autorul cu ID-ul " + id + " nu a fost găsit!"));
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Eroare la ștergere: Autorul cu ID-ul " + id + " nu există!");
        }
        authorRepository.deleteById(id);
    }
}