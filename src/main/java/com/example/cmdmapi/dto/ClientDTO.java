package com.example.cmdmapi.dto;

import com.example.cmdmapi.model.Client;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDTO implements Serializable {
    private Long id;
    private String firstName;

    public ClientDTO(Client client){
        id = client.getId();
        firstName = client.getFirstName();
    }
}

