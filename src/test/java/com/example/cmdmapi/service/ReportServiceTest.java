package com.example.cmdmapi.service;

import com.example.cmdmapi.TesteBase;
import com.example.cmdmapi.dto.ReportDTO;
import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @Test
    void ShouldFindreport() throws Exception {
        MvcResult mvcResult = createReport();
        Report report = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), Report.class);

        ReportDTO reportDTO = ReportDTO.builder()
                .id(report.getId())
                .depoiment("depoimento teste")
                .description("descricao teste")
                .title("titulo teste")
                .build();

        mockMvc.perform(
                        get("/reports")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reportDTO)))
                .andExpect(status().isOk())
                .andReturn();
    }

}

