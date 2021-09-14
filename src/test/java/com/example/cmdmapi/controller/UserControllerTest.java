package com.example.cmdmapi.controller;

import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private UserController userController;

    @Test
    void update() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();
        UserDTO userDTO = new UserDTO(newUserDTO.toModel());

        var json = new ObjectMapper().writeValueAsString(newUserDTO);
        System.out.println(json);

        when(userService.update(1, newUserDTO)).thenReturn(userDTO);

        String url = "/clients/{id}";
        mockMvc.perform(
                put(url, 1)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}