package com.silab.demo.mapper.impl;

import com.silab.demo.dto.impl.ProjectDto;
import com.silab.demo.entity.impl.ProjectEntity;
import com.silab.demo.mapper.MyMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ProjectMapper extends MyMapper<ProjectEntity, ProjectDto> {
}
