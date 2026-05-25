package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Category;
import com.facultate.biblioteca.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @GetMapping("all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(params = "id")
    public Category getCategoryById(@RequestParam final Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping
    public void deleteCategory(@RequestParam final Long id) {
        categoryService.deleteCategory(id);
    }
}
