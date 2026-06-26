package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Category;
import com.facultate.biblioteca.repository.CategoryRepository;
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
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void saveCategoryShouldReturnSavedCategory() {
        Category category = new Category();
        category.setName("Roman");

        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.saveCategory(category);

        assertEquals("Roman", result.getName());
        verify(categoryRepository).save(category);
    }

    @Test
    void getCategoryByIdShouldReturnCategory() {
        Category category = new Category();
        category.setName("Fictiune");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1L);

        assertEquals("Fictiune", result.getName());
    }

    @Test
    void getCategoryByIdShouldThrowExceptionWhenMissing() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.getCategoryById(99L));
    }

    @Test
    void findPaginatedShouldReturnCategoriesPage() {
        Category category = new Category();
        category.setName("Istorie");

        Page<Category> page = new PageImpl<>(List.of(category));

        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Category> result = categoryService.findPaginated(1, 5, "name", "asc");

        assertEquals(1, result.getContent().size());
        assertEquals("Istorie", result.getContent().get(0).getName());
        verify(categoryRepository).findAll(any(Pageable.class));
    }
}