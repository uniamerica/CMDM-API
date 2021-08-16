package com.example.cmdmapi.controller;

import com.example.cmdmapi.model.PendingReport;
import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.repository.PendingReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pendente")
public class PendingReportController{

    @Autowired
    private PendingReportRepository pendingReportRepository;

    @GetMapping
    public List<PendingReport> listaRelatos() {
        return pendingReportRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PendingReport cadastraRelatos(@RequestBody PendingReport relatos) {
        return pendingReportRepository.save(relatos);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return pendingReportRepository.findById(id)
                .map(record -> {
                    pendingReportRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}