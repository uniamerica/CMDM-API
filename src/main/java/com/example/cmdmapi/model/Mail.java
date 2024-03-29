package com.example.cmdmapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMail;
    private String from;
    private String to;
    private String subject;
    private String content;



}