package com.ufc.pix;

import com.ufc.pix.controller.UserController;
import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.model.User;
import com.ufc.pix.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserTests {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;
    private String json;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testSaveUser_Success() throws Exception {
        json = """
            {
                "name": "Cristiano",
                "email": "cristianomendes@gmail.com",
                "password": "qwe123",
                "cpf": "12345678912",
                "birthDate": "1999-05-25"
            }
        """;

        when(userService.createUser(any(CreateUserDto.class))).thenReturn(new User());

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));
    }

    @Test
    public void testSaveUser_without_email_fail() throws Exception {
        json = """
        {
            "name": "Cristiano",
            "password": "qwe123",
            "cpf": "12345678912",
            "birthDate": "1999-05-25"
        }
    """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

}
