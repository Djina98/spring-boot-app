package com.silab.demo.service;

import com.silab.demo.dto.MyDto;
import com.silab.demo.exception.impl.MyEntityAlreadyExists;
import com.silab.demo.exception.impl.MyEntityDoesntExist;

import java.util.List;
import java.util.Optional;

public interface MyService<DTO extends MyDto, ID> {

    /* CRUD */
    Optional<DTO> findById(ID id);
    List<DTO> getAll();
    DTO save(DTO dto) throws MyEntityAlreadyExists;
    void delete(ID id) throws MyEntityDoesntExist;
    Optional<DTO> update(DTO dto) throws  MyEntityDoesntExist;

}
