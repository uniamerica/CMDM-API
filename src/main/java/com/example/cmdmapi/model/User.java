package com.example.cmdmapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @ApiModelProperty(value = "Código do usuario")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(value = "Nome do usuario")
    private String name;
    private String username;
    private String password;
    private LocalDate birth;
    private String phone;
    private String email;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}
