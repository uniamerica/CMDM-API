package com.example.cmdmapi.model;

import lombok.Data;

import javax.persistence.*;
//import java.util.Date;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

//    @Column(nullable = false)
//    private String lastName;
//
//    @Column(nullable = false)
//    private Date birth;
//
//    @Column(nullable = false)
//    private String phone;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String address;


    public Client(String firstName) {
        this.firstName = firstName;
    }

    public Client() {

    }
}
