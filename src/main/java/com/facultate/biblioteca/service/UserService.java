package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.User;
import com.facultate.biblioteca.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        LOGGER.info("Saving user with username={}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        LOGGER.debug("Saved user with id={}", savedUser.getId());
        return savedUser;
    }

    public List<User> getAllUsers() {
        LOGGER.debug("Fetching all users");
        List<User> users = userRepository.findAll();
        LOGGER.info("Fetched {} users", users.size());
        return users;
    }

    public User getUserById(Long id) {
        LOGGER.debug("Fetching user with id={}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("User with id={} was not found", id);
                    return new RuntimeException("Eroare: Utilizatorul cu ID-ul " + id + " nu a fost gasit!");
                });
    }

    public void deleteUser(Long id) {
        LOGGER.info("Deleting user with id={}", id);
        if (!userRepository.existsById(id)) {
            LOGGER.warn("Cannot delete user with id={} because it does not exist", id);
            throw new RuntimeException("Eroare la stergere: Utilizatorul cu ID-ul " + id + " nu exista!");
        }
        userRepository.deleteById(id);
        LOGGER.debug("Deleted user with id={}", id);
    }

    public User updateUser(final User user) {
        LOGGER.info("Updating user with id={}", user.getId());
        final var existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> {
                    LOGGER.warn("Cannot update user with id={} because it does not exist", user.getId());
                    return new RuntimeException("Eroare: Utilizatorul cu ID-ul " + user.getId() + " nu a fost gasit!");
                });

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setUserProfile(user.getUserProfile());
        existingUser.setLoans(user.getLoans());
        existingUser.setRoles(user.getRoles());
        User updatedUser = userRepository.save(existingUser);
        LOGGER.debug("Updated user with id={}", updatedUser.getId());
        return updatedUser;
    }
}
