package com.silab.demo.controller.impl;

import com.silab.demo.controller.MyRestController;
import com.silab.demo.dto.impl.DepartmentDto;
import com.silab.demo.exception.impl.MyEntityAlreadyExists;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/department")
@CrossOrigin("*")
public class DepartmentController implements MyRestController<DepartmentDto, Long> {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<DepartmentDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAll());
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> findById(Long id) {
        Optional<DepartmentDto> departmentDto = departmentService.findById(id);
        if (departmentDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(departmentDto.get());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department with id: " + id + " was not found!");
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> save(DepartmentDto dto) {
        try {
            departmentService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Department " + dto.getName() + " is created!");
        } catch (MyEntityAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> deleteById(Long id) {
        try {
            departmentService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Department with id " +  id + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> update(DepartmentDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(departmentService.update(dto));
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
