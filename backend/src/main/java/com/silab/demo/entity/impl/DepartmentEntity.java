package com.silab.demo.entity.impl;

import com.silab.demo.entity.MyEntity;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="departments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class DepartmentEntity implements MyEntity {

    /* Department ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Department name */
    @Column(name = "name", nullable = false)
    private String name;

    /* Department location */
    @Column(name = "location", nullable = false)
    private String location;
}
