package com.example.cmdmapi.dto.input;

import com.example.cmdmapi.model.Client;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewClientDTO {

    @NotBlank(message = "FirstName is Required.")
    private String firstName;

    public Client toModel(){
        Client client = new Client();
        client.setFirstName(firstName);
        return client;
    }
}
