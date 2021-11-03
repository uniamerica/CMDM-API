package com.example.cmdmapi.service;

import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public List<Role> saveAll(List<Role> roles){
        List<Role> rolesToSave = new ArrayList<>();

        for (Role role : roles){
            if(!roleRepository.findByName(role.getName()).isPresent()){
                rolesToSave.add(role);
            }
        }
        return roleRepository.saveAll(rolesToSave);
    }
}
