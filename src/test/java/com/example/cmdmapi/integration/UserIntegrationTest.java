package com.example.cmdmapi.integration;

import com.example.cmdmapi.controller.UserController;
import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.service.RoleService;
import com.example.cmdmapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ContextConfiguration
@WithMockUser
@AutoConfigureTestDatabase
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private RoleService roleService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private UserController userController;

    @Test
    void shouldGetAllUsersAsList() throws Exception {
        String url = "/users";
        mockMvc.perform(
                get(url)
        ).andExpect(status().isOk());
        verify(userService).findAll();
    }

    @Test
    void shouldAddUser() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .email("joj")
                .password("joj")
                .username("joj")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
        verify(userService).save(newUserDTO);
    }

    @Test
    void shouldAddUserReturnValidationExceptionIfNotValid() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
        verify(userService, never()).save(newUserDTO);
    }

    @Test
    void shouldUpdateUser() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .email("joj")
                .password("joj")
                .username("joj")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(userService).update(1L, newUserDTO);
    }

    @Test
    void shouldUpdateUserReturnsExceptionIfNotValid() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
        verify(userService, never()).update(1L, newUserDTO);
    }

    @Ignore("Teste nao funciona com MOCK")
    @Test
    void shouldUpdateUserReturnsExceptionIfNotFound() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .email("joj")
                .password("joj")
                .username("joj")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, 5000L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
        verify(userService, never()).update(5000L, newUserDTO);
    }


    @Test
    void shouldFindUserById() throws Exception {
        String url = "/users/{id}";
        mockMvc.perform(
                get(url, 1L)
        ).andExpect(status().isOk());
        verify(userService).findById(1L);
    }

    @Test
    void shouldDeleteById() throws Exception {
        String url = "/users/{id}";
        mockMvc.perform(
                delete(url, 1L)
        ).andExpect(status().isOk());

        verify(userService).deleteById(1L);
    }

    @Test
    void shouldAddRole() throws Exception {
        Role role = Role.builder()
                .name("admin")
                .build();

        var json = new ObjectMapper().writeValueAsString(role);

        String url = "/users/roles";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        verify(roleService).save(role);
    }

    @Test
    void shouldAddRoleToUser() throws Exception {
        Role role = Role.builder()
                .name("admin")
                .build();

        var json = new ObjectMapper().writeValueAsString(role);

        String url = "/users/{id}/role";
        mockMvc.perform(
                post(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        verify(userService).addRoleToUser(1L, role.getName());
    }
}