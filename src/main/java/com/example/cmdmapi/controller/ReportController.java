package com.example.cmdmapi.controller;

import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatos")
public class ReportController{

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping
    public List<Report> listaRelatos() {
        return reportRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Report cadastraRelatos(@RequestBody Report relatos) {
        return reportRepository.save(relatos);
    }
}