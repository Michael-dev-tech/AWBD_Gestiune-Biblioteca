package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Category;
import com.facultate.biblioteca.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String viewCategoriesPage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Category> page = categoryService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Category> categories = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("categories", categories);

        return "categories";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping
    public String saveCategory(@Valid @ModelAttribute("category") Category category,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "add-category";
        }

        categoryService.saveCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "add-category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}