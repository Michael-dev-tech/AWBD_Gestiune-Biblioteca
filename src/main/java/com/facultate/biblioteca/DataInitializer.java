package com.facultate.biblioteca;

import com.facultate.biblioteca.model.Book;
import com.facultate.biblioteca.model.Role;
import com.facultate.biblioteca.model.User;
import com.facultate.biblioteca.repository.BookRepository;
import com.facultate.biblioteca.repository.RoleRepository;
import com.facultate.biblioteca.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(BookRepository bookRepository,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Role adminRole = roleRepository.findAll().stream()
                .filter(role -> "ROLE_ADMIN".equals(role.getName()))
                .findFirst()
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_ADMIN");
                    return roleRepository.save(role);
                });

        Role userRole = roleRepository.findAll().stream()
                .filter(role -> "ROLE_USER".equals(role.getName()))
                .findFirst()
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_USER");
                    return roleRepository.save(role);
                });

        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(List.of(adminRole));
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(List.of(userRole));
            userRepository.save(user);
        }

        if (bookRepository.count() == 0) {
            Book b1 = new Book();
            b1.setTitle("Baltagul");
            b1.setIsbn("978-606-123");
            bookRepository.save(b1);

            Book b2 = new Book();
            b2.setTitle("Micul Print");
            b2.setIsbn("978-015-456");
            bookRepository.save(b2);
        }
    }
}