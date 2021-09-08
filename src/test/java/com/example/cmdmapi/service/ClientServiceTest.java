package com.example.cmdmapi.service;

import com.example.cmdmapi.dto.ClientDTO;
import com.example.cmdmapi.dto.input.NewClientDTO;
import com.example.cmdmapi.model.Client;
import com.example.cmdmapi.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientRepository);
    }

    @Test
    void shouldAddClient() {

        NewClientDTO newClientDTO = NewClientDTO.builder()
                .firstName("teste")
                .build();
        Client client = Client.builder()
                .firstName("teste")
                .build();

//        Mockito.when(clientRepository.save(client)).thenReturn(client);
        Mockito.doReturn(client).when(clientRepository).save(any(Client.class));

        var result = clientService.save(newClientDTO);

        assertThat(result)
                .isNotNull();
        assertThat(result.getFirstName())
                .isEqualTo(newClientDTO.getFirstName());
    }

    @Test
    void shouldFindClientById() {
        ClientDTO clientdto = ClientDTO.builder()
                .id(1L)
                .firstName("teste")
                .build();
        Client client = Client.builder()
                .id(1L)
                .firstName("teste")
                .build();

        Mockito.when(clientRepository.findById(clientdto.getId())).thenReturn(Optional.of(client));

        var result = clientService.findById(client.getId());

        assertThat(result).isEqualTo(clientdto);
    }


    @Test
    void shouldUpdateClient() {
        NewClientDTO newClientDTO = NewClientDTO.builder()
                .firstName("teste2")
                .build();
        Client client = Client.builder()
                .id(1L)
                .firstName("teste")
                .build();

        Mockito.when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        var result = clientService.update(client.getId(), newClientDTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(new ClientDTO(client));
        assertThat(result.getId())
                .isEqualTo(client.getId());

    }

    @Test
    void shouldUpdateClientReturnsExceptionIfNotFound(){
        NewClientDTO newClientDTO = NewClientDTO.builder()
                .firstName("teste2")
                .build();
        Client client = Client.builder()
                .id(1L)
                .firstName("teste")
                .build();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clientService.update(client.getId(), newClientDTO)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldDeleteById() {
        Client client = Client.builder()
                .id(1L)
                .firstName("teste")
                .build();

        Mockito.when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        var result = clientService.deleteById(client.getId());

        assertThat(result)
                .isEqualTo("Deletado com Sucesso");
    }

    @Test
    void shouldDeleteByIdReturnsThrowsException() {
        Client client = Client.builder()
                .id(1L)
                .firstName("teste")
                .build();

        Mockito.when(clientRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clientService.deleteById(client.getId())).isInstanceOf(IllegalStateException.class);
    }
}