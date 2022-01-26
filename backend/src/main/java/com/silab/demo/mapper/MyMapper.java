package com.silab.demo.mapper;

import com.silab.demo.dto.MyDto;
import com.silab.demo.entity.MyEntity;
import java.util.List;

public interface MyMapper<Entity extends MyEntity, DTO extends MyDto>{

    public Entity toEntity(DTO dto);
    public DTO toDto(Entity entity);
    List<Entity> toEntity(List<DTO> dtoList);
    List<DTO> toDto(List<Entity> entityList);
}
