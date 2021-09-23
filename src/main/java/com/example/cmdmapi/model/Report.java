package com.example.cmdmapi.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReport;
    private String title;
    private String description;
    private String depoiment;


}