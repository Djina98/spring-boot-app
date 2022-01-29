package com.silab.demo.entity.impl;

import com.silab.demo.entity.MyEntity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="projects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class ProjectEntity implements MyEntity {

    /* Project ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Project name */
    @Column(name = "name", nullable = false)
    private String name;

    /* Project department */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    /* Project items */
    @OneToMany(mappedBy = "project", cascade=CascadeType.ALL)
    private List<ProjectItemEntity> projectItems;
}
