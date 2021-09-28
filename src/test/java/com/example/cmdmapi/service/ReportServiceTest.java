package com.example.cmdmapi.service;

import com.example.cmdmapi.dto.ReportDTO;
import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewReportDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.repository.ReportRepository;
import com.example.cmdmapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private UserRepository userRepository;

    ReportService reportService;

    @BeforeEach
    void setUp(){
        reportService = new ReportService(reportRepository, userRepository);
    }

    @Test
    void ShouldAddReport(){
        NewReportDTO newReportDTO = NewReportDTO.builder()
                .depoiment("depoimento")
                .build();
        Report report = Report.builder()
                .depoiment("depoimento")
                .build();

        Mockito.doReturn(report).when(reportRepository).save(any(Report.class));

        var result = reportService.save(newReportDTO);

        assertThat(result).isNotNull();
        assertThat(result.getDepoiment()).isEqualTo(newReportDTO.getDepoiment());

    }
    @Test
    void ShouldFindReportById(){
        ReportDTO reportDTO = ReportDTO.builder().id(1L).depoiment("depoimento").build();
        Report report = Report.builder().idReport(1L).depoiment("depoimento").build();

        Mockito.when(reportRepository.findById(reportDTO.getId())).thenReturn(Optional.of(report));

        var result=reportService.findById(report.getIdReport());
        assertThat(result).isEqualTo(reportDTO);

    }
    @Test
    void ShouldUpdateReport(){
        NewReportDTO newReportDTO = NewReportDTO.builder().depoiment("depoimento2").build();
        Report report = Report.builder().idReport(1L).depoiment("depoimento").build();

        Mockito.when(reportRepository.findById(report.getIdReport())).thenReturn(Optional.of(report));

        var result = reportService.update(report.getIdReport(), newReportDTO);

        assertThat(result).isNotNull().isEqualTo(new ReportDTO(report));
        assertThat(result.getId()).isEqualTo(report.getIdReport());
    }
    @Test
    void shouldUpdateReportReturnsExceptionIfNotFound(){
        NewReportDTO newReportDTO = NewReportDTO.builder().depoiment("errado").build();
        Report report = Report.builder().idReport(1L).depoiment("depoiment").build();

        Mockito.when(reportRepository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> reportService.update(report.getIdReport(), newReportDTO)).isInstanceOf(IllegalStateException.class);
    }
    @Test
    void shouldDeleteById() {
        Report report = Report.builder()
                .idReport(1L)
                .depoiment("teste")
                .build();

        Mockito.when(reportRepository.findById(report.getIdReport())).thenReturn(Optional.of(report));

        var result = reportService.deleteById(report.getIdReport());

        assertThat(result).isEqualTo("Report was successfully deleted");
    }
    @Test
    void shouldDeleteByIdReturnsThrowsException() {
        Report report = Report.builder()
                .idReport(1L)
                .depoiment("teste")
                .build();

        Mockito.when(reportRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reportService.deleteById(report.getIdReport())).isInstanceOf(IllegalStateException.class);
    }
}

