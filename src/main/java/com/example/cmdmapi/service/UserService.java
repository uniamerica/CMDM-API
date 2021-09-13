package com.example.cmdmapi.service;

import com.example.cmdmapi.dto.UserDTO;
import com.example.cmdmapi.dto.input.NewUserDTO;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.model.Role;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.repository.RoleRepository;
import com.example.cmdmapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO save(NewUserDTO newUserDTO) {
        newUserDTO.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        return new UserDTO(userRepository.save(newUserDTO.toModel()));
    }

    public UserDTO findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found User by ID:" + id));
        return new UserDTO(user);
    }

    public UserDTO update(long id, NewUserDTO newUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found User by ID:" + id));

        user.setName(newUserDTO.getName());
        user.setAddress(newUserDTO.getAddress());
        user.setUsername(newUserDTO.getUsername());
        user.setPhone(newUserDTO.getPhone());
        user.setEmail(newUserDTO.getEmail());
        user.setBirth(newUserDTO.getBirth());
        return new UserDTO(user);
    }

    public String deleteById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found User by ID:" + id));
        userRepository.deleteById(id);
        return "Deletado com Sucesso";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found ");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void addRoleToUser(Long userId, String roleName){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("jorge"));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
}
