package com.kleber.transcard.service;

import com.kleber.transcard.dto.request.CreateUserDTO;
import com.kleber.transcard.dto.response.UserDTO;
import com.kleber.transcard.dto.request.UserUpdateDTO;
import com.kleber.transcard.entity.User;
import com.kleber.transcard.entity.enums.Role;

import com.kleber.transcard.exceptions.common.EmailAlreadyExistsException;
import com.kleber.transcard.exceptions.common.UserNotFoundException;
import com.kleber.transcard.mapper.UserMapper;
import com.kleber.transcard.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //Listar
    public Page<UserDTO> getAllUsers(String name, Pageable pageable) {
        return userRepository.searchByNameNormalized(name, pageable)
                .map(userMapper::toDto);
    }

    //Criar
    public UserDTO save(CreateUserDTO createUserDTO){
        if (userRepository.existsByEmail(createUserDTO.email())) {
            throw new EmailAlreadyExistsException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(createUserDTO.password());

        User user = new User();
        user.setName(createUserDTO.name());
        user.setEmail(createUserDTO.email());
        user.setPassword(encryptedPassword);
        user.setRole(Role.USER);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }


    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {

        var user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);


        if (userUpdateDTO.name() != null) user.setName(userUpdateDTO.name());
        if (userUpdateDTO.email() != null){
            user.setEmail(userUpdateDTO.email());
        }
        if (userUpdateDTO.password() != null && !userUpdateDTO.password().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userUpdateDTO.password());
            user.setPassword(encryptedPassword);
        }

        user = userRepository.save(user);

        return userMapper.toDto(user);

    }

    //Deletear
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }


}
