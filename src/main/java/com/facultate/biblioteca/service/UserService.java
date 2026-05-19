package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.User;
import com.facultate.biblioteca.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eroare: Utilizatorul cu ID-ul " + id + " nu a fost găsit!"));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Eroare la ștergere: Utilizatorul cu ID-ul " + id + " nu există!");
        }
        userRepository.deleteById(id);
    }
}