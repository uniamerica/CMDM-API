package com.example.cmdmapi.controller;

import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.service.RoleService;
import com.example.cmdmapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Mostrar todos os usuários cadastrados"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
})
@RestController
@Api(value = "Users")
@RequestMapping("/users")

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @ApiOperation(value = "Mostrar todos os usuários cadastrados")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> listItems() {
        return userService.findAll();
    }

    @ApiOperation(value = "Cadastrar um novo usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addItem (@RequestBody NewUserDTO newUserDTO) {
        return userService.save(newUserDTO);
    }

    @ApiOperation(value = "Buscar usuário por id")
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO findById(@PathVariable long id){
        return userService.findById(id);
    }

    @ApiOperation(value = "Altera usuário baseado no ID")
    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable("id") long id,
                          @RequestBody NewUserDTO newUserDTO) {
        return userService.update(id, newUserDTO);
    }

    @ApiOperation(value = "Deletar usuário")
    @DeleteMapping(path ={"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable long id) {
        return userService.deleteById(id);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/roles").toUriString());
        return ResponseEntity.created(uri).body(roleService.save(role));
    }
    @PostMapping("{id}/role")
    public ResponseEntity<?> saveRole(@RequestBody Role role, @PathVariable long id) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/roles").toUriString());
        userService.addRoleToUser(id, role.getName());
        return ResponseEntity.created(uri).build();
    }
}
