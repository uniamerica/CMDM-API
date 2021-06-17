package com.example.cmdmapi.repository;

import com.example.cmdmapi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}