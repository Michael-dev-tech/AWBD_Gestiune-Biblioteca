package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Role;
import com.facultate.biblioteca.repository.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eroare: Rolul cu ID-ul " + id + " nu a fost găsit!"));
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Eroare la ștergere: Rolul cu ID-ul " + id + " nu există!");
        }
        roleRepository.deleteById(id);
    }
}