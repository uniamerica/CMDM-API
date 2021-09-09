package com.example.cmdmapi.service;

import com.example.cmdmapi.dto.ClientDTO;
import com.example.cmdmapi.dto.input.NewClientDTO;
import com.example.cmdmapi.model.Client;
import com.example.cmdmapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

//teste kanban

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public ClientDTO save(NewClientDTO newClientDTO) {
        return new ClientDTO(clientRepository.save(newClientDTO.toModel()));
    }

    public ClientDTO findById(long id) {
        Client cliente = clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found Client by ID:" + id));
        return new ClientDTO(cliente);
    }

    @Transactional
    public ClientDTO update(long id, NewClientDTO newClientDTO) {
        Client cliente = clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found Client by ID:" + id));

        cliente.setFirstName(newClientDTO.getFirstName());
        return new ClientDTO(cliente);
    }

    public String deleteById(long id) {
        Client cliente = clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found Client by ID:" + id));
        clientRepository.deleteById(id);
        return "Deletado com Sucesso";
    }
}
