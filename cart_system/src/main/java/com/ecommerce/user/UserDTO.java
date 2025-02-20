package com.ecommerce.user;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
}