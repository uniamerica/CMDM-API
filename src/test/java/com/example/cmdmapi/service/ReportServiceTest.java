package com.example.cmdmapi.service;

import com.example.cmdmapi.TesteBase;
import com.example.cmdmapi.dto.ReportDTO;
import com.example.cmdmapi.dto.input.NewReportDTO;
import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;

public class ReportServiceTest extends TesteBase {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void ShouldAddReport() throws Exception {
        MvcResult mvcResult = createReport();
        Report report = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), Report.class);

        ReportDTO reportDTO = ReportDTO.builder()
                .id(report.getId())
                .depoiment("depoimento teste")
                .description("descricao teste")
                .title("titulo teste")
                .build();

        mockMvc.perform(
                        post("/reports")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reportDTO)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    private MvcResult createReport() throws Exception {
        Report report = Report.builder()
                .depoiment("depoimento teste")
                .description("descricao teste")
                .title("titulo teste")
                .build();

        return mockMvc.perform(
                        post("/reports")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(report)))
                .andReturn();
    }

}

