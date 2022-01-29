package com.silab.demo.entity.impl;

import com.silab.demo.entity.MyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProjectItemIdentity implements MyEntity {

    private Long employee_id;
    private Long project_id;
}
