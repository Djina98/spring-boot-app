package com.silab.demo.mapper;

import com.silab.demo.dto.MyDto;
import com.silab.demo.entity.MyEntity;
import java.util.List;

public interface MyMapper<Entity extends MyEntity, Dto extends MyDto>{

    public Entity toEntity(Dto dto);
    public Dto toDto(Entity entity);
    List<Entity> toEntity(List<Dto> dtoList);
    List<Dto> toDto(List<Entity> entityList);
}
