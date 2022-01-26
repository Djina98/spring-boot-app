package com.silab.demo.dto.impl;

import com.silab.demo.dto.MyDto;
import lombok.*;

@Getter @Setter
public class UserDto implements MyDto {

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
