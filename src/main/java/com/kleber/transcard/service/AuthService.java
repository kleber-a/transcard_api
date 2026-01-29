package com.kleber.transcard.service;

import com.kleber.transcard.dto.request.AuthDTO;
import com.kleber.transcard.dto.response.LoginResponseDTO;
import com.kleber.transcard.dto.request.RegisterDTO;
import com.kleber.transcard.entity.User;
import com.kleber.transcard.exceptions.auth.InvalidCredentialsException;
import com.kleber.transcard.exceptions.common.EmailAlreadyExistsException;
import com.kleber.transcard.infra.security.TokenService;
import com.kleber.transcard.mapper.UserMapper;
import com.kleber.transcard.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public AuthService(
            AuthenticationManager authenticationManager,
            TokenService tokenService,
            UserMapper userMapper,
            UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;

    }



    public LoginResponseDTO login(AuthDTO data) {
        try {
            var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            User user = (User) auth.getPrincipal();
            System.out.println("user");
            return new LoginResponseDTO(
                    token,
                    userMapper.loginToDto(user)
            );
        }catch (Exception ex) {
            throw new InvalidCredentialsException();
        }

    }

    public void register(RegisterDTO data) {

        if (userRepository.findByEmail(data.email()) != null) {
            throw new EmailAlreadyExistsException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(),data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
    }


}
