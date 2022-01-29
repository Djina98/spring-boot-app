package com.silab.demo.dto.impl;

import com.silab.demo.dto.MyDto;
import com.silab.demo.entity.impl.ProjectItemIdentity;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
public class ProjectItemDto implements MyDto {

    /* Project item ID */
    private ProjectItemIdentity id;

    /* Employee */
    private EmployeeDto employee;

    /* Project */
    private ProjectDto project;

    /* Project item job name */
    private String job;

    /* Project item enter date */
    private Date enterDate;


}
