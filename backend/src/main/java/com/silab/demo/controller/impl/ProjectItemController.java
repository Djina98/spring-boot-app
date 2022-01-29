package com.silab.demo.controller.impl;

import com.silab.demo.controller.MyRestProjectItemController;
import com.silab.demo.dto.impl.ProjectItemDto;
import com.silab.demo.entity.impl.ProjectItemIdentity;
import com.silab.demo.exception.impl.MyEntityAlreadyExists;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.service.impl.ProjectItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/project-item")
@CrossOrigin("*")
public class ProjectItemController implements MyRestProjectItemController<ProjectItemDto, ProjectItemIdentity> {

    private final ProjectItemService projectItemService;

    @Autowired
    public ProjectItemController(ProjectItemService projectItemService) {
        this.projectItemService = projectItemService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ProjectItemDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(projectItemService.getAll());
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<Object> findById(ProjectItemIdentity id) {
        Optional<ProjectItemDto> projectItemDto = projectItemService.findById(id);
        if (projectItemDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(projectItemDto.get());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project item with employee id " + id.getEmployee_id() + " and project id " + id.getProject_id() + " was not found!");
    }

    @GetMapping(path = "/project-item/employee/{id}")
    @Override
    public ResponseEntity<List<ProjectItemDto>> findProjectItemsByEmployeeId(Long id) {
        List<ProjectItemDto> projectItemsDto = projectItemService.findProjectItemsByEmployeeId(id);
        return ResponseEntity.status(HttpStatus.OK).body(projectItemsDto);
    }

    @GetMapping(path = "/project-item/project/{id}")
    @Override
    public ResponseEntity<List<ProjectItemDto>> findProjectItemsByProjectId(Long id) {
        List<ProjectItemDto> projectItemsDto = projectItemService.findProjectItemsByProjectId(id);
        return ResponseEntity.status(HttpStatus.OK).body(projectItemsDto);
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> save(ProjectItemDto dto) {
        ProjectItemIdentity id = dto.getId();
        Optional<ProjectItemDto> dtoExists = projectItemService.findById(id);
        if(!dtoExists.isPresent()) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(projectItemService.save(dto));
            } catch (MyEntityAlreadyExists e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            } catch (MyEntityDoesntExist e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project item with employee id " + dto.getId().getEmployee_id() + " and project id " + dto.getId().getProject_id() + " already exists!");
        }
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Object> deleteById(ProjectItemIdentity id) {
        try {
            projectItemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Project item with employee id " + id.getEmployee_id() + " and project id " + id.getProject_id() + " is deleted!");
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path="/{id}")
    @Override
    public ResponseEntity<Object> update(ProjectItemDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectItemService.update(dto));
        } catch (MyEntityDoesntExist e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
