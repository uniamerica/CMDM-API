package com.example.cmdmapi.controller;

import com.example.cmdmapi.model.User;
import com.example.cmdmapi.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ApiOperation(value = "Burscar usuário por id")
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return userRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody User user) {
        return userRepository.findById(id)
                .map(record -> {
                    record.setFirstName(user.getFirstName());
                    record.setEmail(user.getEmail());
                    record.setPhone(user.getPhone());
                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
