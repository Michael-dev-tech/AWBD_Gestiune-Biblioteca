package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Category;
import com.facultate.biblioteca.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eroare: Categoria cu ID-ul " + id + " nu a fost găsită!"));
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Eroare la ștergere: Categoria cu ID-ul " + id + " nu există!");
        }
        categoryRepository.deleteById(id);
    }
}