package com.example.cmdmapi.service;

import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.exceptions.NotFoundException;
import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.repository.RoleRepository;
import com.example.cmdmapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder, roleRepository);
    }

    @Test
    void shouldAddUser() {

        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste")
                .build();
        User user = User.builder()
                .name("teste")
                .build();

//        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.doReturn(user).when(userRepository).save(any(User.class));

        var result = userService.save(newUserDTO);

        assertThat(result)
                .isNotNull();
        assertThat(result.getName())
                .isEqualTo(newUserDTO.getName());
    }

    @Test
    void shouldFindUserById() {
        UserDTO userdto = UserDTO.builder()
                .id(1L)
                .name("teste")
                .build();
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(userdto.getId())).thenReturn(Optional.of(user));

        var result = userService.findById(user.getId());

        assertThat(result).isEqualTo(userdto);
    }


    @Test
    void shouldUpdateUser() {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var result = userService.update(user.getId(), newUserDTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(new UserDTO(user));
        assertThat(result.getId())
                .isEqualTo(user.getId());

    }

    @Test
    void shouldUpdateUserReturnsExceptionIfNotFound(){
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.update(user.getId(), newUserDTO)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldDeleteById() {
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var result = userService.deleteById(user.getId());

        assertThat(result)
                .isEqualTo("Deletado com Sucesso");
    }

    @Test
    void shouldDeleteByIdReturnsThrowsException() {
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteById(user.getId())).isInstanceOf(NotFoundException.class);
    }
}