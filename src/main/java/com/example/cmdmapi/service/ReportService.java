package com.example.cmdmapi.service;

import com.example.cmdmapi.dto.ReportDTO;
import com.example.cmdmapi.dto.input.NewReportDTO;
import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.repository.ReportRepository;
import com.example.cmdmapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService{
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public List<ReportDTO> findAll() {
        return reportRepository.findAll().stream().map(ReportDTO::new).collect(Collectors.toList());
    }

    public ReportDTO findById(long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found Report by ID:" + id));
        return new ReportDTO(report);
    }

    public ReportDTO save(NewReportDTO newReportDTO) {
        userRepository.findById(newReportDTO.getUserId()).orElseThrow(() -> new IllegalStateException("Not Found User by ID:" + newReportDTO.getUserId()));
        return new ReportDTO(reportRepository.save(newReportDTO.toModel()));
    }

    public ReportDTO update(long id, NewReportDTO newReportDTO) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found Report by ID:" + id));

        report.setTitle(newReportDTO.getTitle());
        report.setDescription(newReportDTO.getDescription());
        report.setDepoiment(newReportDTO.getDepoiment());
        User user = userRepository.findById(newReportDTO.getUserId()).orElseThrow(() -> new IllegalStateException("Not Found User by ID:" + newReportDTO.getUserId()));
        report.setUser(user);

        Report updated = reportRepository.save(report);
        return new ReportDTO(updated);
    }

    public String deleteById(long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found Report by ID:" + id));
        reportRepository.deleteById(id);
        return "Report was successfully deleted";
    }
}
