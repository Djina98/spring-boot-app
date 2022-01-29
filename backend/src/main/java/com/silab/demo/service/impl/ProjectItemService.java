package com.silab.demo.service.impl;

import com.silab.demo.dto.impl.ProjectItemDto;
import com.silab.demo.entity.impl.ProjectItemEntity;
import com.silab.demo.entity.impl.ProjectItemIdentity;
import com.silab.demo.exception.impl.MyEntityAlreadyExists;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.mapper.impl.ProjectItemMapper;
import com.silab.demo.repository.EmployeeRepository;
import com.silab.demo.repository.ProjectItemRepository;
import com.silab.demo.repository.ProjectRepository;
import com.silab.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectItemService implements MyService<ProjectItemDto, ProjectItemIdentity> {

    private ProjectItemMapper projectItemMapper;
    private ProjectItemRepository projectItemRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectItemService(ProjectItemMapper projectItemMapper,
                              ProjectItemRepository projectItemRepository,
                              EmployeeRepository employeeRepository,
                              ProjectRepository projectRepository) {
        this.projectItemMapper = projectItemMapper;
        this.projectItemRepository = projectItemRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<ProjectItemDto> findById(ProjectItemIdentity id) {
        Optional<ProjectItemEntity> projectItemEntity = projectItemRepository.findById(id);
        if(projectItemEntity.isPresent()) {
            return Optional.of(projectItemMapper.toDto(projectItemEntity.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectItemDto> getAll() {
        List<ProjectItemEntity> projectItemEntities = projectItemRepository.findAll();
        return projectItemEntities.stream().map(en -> {
            return projectItemMapper.toDto(en);
        }).collect(Collectors.toList());
    }

    @Override
    public ProjectItemDto save(ProjectItemDto dto) throws MyEntityAlreadyExists, MyEntityDoesntExist {
        Optional<ProjectItemEntity> projectItemEntity = projectItemRepository.findById(dto.getId());
        if(projectItemEntity.isPresent()) {
            throw new MyEntityAlreadyExists("Project item with employee id " + dto.getId().getEmployee_id() +
                    " and project id " + dto.getId().getProject_id() +
                    " already exists in the system!");
        }

        employeeRepository.findById(dto.getEmployee().getId()).orElseThrow(
                () -> new MyEntityDoesntExist("Employee with id: " + dto.getEmployee().getId() + "doesn't exist!"));

        projectRepository.findById(dto.getProject().getId()).orElseThrow(
                () -> new MyEntityDoesntExist("Project with id: " + dto.getProject().getId() + "doesn't exist!"));

        ProjectItemEntity projectItemToSave = this.projectItemMapper.toEntity(dto);
        return this.projectItemMapper.toDto(this.projectItemRepository.save(projectItemToSave));
    }

    @Override
    public void delete(ProjectItemIdentity id) throws MyEntityDoesntExist {
        Optional<ProjectItemEntity> projectItemEntity = projectItemRepository.findById(id);
        if(projectItemEntity.isPresent()) {
            projectItemRepository.deleteById(id);
        }
        else
            throw new MyEntityDoesntExist("Project item with employee id " + id.getEmployee_id() + " and project id " + id.getProject_id() + " doesn't exist!");
    }

    @Override
    public Optional<ProjectItemDto> update(ProjectItemDto dto) throws MyEntityDoesntExist {
        Optional<ProjectItemEntity> projectItemEntity = projectItemRepository.findById(dto.getId());
        if(projectItemEntity.isPresent()) {
           employeeRepository.findById(dto.getEmployee().getId()).orElseThrow(
                    () -> new MyEntityDoesntExist("Employee with id: " + dto.getEmployee().getId() + "doesn't exist!"));

           projectRepository.findById(dto.getProject().getId()).orElseThrow(
                    () -> new MyEntityDoesntExist("Project with id: " + dto.getProject().getId() + "doesn't exist!"));

            projectItemRepository.save(projectItemMapper.toEntity(dto));
            return Optional.of(dto);
        }
        else {
            throw new MyEntityDoesntExist("Project item with employee id " + dto.getId().getEmployee_id() + " and project id " + dto.getId().getProject_id() + " doesn't exist!");
        }
    }

    public List<ProjectItemDto> findProjectItemsByEmployeeId(Long id) {
        this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " doesn't exist"));

        return projectItemRepository.findProjectItemsByEmployeeId(id).stream().map(this.projectItemMapper::toDto).collect(Collectors.toList());
    }

    public List<ProjectItemDto> findProjectItemsByProjectId(Long id) {
        this.projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project with id: " + id + " doesn't exist"));

        return projectItemRepository.findProjectItemsByProjectId(id).stream().map(this.projectItemMapper::toDto).collect(Collectors.toList());
    }
}
