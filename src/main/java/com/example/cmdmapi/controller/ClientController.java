package com.example.cmdmapi.controller;

import com.example.cmdmapi.dto.ClientDTO;
import com.example.cmdmapi.dto.input.NewClientDTO;
import com.example.cmdmapi.model.Client;
import com.example.cmdmapi.repository.ClientRepository;
import com.example.cmdmapi.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@Api(value = "Client")
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Mostrar todos os usuários cadastrados")
    @GetMapping
    public List<ClientDTO> listItems() {
        return clientService.findAll();
    }

    @ApiOperation(value = "Cadastrar um novo usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO addItem (@RequestBody NewClientDTO newClientDTO) {
        return clientService.save(newClientDTO);
    }

//    @ApiOperation(value = "Burscar usuário por id")
//    @GetMapping(path = {"/{id}"})
//    public ResponseEntity findById(@PathVariable long id){
//        return clientRepository.findById(id)
//                .map(record -> ResponseEntity.ok().body(record))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @ApiOperation(value = "Altera usuário")
//    @PutMapping(value="/{id}")
//    public ResponseEntity update(@PathVariable("id") long id,
//                                 @RequestBody Client client) {
//        return clientRepository.findById(id)
//                .map(record -> {
//                    record.setFirstName(client.getFirstName());
//                    Client updated = clientRepository.save(record);
//                    return ResponseEntity.ok().body(updated);
//                }).orElse(ResponseEntity.notFound().build());
//    }
//
//    @ApiOperation(value = "Deletar usuário")
//    @DeleteMapping(path ={"/{id}"})
//    public ResponseEntity <?> delete(@PathVariable long id) {
//        return clientRepository.findById(id)
//                .map(record -> {
//                    clientRepository.deleteById(id);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
//    }
}
