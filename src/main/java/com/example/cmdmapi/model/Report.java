package com.example.cmdmapi.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
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

    public Report(Long codigo, String relato_relato_relato, String teste_teste_teste) {
    }

    public Report() {

    }

    @Column(nullable = false)
    private boolean pendente;

}