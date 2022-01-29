package com.silab.demo.controller.impl;

import com.silab.demo.controller.MyRestController;
import com.silab.demo.dto.impl.EmployeeDto;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
@CrossOrigin("*")
public class EmployeeController implements MyRestController<EmployeeDto, Long> {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAll());
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> findById(Long id) {
        Optional<EmployeeDto> employeeDto = employeeService.findById(id);
        if (employeeDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(employeeDto.get());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee with id: " + id + " was not found!");
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> save(EmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> deleteById(Long id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee with id " +  id + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> update(EmployeeDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(dto));
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
