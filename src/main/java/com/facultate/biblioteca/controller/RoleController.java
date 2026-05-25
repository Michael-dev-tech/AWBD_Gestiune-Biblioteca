package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Role;
import com.facultate.biblioteca.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    @GetMapping("all")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping(params = "id")
    public Role getRoleById(@RequestParam final Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping
    public Role updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping
    public void deleteRole(@RequestParam final Long id) {
        roleService.deleteRole(id);
    }
}
