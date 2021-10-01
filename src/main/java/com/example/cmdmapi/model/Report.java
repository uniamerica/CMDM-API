package com.example.cmdmapi.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String depoiment;

//    @Column(nullable = false)
//    private boolean pendente;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

}