package com.silab.demo.service.impl;

import com.silab.demo.dto.impl.EmployeeDto;
import com.silab.demo.entity.impl.EmployeeEntity;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.mapper.impl.EmployeeMapper;
import com.silab.demo.repository.EmployeeRepository;
import com.silab.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService implements MyService <EmployeeDto, Long> {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<EmployeeDto> findById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(employeeEntity.isPresent()) {
            EmployeeDto employeeDto = employeeMapper.toDto(employeeEntity.get());
            return Optional.of(employeeDto);
        }
        return Optional.empty();
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(en -> {
            return employeeMapper.toDto(en);
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        employeeRepository.save(employeeMapper.toEntity(dto));
        return dto;
    }

    @Override
    public void delete(Long id) throws MyEntityDoesntExist {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(employeeEntity.isPresent()) {
            employeeRepository.deleteById(id);
        }
        else
            throw new MyEntityDoesntExist("Employee with id: " + id + " doesn't exist!");
    }

    @Override
    public Optional<EmployeeDto> update(EmployeeDto dto) throws MyEntityDoesntExist {
        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(dto.getId());
        if(employeeEntity.isPresent()) {
            employeeRepository.save(employeeMapper.toEntity(dto));
            return Optional.of(dto);
        }
        else
            throw new MyEntityDoesntExist("Employee with id " + dto.getId() + " doesn't exist!");
    }
}
