package com.example.cmdmapi.service;

import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public Role save(Role role){
        if(!roleRepository.exists(Example.of(role))){
            return roleRepository.save(role);
        }
        return roleRepository.findByName(role.getName());
    }
}
