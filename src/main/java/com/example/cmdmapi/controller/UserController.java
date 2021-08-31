package com.example.cmdmapi.controller;

import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.service.RoleService;
import com.example.cmdmapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<User>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/roles").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }
    @PostMapping("{id}/role")
    public ResponseEntity<?> saveRole(@RequestBody Role role, @PathVariable long id) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/roles").toUriString());
        userService.addRoleToUser(id, role.getName());
        return ResponseEntity.created(uri).build();
    }

}
