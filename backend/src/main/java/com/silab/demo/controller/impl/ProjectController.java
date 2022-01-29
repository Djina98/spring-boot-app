package com.silab.demo.controller.impl;

import com.silab.demo.controller.MyRestController;
import com.silab.demo.dto.impl.ProjectDto;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/project")
@CrossOrigin("*")
public class ProjectController implements MyRestController<ProjectDto, Long> {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ProjectDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAll());
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> findById(Long id) {
        Optional<ProjectDto> projectDto = projectService.findById(id);
        if (projectDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(projectDto.get());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project with id: " + id + " was not found!");
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> save(ProjectDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> deleteById(Long id) {
        try {
            projectService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Project with id " +  id + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> update(ProjectDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.update(dto));
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
