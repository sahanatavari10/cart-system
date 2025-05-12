package com.ecommerce;

import com.ecommerce.user.User;
import com.ecommerce.user.UserController;
import com.ecommerce.user.UserDTO;
import com.ecommerce.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    void shouldRegisterUser() {
        UserDTO userDTO = new UserDTO("testuser", "test@example.com", "password");
        User user = User.builder()
                .userName(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        when(userService.registerUser(userDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(userDTO);
        User result = response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assert result != null;
        assertEquals("testuser", result.getUserName());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
    }
}