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
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> listItems() {
        return clientService.findAll();
    }

    @ApiOperation(value = "Cadastrar um novo usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO addItem (@RequestBody NewClientDTO newClientDTO) {
        return clientService.save(newClientDTO);
    }

    @ApiOperation(value = "Buscar usuário por id")
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.FOUND)
    public ClientDTO findById(@PathVariable long id){
        return clientService.findById(id);
    }

    @ApiOperation(value = "Altera usuário baseado no ID")
    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO update(@PathVariable("id") long id,
                                 @RequestBody NewClientDTO newClientDTO) {
        return clientService.update(id, newClientDTO);
    }

    @ApiOperation(value = "Deletar usuário")
    @DeleteMapping(path ={"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable long id) {
        return clientService.deleteById(id);
    }
}
