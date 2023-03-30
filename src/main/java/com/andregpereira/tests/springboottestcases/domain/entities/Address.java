package com.andregpereira.tests.springboottestcases.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_addresses")
@SequenceGenerator(name = "address", sequenceName = "sq_addresses", allocationSize = 1)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address")
    private Long id;

    @Column
    private String city;

}
