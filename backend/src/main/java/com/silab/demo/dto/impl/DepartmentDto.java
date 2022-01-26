package com.silab.demo.dto.impl;

import com.silab.demo.dto.MyDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentDto implements MyDto {

    /* Department ID */
    private Long id;

    /* Department name */
    private String name;

    /* Department location */
    private String location;
}


