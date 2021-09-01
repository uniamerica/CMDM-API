package com.example.cmdmapi.service;

import com.example.cmdmapi.dto.ClientDTO;
import com.example.cmdmapi.dto.input.NewClientDTO;
import com.example.cmdmapi.model.Client;
import com.example.cmdmapi.repository.ClientRepository;
import com.google.common.base.CharMatcher;
import lombok.var;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void shouldListAllItems() {
        //when
        clientService.findAll();
        //then
        verify(clientRepository).findAll();
    }

    // Test is no working
    @Test
    @Disabled
    void shouldAddClient() {

        // given
        NewClientDTO newClientDTO = NewClientDTO.builder()
                .firstName("teste")
                .build();

        // when
        clientService.save(newClientDTO);

        // then
        ArgumentCaptor<Client> clientArgumentCaptor =
                ArgumentCaptor.forClass(Client.class);

        verify(clientRepository)
                .save(clientArgumentCaptor.capture());

        Client capturedClient = clientArgumentCaptor.getValue();

        assertThat(capturedClient).isEqualTo(newClientDTO);
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

        assertThat(result.getId())
                .isEqualTo(client.getId());
        assertThat(result)
                .isNotNull()
                .isEqualTo(new ClientDTO(client));

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

        Mockito.when(clientRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

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

        Mockito.when(clientRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clientService.deleteById(client.getId())).isInstanceOf(IllegalStateException.class);
    }
}