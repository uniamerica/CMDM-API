package com.example.cmdmapi.integration;

import com.example.cmdmapi.dto.input.NewReportDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReportIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String url = "/reports";

    private MvcResult createUser() throws Exception {

        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("test")
                .email("joj")
                .password("joj")
                .username("joj")
                .build();

        String content = objectMapper.writeValueAsString(newUserDTO);
        String UserUrl = "/users";

        return mockMvc.perform(
                post(UserUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andReturn();
    }

    @Test
    @Order(1)
    void shouldGetAllReportsAsList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void shouldFindReportById() throws Exception {
        shouldAddReport();

        mockMvc.perform(
                MockMvcRequestBuilders.get(url, 1L)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void shouldAddReport() throws Exception {
        MvcResult mvcResult = createUser();

        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), User.class);

        NewReportDTO newReportDTO = NewReportDTO.builder()
                .title("Title Test")
                .description("test")
                .depoiment("test")
                .userId(user.getId())
                .build();

        String content = objectMapper.writeValueAsString(newReportDTO);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    void shouldAddReportReturnValidationExceptionIfNotValid() throws Exception {
        NewReportDTO newReportDTO = NewReportDTO.builder()
                .title("test")
                .build();

        String content = objectMapper.writeValueAsString(newReportDTO);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    void shouldUpdateReport() throws Exception {
        shouldAddReport();

        MvcResult mvcResult = createUser();

        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), User.class);

        NewReportDTO newReportDTO = NewReportDTO.builder()
                .title("Update Test")
                .description("test")
                .depoiment("test")
                .userId(user.getId())
                .build();

        String content = objectMapper.writeValueAsString(newReportDTO);

        mockMvc.perform(
                put(url + "/{id}", 1L)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void shouldUpdateReportReturnsExceptionIfNotValid() throws Exception {
        NewReportDTO newReportDTO = NewReportDTO.builder()
                .title("test")
                .build();

        String content = objectMapper.writeValueAsString(newReportDTO);

        mockMvc.perform(
                put(url + "/{id}", 1L)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    void shouldDeleteById() throws Exception {
        shouldAddReport();

        mockMvc.perform(
                MockMvcRequestBuilders.delete(url + "/{id}", 1L)
        ).andExpect(status().isOk());
    }
}
