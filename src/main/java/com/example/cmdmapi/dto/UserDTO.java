package com.example.cmdmapi.dto;

import com.example.cmdmapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String name;
    private String username;
    private LocalDate birth;
    private String phone;
    private String email;

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        username = user.getUsername();
        birth = user.getBirth();
        phone = user.getPhone();
        email = user.getEmail();
    }
}

