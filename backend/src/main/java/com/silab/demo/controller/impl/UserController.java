package com.silab.demo.controller.impl;

import com.silab.demo.requests.impl.UserLoginRequest;
import com.silab.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.silab.demo.dto.impl.UserDto;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/login")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        try {
            UserDto userDto = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
            return ResponseEntity.ok()
                    .body(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
