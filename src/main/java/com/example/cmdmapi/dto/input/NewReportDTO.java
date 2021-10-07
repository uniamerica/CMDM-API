package com.example.cmdmapi.dto.input;

import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "User id is required")
    private Long userId;
    @NotBlank (photo = "Photo is Required")
    private String photo;
    @NotBlank (video = "Video is Required")
    private String video;


    public Report toModel(){
        Report report = new Report();
        report.setTitle(getTitle());
        report.setDescription(getDescription());
        report.setDepoiment(getDepoiment());
        report.setUser(new User(userId));
        report.setPhoto(getPhoto());
        report.setVideo(getVideo());

        return report;
    }


}
