package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Category;
import com.facultate.biblioteca.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        LOGGER.info("Saving category with name={}", category.getName());
        Category savedCategory = categoryRepository.save(category);
        LOGGER.debug("Saved category with id={}", savedCategory.getId());
        return savedCategory;
    }

    public List<Category> getAllCategories() {
        LOGGER.debug("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        LOGGER.info("Fetched {} categories", categories.size());
        return categories;
    }

    public Category getCategoryById(Long id) {
        LOGGER.debug("Fetching category with id={}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Category with id={} was not found", id);
                    return new RuntimeException("Eroare: Categoria cu ID-ul " + id + " nu a fost gasita!");
                });
    }

    public void deleteCategory(Long id) {
        LOGGER.info("Deleting category with id={}", id);
        if (!categoryRepository.existsById(id)) {
            LOGGER.warn("Cannot delete category with id={} because it does not exist", id);
            throw new RuntimeException("Eroare la stergere: Categoria cu ID-ul " + id + " nu exista!");
        }
        categoryRepository.deleteById(id);
        LOGGER.debug("Deleted category with id={}", id);
    }

    public Category updateCategory(final Category category) {
        LOGGER.info("Updating category with id={}", category.getId());
        final var existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> {
                    LOGGER.warn("Cannot update category with id={} because it does not exist", category.getId());
                    return new RuntimeException("Eroare: Categoria cu ID-ul " + category.getId() + " nu a fost gasita!");
                });

        existingCategory.setName(category.getName());
        existingCategory.setBooks(category.getBooks());
        Category updatedCategory = categoryRepository.save(existingCategory);
        LOGGER.debug("Updated category with id={}", updatedCategory.getId());
        return updatedCategory;
    }
}
