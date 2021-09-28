package com.example.cmdmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    @JsonIgnore
    private String password;
    private LocalDate birth;
    private String phone;
    private String email;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private List<Report> reports = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }
}
