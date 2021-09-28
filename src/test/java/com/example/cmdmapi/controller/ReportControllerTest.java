package com.example.cmdmapi.controller;

import com.example.cmdmapi.dto.ReportDTO;
import com.example.cmdmapi.dto.input.NewReportDTO;
import com.example.cmdmapi.service.ReportService;
import com.example.cmdmapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
@ContextConfiguration
@WithMockUser
@AutoConfigureTestDatabase
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    @Test
    void shouldGetAllReports() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/reports");
        ResultActions result = mockMvc.perform(request).andExpect(status().isOk());
        verify(reportService).findAll();
        assertThat(result).isNotNull();
    }

    @Test
    void shouldAddReport() throws Exception{
        NewReportDTO newReportDTO = NewReportDTO.builder()
                .title("Titulo")
                .description("descrição")
                .depoiment("depoimento")
                .build();

        String report = new ObjectMapper().writeValueAsString(newReportDTO);

        String url = "/reports";
        ResultActions result = mockMvc.perform(post(url)
                .content(report)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
        verify(reportService).save(newReportDTO);
    }

    @Test
    void shouldFindReportById() throws Exception{
        ReportDTO reportDTO = ReportDTO.builder()
                .id(1L)
                .title("teste")
                .description("teset description")
                .depoiment("teste depoiment")
                .build();

        when(reportService.findById(1L)).thenReturn(reportDTO);

        String url = "/reports/{id}";
        mockMvc.perform(
                get(url, 1L)
        ).andExpect(status().isFound());
    }

    @Test
    void shouldUpdateReportById() throws Exception {
        NewReportDTO newReportDTO = NewReportDTO.builder()
                .title("update test")
                .description("update description")
                .depoiment("update depoiment")
                .build();
        ReportDTO reportDTO = ReportDTO.builder()
                .id(1L)
                .title("teste")
                .description("description")
                .depoiment("depoiment")
                .build();

        String json = new ObjectMapper().writeValueAsString(newReportDTO);

        when(reportService.update(1, newReportDTO)).thenReturn(reportDTO);

        String url = "/reports/{id}";
        mockMvc.perform(
                put(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        ReportDTO reportDTO = ReportDTO.builder()
                .id(1L)
                .title("delete test")
                .description("description delete")
                .depoiment("depoiment delete")
                .build();

        when(reportService.deleteById(1L)).thenReturn("Successfull deleted");

        String url = "/reports/{id}";
        mockMvc.perform(
                delete(url, 1L)
        ).andExpect(status().isOk());
    }
}