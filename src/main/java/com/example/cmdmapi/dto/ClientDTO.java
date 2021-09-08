package com.example.cmdmapi.dto;

import com.example.cmdmapi.model.Client;
import junit.runner.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable {
    private Long id;
    private String firstName;

    public ClientDTO(Client client){
        id = client.getId();
        firstName = client.getFirstName();
    }
}

