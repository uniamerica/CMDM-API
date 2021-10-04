package com.example.cmdmapi;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import com.example.cmdmapi.security.SecurityConfig;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import({
        SecurityConfig.class
})
public abstract class TesteBase {

}