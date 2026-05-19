package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.UserProfile;
import com.facultate.biblioteca.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserProfileById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eroare: Profilul cu ID-ul " + id + " nu a fost găsit!"));
    }

    public void deleteUserProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new RuntimeException("Eroare la ștergere: Profilul cu ID-ul " + id + " nu există!");
        }
        userProfileRepository.deleteById(id);
    }
}