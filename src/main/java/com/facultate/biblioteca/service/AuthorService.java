package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Author;
import com.facultate.biblioteca.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        LOGGER.info("Saving author with name={}", author.getName());
        Author savedAuthor = authorRepository.save(author);
        LOGGER.debug("Saved author with id={}", savedAuthor.getId());
        return savedAuthor;
    }

    public List<Author> getAllAuthors() {
        LOGGER.debug("Fetching all authors");
        List<Author> authors = authorRepository.findAll();
        LOGGER.info("Fetched {} authors", authors.size());
        return authors;
    }

    public Author getAuthorById(Long id) {
        LOGGER.debug("Fetching author with id={}", id);
        return authorRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Author with id={} was not found", id);
                    return new RuntimeException("Eroare: Autorul cu ID-ul " + id + " nu a fost gasit!");
                });
    }

    public void deleteAuthor(Long id) {
        LOGGER.info("Deleting author with id={}", id);
        if (!authorRepository.existsById(id)) {
            LOGGER.warn("Cannot delete author with id={} because it does not exist", id);
            throw new RuntimeException("Eroare la stergere: Autorul cu ID-ul " + id + " nu exista!");
        }
        authorRepository.deleteById(id);
        LOGGER.debug("Deleted author with id={}", id);
    }

    public Author updateAuthor(final Author author) {
        LOGGER.info("Updating author with id={}", author.getId());
        final var existingAuthor = authorRepository.findById(author.getId())
                .orElseThrow(() -> {
                    LOGGER.warn("Cannot update author with id={} because it does not exist", author.getId());
                    return new RuntimeException("Eroare: Autorul cu ID-ul " + author.getId() + " nu a fost gasit!");
                });

        existingAuthor.setName(author.getName());
        existingAuthor.setBooks(author.getBooks());
        Author updatedAuthor = authorRepository.save(existingAuthor);
        LOGGER.debug("Updated author with id={}", updatedAuthor.getId());
        return updatedAuthor;
    }
}
