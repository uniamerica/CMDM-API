package com.example.cmdmapi.service;

import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.repository.RoleRepository;
import com.example.cmdmapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;

    private RoleService roleService;

    @BeforeEach
    void setUp() {
        roleService = new RoleService(roleRepository);
    }


    @Test
    void shouldAddRole(){
        Role role = Role.builder()
                .name("admin")
                .build();

        Mockito.when(roleRepository.save(role)).thenReturn(role);

        var result = roleService.save(role);

        assertThat(result)
                .isNotNull();
        assertThat(result.getName())
                .isEqualTo(role.getName());
    }
}