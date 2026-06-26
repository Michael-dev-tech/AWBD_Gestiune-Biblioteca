package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.facultate.biblioteca.service.AuthorService;
import com.facultate.biblioteca.service.CategoryService;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookController(BookService bookService,
                          AuthorService authorService,
                          CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    // Rutele pentru Paginare și Sortare
    @GetMapping
    public String viewHomePage(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5; // Arătăm maxim 5 cărți pe o pagină
        Page<Book> page = bookService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Book> listBooks = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("books", listBooks);

        return "books";
    }

    // NOU: Funcționalitatea pentru bara de căutare
    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("books", bookService.searchBooks(keyword));
        model.addAttribute("keyword", keyword);
        return "books"; // Refolosim tabelul existent pentru a afișa rezultatele
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-book";
    }

    @PostMapping
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "add-book";
        }

        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}