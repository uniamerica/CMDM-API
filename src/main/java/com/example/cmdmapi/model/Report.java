package com.example.cmdmapi.model;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Report{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelatos;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String depoimento;

    @Column(nullable = false)
    private boolean pendente;

}