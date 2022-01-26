package com.silab.demo.mapper.impl;

import com.silab.demo.dto.impl.DepartmentDto;
import com.silab.demo.entity.impl.DepartmentEntity;
import com.silab.demo.mapper.MyMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface DepartmentMapper extends MyMapper<DepartmentEntity, DepartmentDto> {
}
