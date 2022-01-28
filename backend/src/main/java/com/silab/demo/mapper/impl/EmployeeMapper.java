package com.silab.demo.mapper.impl;

import com.silab.demo.dto.impl.EmployeeDto;
import com.silab.demo.entity.impl.EmployeeEntity;
import com.silab.demo.mapper.MyMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface EmployeeMapper extends MyMapper<EmployeeEntity, EmployeeDto> {
}
