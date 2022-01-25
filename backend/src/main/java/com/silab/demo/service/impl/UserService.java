package com.silab.demo.service.impl;

import com.silab.demo.dto.impl.UserDto;
import com.silab.demo.mapper.impl.UserMapper;
import com.silab.demo.repository.UserRepository;
import com.silab.demo.service.MyUserInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements MyUserInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto login(String username, String password) {
        return userMapper.toDto(userRepository.login(username, password));
    }
}
