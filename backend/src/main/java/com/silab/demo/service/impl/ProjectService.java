package com.silab.demo.service.impl;

import com.silab.demo.dto.impl.ProjectDto;
import com.silab.demo.entity.impl.ProjectEntity;
import com.silab.demo.exception.impl.MyEntityDoesntExist;
import com.silab.demo.mapper.impl.ProjectMapper;
import com.silab.demo.repository.ProjectRepository;
import com.silab.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService implements MyService<ProjectDto, Long> {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectMapper projectMapper, ProjectRepository projectRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<ProjectDto> findById(Long id) {
        Optional<ProjectEntity> projectEntity = projectRepository.findById(id);
        if(projectEntity.isPresent()) {
            ProjectDto projectDto = projectMapper.toDto(projectEntity.get());
            return Optional.of(projectDto);
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectDto> getAll() {
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        return projectEntities.stream().map(en -> {
            return projectMapper.toDto(en);
        }).collect(Collectors.toList());
    }

    @Override
    public ProjectDto save(ProjectDto dto) {
        projectRepository.save(projectMapper.toEntity(dto));
        return dto;
    }

    @Override
    public void delete(Long id) throws MyEntityDoesntExist {
        Optional<ProjectEntity> projectEntity = projectRepository.findById(id);
        if(projectEntity.isPresent()) {
            projectRepository.deleteById(id);
        }
        else
            throw new MyEntityDoesntExist("Project with id: " + id + " doesn't exist!");
    }

    @Override
    public Optional<ProjectDto> update(ProjectDto dto) throws MyEntityDoesntExist {
        Optional<ProjectEntity> projectEntity= projectRepository.findById(dto.getId());
        if(projectEntity.isPresent()) {
            projectRepository.save(projectMapper.toEntity(dto));
            return Optional.of(dto);
        }
        else
            throw new MyEntityDoesntExist("Project with id " + dto.getId() + " doesn't exist!");
    }
}
