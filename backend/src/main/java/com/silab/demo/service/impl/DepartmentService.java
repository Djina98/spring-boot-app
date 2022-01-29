package com.silab.demo.service.impl;

import com.silab.demo.dto.impl.DepartmentDto;
import com.silab.demo.entity.impl.DepartmentEntity;
import com.silab.demo.exception.impl.MyEntityAlreadyExists;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.mapper.impl.DepartmentMapper;
import com.silab.demo.repository.DepartmentRepository;
import com.silab.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentService implements MyService<DepartmentDto, Long> {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<DepartmentDto> findById(Long id) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        if(departmentEntity.isPresent()) {
            DepartmentDto departmentDto = departmentMapper.toDto(departmentEntity.get());
            return Optional.of(departmentDto);
        }
        return Optional.empty();
    }

    @Override
    public List<DepartmentDto> getAll() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities.stream().map(en -> {
            return departmentMapper.toDto(en);
        }).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto save(DepartmentDto dto){
        departmentRepository.save(departmentMapper.toEntity(dto));
        return dto;
    }

    @Override
    public void delete(Long id) throws MyEntityDoesntExist {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        if(departmentEntity.isPresent()) {
            departmentRepository.deleteById(id);
        }
        else
            throw new MyEntityDoesntExist("Department with id: " + id + " doesn't exist!");
    }

    @Override
    public Optional<DepartmentDto> update(DepartmentDto dto) throws MyEntityDoesntExist {
        Optional<DepartmentEntity> departmentEntity= departmentRepository.findById(dto.getId());
        if(departmentEntity.isPresent()) {
            departmentRepository.save(departmentMapper.toEntity(dto));
            return Optional.of(dto);
        }
        else
            throw new MyEntityDoesntExist("Department " + dto.getName() + " doesn't exist!");
    }
}
