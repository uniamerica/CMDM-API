package com.example.cmdmapi.controller.management;

import com.example.cmdmapi.model.Client;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/clients")
public class ClientManagementController {

    private static final List<Client> CLIENTS = Arrays.asList(
            new Client("Ana"),
            new Client("Larissa")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Client> getAllClients() {
        return CLIENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('client:write')")
    public void registerNewClient(@RequestBody Client client) {
        System.out.println(client);
    }

    @DeleteMapping(path = "{firstName}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteClient(@PathVariable("firstName") String firstName) {
        System.out.println(firstName);
    }


}
