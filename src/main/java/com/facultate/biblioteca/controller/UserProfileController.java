package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.UserProfile;
import com.facultate.biblioteca.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("userprofile")
public class UserProfileController {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private final UserProfileService userProfileService;

    @GetMapping("all")
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping(params = "id")
    public UserProfile getUserProfileById(@RequestParam final Long id) {
        return userProfileService.getUserProfileById(id);
    }

    @PostMapping
    public UserProfile saveUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.saveUserProfile(userProfile);
    }

    @PutMapping
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.updateUserProfile(userProfile);
    }

    @DeleteMapping
    public void deleteUserProfile(@RequestParam final Long id) {
        userProfileService.deleteUserProfile(id);
    }
}
