package com.example.cmdmapi.config;

import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.repository.RoleRepository;
import com.example.cmdmapi.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RolesConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleService service){
        List<Role> roles = new ArrayList<>();
        Role admin = new Role("Admin");
        roles.add(admin);
        Role user = new Role("User");
        roles.add(user);

        return args -> service.saveAll(roles);
    }
}