package com.example.cmdmapi.model;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Relatos{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelatos;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String depoimento;

    @Column
    private String data;

    @Column
    @ManyToOne
    private User Usuario_IdUsuario;
}
