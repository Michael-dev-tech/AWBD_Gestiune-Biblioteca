package com.facultate.biblioteca.repository;

import com.facultate.biblioteca.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}