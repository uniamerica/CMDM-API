package com.example.cmdmapi.controller;

import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.filter.CustomAuthenticationFilter;
import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.service.RoleService;
import com.example.cmdmapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.test.context.support.WithMockUser;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WithMockUser
class UserControllerTest {

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
        List<UserDTO> users = new ArrayList<UserDTO>();
        when(userService.findAll()).thenReturn(users);

        String url = "/users";
        mockMvc.perform(
                get(url)
        ).andExpect(status().isOk());
    }

    @Test
    void addItem() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();
        UserDTO userDTO = UserDTO.builder()
                .name("teste2")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        when(userService.save(newUserDTO)).thenReturn(userDTO);

        String url = "/users";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .name("teste")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        when(userService.update(1, newUserDTO)).thenReturn(userDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .name("teste")
                .build();

        when(userService.findById(1L)).thenReturn(userDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                get(url, 1L)
        ).andExpect(status().isOk());
    }

}