package com.silab.demo.entity.impl;

import com.silab.demo.entity.MyEntity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="project_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class ProjectItemEntity implements MyEntity {

    /* Project item ID */
    @EmbeddedId
    private ProjectItemIdentity id;

    /* Employee */
    @MapsId("employee_id")
    @ManyToOne
    private EmployeeEntity employee;

    /* Project */
    @MapsId("project_id")
    @ManyToOne
    private ProjectEntity project;

    /* Job */
    @Column(name = "job", nullable = false)
    private String job;

    /* Enter date */
    @Column(name = "enter_date", nullable = false)
    private Date enterDate;
}
