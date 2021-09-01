package com.example.cmdmapi.service;


import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public Report cadastrarRelatos (Report report){
        System.out.println("Relato Enviado com Sucesso.");
        return reportRepository.save(report);
    }
    public List<Report> list(){
        return reportRepository.findAll();
    }

    public Report obterRelatos(Long codigo){

        if (codigo > 100){
            return null;
        }
        return new Report(
            codigo,
            "relato relato relato",
            "teste teste teste"
        );
    }




}
