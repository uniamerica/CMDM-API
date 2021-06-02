package com.example.cmdmapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Called {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer nChamado;
    private String local;
    private Time horario;
    private Integer timeEstimated;
    private Long id;


}