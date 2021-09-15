package com.example.cmdmapi.model;
import lombok.Data;
import javax.persistence.*;
@Entity
@Data
public class Report{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReport;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String depoiment;

//    @Column(nullable = false)
//    private boolean pendente;

}