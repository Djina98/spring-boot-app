package com.silab.demo.dto;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
public class UserDto implements Serializable {

    /* User ID */
    private Long id;

    /* User firstname */
    private String firstName;

    /* User lastname */
    private String lastName;

    /* Username */
    private String username;

    /* Password */
    private String password;
}
