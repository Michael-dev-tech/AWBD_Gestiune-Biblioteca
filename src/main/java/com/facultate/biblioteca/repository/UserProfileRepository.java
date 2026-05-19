package com.facultate.biblioteca.repository;

import com.facultate.biblioteca.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}