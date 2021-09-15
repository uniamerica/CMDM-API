package com.example.cmdmapi.controller;

import com.example.cmdmapi.dto.ReportDTO;
import com.example.cmdmapi.dto.input.NewReportDTO;
import com.example.cmdmapi.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Reports")
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController{

    private final ReportService reportService;

    @ApiOperation(value = "List all Reports")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportDTO> listItems() {
        return reportService.findAll();
    }

    @ApiOperation(value = "Create new Report")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReportDTO addItem(@RequestBody NewReportDTO newReportDTO) {
        return reportService.save(newReportDTO);
    }

    @ApiOperation(value = "Find Report by ID")
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.FOUND)
    public ReportDTO findById(@PathVariable long id){
        return reportService.findById(id);
    }

    @ApiOperation(value = "Change Report by ID")
    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportDTO update(@PathVariable("id") long id, @RequestBody NewReportDTO newReportDTO) {
        return reportService.update(id, newReportDTO);
    }

    @ApiOperation(value = "Delete Report")
    @DeleteMapping(path ={"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable long id) {
        return reportService.deleteById(id);
    }
}