package com.ecommerce.user;

public interface UserService {
    User registerUser(UserDTO userDTO);
    UserLoginResult loginUser(String email, String password);
}
