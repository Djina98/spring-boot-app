package com.silab.demo.mapper.impl;

import com.silab.demo.dto.impl.ProjectItemDto;
import com.silab.demo.entity.impl.ProjectItemEntity;
import com.silab.demo.mapper.MyMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ProjectItemMapper extends MyMapper<ProjectItemEntity, ProjectItemDto> {
}
