package com.example.cmdmapi.dto;

import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String depoiment;
    private User user;

    public ReportDTO(Report report) {
        id = report.getId();
        title = report.getTitle();
        description = report.getDescription();
        depoiment = report.getDepoiment();
        user = report.getUser();
    }
}
