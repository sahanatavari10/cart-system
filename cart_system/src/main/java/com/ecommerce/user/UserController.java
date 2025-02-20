package com.ecommerce.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("e-commerce/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO){
        log.info("Received request: {}", userDTO);
        var user = userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResult> loginUser(@Valid @RequestParam String email, @Valid @RequestParam String password){
        var result = userService.loginUser(email, password);
        return switch (result){
            case UserLoginResult.IsPresent isPresent -> ResponseEntity.ok(isPresent);
            case UserLoginResult.NotFound notFound -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
            case UserLoginResult.InvalidCredentials invalid -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalid);
        };
    }
}
