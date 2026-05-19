package com.facultate.biblioteca.repository;

import com.facultate.biblioteca.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name); // Ne trebuie pentru a găsi rolul "ROLE_USER" sau "ROLE_ADMIN"
}