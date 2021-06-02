package com.example.cmdmapi.controller;
import com.example.cmdmapi.model.Relatos;
import com.example.cmdmapi.repository.RelatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Relatos")
public class RelatosController{

    @Autowired
    private RelatosRepository repository;

    @GetMapping
    public List<Relatos> listaRelatos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Relatos cadastraRelatos(@RequestBody Relatos relatos) {
        return repository.save(relatos);
    }
}
