package com.facultate.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele este obligatoriu")
    private String fullName;

    @Email(message = "Email invalid")
    @NotBlank(message = "Email-ul este obligatoriu")
    private String email;
    private String phoneNumber;

    // Partea a doua a relației @OneToOne
    @OneToOne(mappedBy = "userProfile")
    private User user;

    // Constructori, Getteri și Setteri
    public UserProfile() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}