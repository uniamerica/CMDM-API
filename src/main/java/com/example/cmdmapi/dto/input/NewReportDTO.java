package com.example.cmdmapi.dto.input;

import com.example.cmdmapi.model.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewReportDTO {

    @NotBlank(message = "Title is Required")
    private String title;
    @NotBlank(message = "Description is Required")
    private String description;
    @NotBlank(message = "Depoiment is Required")
    private String depoiment;

    public Report toModel(){
        Report report = new Report();
        report.setTitle(getTitle());
        report.setDescription(getDescription());
        report.setDepoiment(getDepoiment());

        return report;
    }
}
