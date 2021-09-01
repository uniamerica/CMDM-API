//package com.example.cmdmapi.controller;
//
//import com.example.cmdmapi.auth.ApplicationUserService;
//import com.example.cmdmapi.dto.ClientDTO;
//import com.example.cmdmapi.dto.input.NewClientDTO;
//import com.example.cmdmapi.jwt.JwtTokenVerifier;
//import com.example.cmdmapi.jwt.JwtUsernameAndPasswordAuthenticationFilter;
//import com.example.cmdmapi.jwt.UsernameAndPasswordAuthenticationRequest;
//import com.example.cmdmapi.service.ClientService;
//import io.jsonwebtoken.Jwt;
//import org.h2.engine.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//@WebMvcTest(ClientController.class)
//class ClientControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ClientService clientService;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @MockBean
//    private ApplicationUserService applicationUserService;
//
//    @MockBean
//    private UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest;
//
//    private ClientController clientController;
//
//    @Test
//    void update() throws Exception {
//        NewClientDTO newClientDTO = NewClientDTO.builder()
//                .firstName("teste2")
//                .build();
//        ClientDTO clientDTO = new ClientDTO(newClientDTO.toModel());
//
//        when(clientService.update(1, newClientDTO)).thenReturn(clientDTO);
//
//        String url = "/clients/{id}";
//        mockMvc.perform(put(url, 1)).andExpect(status().isOk());
//    }
//
//}