package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Role;
import com.facultate.biblioteca.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        LOGGER.info("Saving role with name={}", role.getName());
        Role savedRole = roleRepository.save(role);
        LOGGER.debug("Saved role with id={}", savedRole.getId());
        return savedRole;
    }

    public List<Role> getAllRoles() {
        LOGGER.debug("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        LOGGER.info("Fetched {} roles", roles.size());
        return roles;
    }

    public Role getRoleById(Long id) {
        LOGGER.debug("Fetching role with id={}", id);
        return roleRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Role with id={} was not found", id);
                    return new RuntimeException("Eroare: Rolul cu ID-ul " + id + " nu a fost gasit!");
                });
    }

    public void deleteRole(Long id) {
        LOGGER.info("Deleting role with id={}", id);
        if (!roleRepository.existsById(id)) {
            LOGGER.warn("Cannot delete role with id={} because it does not exist", id);
            throw new RuntimeException("Eroare la stergere: Rolul cu ID-ul " + id + " nu exista!");
        }
        roleRepository.deleteById(id);
        LOGGER.debug("Deleted role with id={}", id);
    }

    public Role updateRole(final Role role) {
        LOGGER.info("Updating role with id={}", role.getId());
        final var existingRole = roleRepository.findById(role.getId())
                .orElseThrow(() -> {
                    LOGGER.warn("Cannot update role with id={} because it does not exist", role.getId());
                    return new RuntimeException("Eroare: Rolul cu ID-ul " + role.getId() + " nu a fost gasit!");
                });

        existingRole.setName(role.getName());
        existingRole.setUsers(role.getUsers());
        Role updatedRole = roleRepository.save(existingRole);
        LOGGER.debug("Updated role with id={}", updatedRole.getId());
        return updatedRole;
    }
}
