package com.silab.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /* User ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* User firstname */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /* User lastname */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /* Username */
    @Column(nullable = false)
    private String username;

    /* Password */
    @Column(nullable = false)
    private String password;
}
