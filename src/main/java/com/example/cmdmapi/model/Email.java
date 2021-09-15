package com.example.cmdmapi.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String from;
    @Column(nullable = false)
    private String to;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String content;

}