package com.example.cmdmapi.controller;

import com.example.cmdmapi.model.Report;
import com.example.cmdmapi.repository.ReportRepository;
import com.example.cmdmapi.service.ReportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Client")
@RequestMapping("/relatos")
public class ReportController{


    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportService reportService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Report cadastrarRelatos(@RequestBody Report reports) {
        return reportService.cadastrarRelatos(reports);
    }

    @GetMapping
    public List<Report> listaRelatos() {
        return reportService.list();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Report> obterRelatos(@PathVariable Long codigo){
        Report report = this.reportService.obterRelatos(codigo);
        if (report == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }


    @PutMapping("report/{id}")
    public ResponseEntity update (@PathVariable("id") Long id,
                                  @RequestBody Report report){
        return reportRepository.findById(id).map(record ->{
           record.setDepoimento(report.getDepoimento());
           Report update = reportRepository.save(record);
           return ResponseEntity.ok().body(update);
        }).orElse(ResponseEntity.notFound().build());
    }



}