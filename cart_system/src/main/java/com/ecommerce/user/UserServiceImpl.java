package com.ecommerce.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        log.info("{}", user);
        return userRepository.save(user);
    }

    @Override
    public UserLoginResult loginUser(String email, String password){
        Optional<User> mayBeUser = userRepository.findByEmail(email);
        if(mayBeUser.isPresent()){
            if(passwordEncoder.matches(password, mayBeUser.get().getPassword())) {
                return new UserLoginResult.IsPresent(mayBeUser.get());
            }
            return new UserLoginResult.InvalidCredentials();
        }
        return new UserLoginResult.NotFound();
    }
}
