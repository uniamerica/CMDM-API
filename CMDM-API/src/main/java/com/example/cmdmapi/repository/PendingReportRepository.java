package com.example.cmdmapi.repository;

import com.example.cmdmapi.model.PendingReport;
import com.example.cmdmapi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingReportRepository extends JpaRepository<PendingReport, Long> {

}