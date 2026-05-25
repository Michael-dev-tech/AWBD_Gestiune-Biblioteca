package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.UserProfile;
import com.facultate.biblioteca.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile saveUserProfile(UserProfile userProfile) {
        LOGGER.info("Saving user profile with email={}", userProfile.getEmail());
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        LOGGER.debug("Saved user profile with id={}", savedUserProfile.getId());
        return savedUserProfile;
    }

    public List<UserProfile> getAllUserProfiles() {
        LOGGER.debug("Fetching all user profiles");
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        LOGGER.info("Fetched {} user profiles", userProfiles.size());
        return userProfiles;
    }

    public UserProfile getUserProfileById(Long id) {
        LOGGER.debug("Fetching user profile with id={}", id);
        return userProfileRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("User profile with id={} was not found", id);
                    return new RuntimeException("Eroare: Profilul cu ID-ul " + id + " nu a fost gasit!");
                });
    }

    public void deleteUserProfile(Long id) {
        LOGGER.info("Deleting user profile with id={}", id);
        if (!userProfileRepository.existsById(id)) {
            LOGGER.warn("Cannot delete user profile with id={} because it does not exist", id);
            throw new RuntimeException("Eroare la stergere: Profilul cu ID-ul " + id + " nu exista!");
        }
        userProfileRepository.deleteById(id);
        LOGGER.debug("Deleted user profile with id={}", id);
    }

    public UserProfile updateUserProfile(final UserProfile userProfile) {
        LOGGER.info("Updating user profile with id={}", userProfile.getId());
        final var existingUserProfile = userProfileRepository.findById(userProfile.getId())
                .orElseThrow(() -> {
                    LOGGER.warn("Cannot update user profile with id={} because it does not exist", userProfile.getId());
                    return new RuntimeException("Eroare: Profilul cu ID-ul " + userProfile.getId() + " nu a fost gasit!");
                });

        existingUserProfile.setFullName(userProfile.getFullName());
        existingUserProfile.setEmail(userProfile.getEmail());
        existingUserProfile.setPhoneNumber(userProfile.getPhoneNumber());
        existingUserProfile.setUser(userProfile.getUser());
        UserProfile updatedUserProfile = userProfileRepository.save(existingUserProfile);
        LOGGER.debug("Updated user profile with id={}", updatedUserProfile.getId());
        return updatedUserProfile;
    }
}
