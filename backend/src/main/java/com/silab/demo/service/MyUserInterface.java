package com.silab.demo.service;

import com.silab.demo.dto.impl.UserDto;

public interface MyUserInterface {
    UserDto login(String username, String password);
}
