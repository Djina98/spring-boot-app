package com.silab.demo.mapper;

import com.silab.demo.dto.UserDto;
import com.silab.demo.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper extends MyMapper<UserEntity, UserDto>{
}
