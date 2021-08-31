package com.example.cmdmapi.repository;

import com.example.cmdmapi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {


}