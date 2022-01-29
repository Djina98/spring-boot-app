package com.silab.demo.repository;

import com.silab.demo.entity.impl.ProjectItemEntity;
import com.silab.demo.entity.impl.ProjectItemIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectItemRepository extends JpaRepository<ProjectItemEntity, ProjectItemIdentity> {

    List<ProjectItemEntity> findProjectItemsByEmployeeId(Long id);
    List<ProjectItemEntity> findProjectItemsByProjectId(Long id);
}
