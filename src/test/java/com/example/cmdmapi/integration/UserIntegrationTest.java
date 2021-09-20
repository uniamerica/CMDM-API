package com.example.cmdmapi.integration;

import com.example.cmdmapi.controller.UserController;
import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.service.RoleService;
import com.example.cmdmapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@RunWith(SpringJUnit4ClassRunner.class)
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
    void listItems() throws Exception {
        String url = "/users";
        mockMvc.perform(
                get(url)
        ).andExpect(status().isOk());
        verify(userService).findAll();
    }

    @Test
    void addItem() throws Exception {
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
        ).andExpect(status().isCreated());
        verify(userService).save(newUserDTO);
    }

    @Test
    void update() throws Exception {
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
        ).andExpect(status().isOk());
        verify(userService).update(1L, newUserDTO);
    }

    @Test
    void findById() throws Exception {
        String url = "/users/{id}";
        mockMvc.perform(
                get(url, 1L)
        ).andExpect(status().isOk());
        verify(userService).findById(1L);
    }

    @Test
    void deleteById() throws Exception {
        String url = "/users/{id}";
        mockMvc.perform(
                delete(url, 1L)
        ).andExpect(status().isOk());

        verify(userService).deleteById(1L);
    }

    @Test
    void saveRole() throws Exception {
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
    void addRoleToUser() throws Exception {
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