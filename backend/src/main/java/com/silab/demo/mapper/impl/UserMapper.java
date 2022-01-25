package com.silab.demo.mapper.impl;

import com.silab.demo.dto.impl.UserDto;
import com.silab.demo.entity.impl.UserEntity;
import com.silab.demo.mapper.MyMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper extends MyMapper<UserEntity, UserDto> {
}
