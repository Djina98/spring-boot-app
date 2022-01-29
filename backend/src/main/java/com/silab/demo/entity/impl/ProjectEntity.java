package com.silab.demo.entity.impl;

import com.silab.demo.entity.MyEntity;
import lombok.*;
import javax.persistence.*;

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
}
