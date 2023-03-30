package com.andregpereira.tests.springboottestcases.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
@SequenceGenerator(name = "user", sequenceName = "sq_users", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column(unique = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "id_address", foreignKey = @ForeignKey(name = "fk_id_address"))
    private Address address;

}
