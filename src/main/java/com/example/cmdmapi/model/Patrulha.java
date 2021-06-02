package com.example.cmdmapi.model;

import lombok.Data;

import javax.persistence.OneToMany;

@Data
public class Patrulha {

    private Integer idPatrulha;
    private String localizaçao;

    @OneToMany
    private Called chamado;


}