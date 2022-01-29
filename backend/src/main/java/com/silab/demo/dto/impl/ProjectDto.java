package com.silab.demo.dto.impl;

import com.silab.demo.dto.MyDto;
import com.silab.demo.entity.impl.ProjectItemEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProjectDto implements MyDto {

    /* Project ID */
    private Long id;

    /* Project name */
    private String name;

    /* Project department */
    private DepartmentDto department;
    
}
