package com.example.cmdmapi.controller;

import com.example.cmdmapi.model.User;
import com.example.cmdmapi.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "User")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Mostrar todos os usuários cadastrados")
    @GetMapping
    public List<User> listItems() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Cadastrar um novo usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addItem (@RequestBody User user) {
        return userRepository.save(user);
    }
}
