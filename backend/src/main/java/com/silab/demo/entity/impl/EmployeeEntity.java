package com.silab.demo.entity.impl;

import com.silab.demo.entity.MyEntity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="employees")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class EmployeeEntity implements MyEntity {

    /* Employee ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Employee firstname */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /* Employee lastname */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /* Employee job name */
    @Column(name = "job_name", nullable = false)
    private String jobName;

    /* Employee birthday */
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    /* Employee address */
    @Column(name = "address", nullable = false)
    private String address;

    /* Employee hire date */
    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

    /* Employee salary */
    @Column(name = "salary", nullable = false)
    private Double salary;

    /* Employee department */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

}
