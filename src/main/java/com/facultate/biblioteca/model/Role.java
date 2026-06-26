package com.facultate.biblioteca.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele rolului este obligatoriu")
    private String name; // Aici vom salva "ROLE_USER" sau "ROLE_ADMIN"

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}