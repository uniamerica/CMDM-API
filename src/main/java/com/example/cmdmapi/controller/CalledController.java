package com.example.cmdmapi.controller;


import com.example.cmdmapi.model.Called;
import com.example.cmdmapi.model.User;
import com.example.cmdmapi.repository.CalledRepository;
import com.example.cmdmapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("called")

public class CalledController {

    @Autowired
    private CalledRepository calledRepository;

    @GetMapping
    public List<Called> listItems() {
        return calledRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Called addItem (@RequestBody Called called) {
        return calledRepository.save(called);
    }
}