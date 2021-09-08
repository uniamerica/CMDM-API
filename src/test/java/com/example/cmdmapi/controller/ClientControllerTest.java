package com.example.cmdmapi.controller;

import com.example.cmdmapi.dto.ClientDTO;
import com.example.cmdmapi.dto.input.NewClientDTO;
import com.example.cmdmapi.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private ClientController clientController;

    @Test
    void update() throws Exception {
        NewClientDTO newClientDTO = NewClientDTO.builder()
                .firstName("teste2")
                .build();
        ClientDTO clientDTO = new ClientDTO(newClientDTO.toModel());

        var json = new ObjectMapper().writeValueAsString(newClientDTO);
        System.out.println(json);

        when(clientService.update(1, newClientDTO)).thenReturn(clientDTO);

        String url = "/clients/{id}";
        mockMvc.perform(
                put(url, 1)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}