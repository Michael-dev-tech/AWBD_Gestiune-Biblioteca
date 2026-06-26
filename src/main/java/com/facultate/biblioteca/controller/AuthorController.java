package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Author;
import com.facultate.biblioteca.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public String viewAuthorsPage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Author> page = authorService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Author> authors = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("authors", authors);

        return "authors";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "add-author";
    }

    @PostMapping
    public String saveAuthor(@Valid @ModelAttribute("author") Author author,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "add-author";
        }

        authorService.saveAuthor(author);
        return "redirect:/author";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        return "add-author";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }
}