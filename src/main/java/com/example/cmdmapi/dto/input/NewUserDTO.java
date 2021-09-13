package com.example.cmdmapi.dto.input;

import com.example.cmdmapi.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewUserDTO {

    @NotBlank(message = "name is Required.")
    private String name;
    @NotBlank(message = "username is Required.")
    private String username;
    @NotBlank(message = "password is Required.")
    private String password;
    private LocalDate birth;
    private String phone;
    @NotBlank(message = "email is Required.")
    private String email;
    private String address;

    public User toModel(){
        User user = new User();
        user.setName(getName());
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setBirth(getBirth());
        user.setPhone(getPhone());
        user.setEmail(getEmail());
        user.setAddress(getAddress());
        return user;
    }
}
