package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        LOGGER.info("Saving book with title={}", book.getTitle());
        Book savedBook = bookRepository.save(book);
        LOGGER.debug("Saved book with id={}", savedBook.getId());
        return savedBook;
    }

    public List<Book> getAllBooks() {
        LOGGER.debug("Fetching all books");
        List<Book> books = bookRepository.findAll();
        LOGGER.info("Fetched {} books", books.size());
        return books;
    }

    public Book getBookById(Long id) {
        LOGGER.debug("Fetching book with id={}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Book with id={} was not found", id);
                    return new RuntimeException("Eroare: Cartea nu a fost gasita!");
                });
    }

    public void deleteBook(Long id) {
        LOGGER.info("Deleting book with id={}", id);
        bookRepository.deleteById(id);
        LOGGER.debug("Deleted book with id={}", id);
    }

    public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        LOGGER.debug("Fetching books page={}, size={}, sortField={}, sortDir={}", pageNo, pageSize, sortField, sortDir);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Book> page = bookRepository.findAll(pageable);
        LOGGER.info("Fetched page {} with {} books from {} total books", pageNo, page.getNumberOfElements(), page.getTotalElements());
        return page;
    }

    public List<Book> searchBooks(String keyword) {
        LOGGER.debug("Searching books with keyword={}", keyword);
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(keyword);
        LOGGER.info("Found {} books for keyword={}", books.size(), keyword);
        return books;
    }
}
