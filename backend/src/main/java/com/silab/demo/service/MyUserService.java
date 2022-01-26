package com.silab.demo.service;

import com.silab.demo.dto.impl.UserDto;

public interface MyUserService {
    UserDto login(String username, String password);
}
