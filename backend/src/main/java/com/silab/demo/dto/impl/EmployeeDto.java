package com.silab.demo.dto.impl;

import com.silab.demo.dto.MyDto;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
public class EmployeeDto implements MyDto {

    /* Employee ID */
    private Long id;

    /* Employee firstname */
    private String firstName;

    /* Employee lastname */
    private String lastName;

    /* Employee job name */
    private String jobName;

    /* Employee birthday */
    private Date birthday;

    /* Employee address */
    private String address;

    /* Employee hire date */
    private Date hireDate;

    /* Employee salary */
    private Double salary;

    /* Employee department */
    private DepartmentDto department;
}
