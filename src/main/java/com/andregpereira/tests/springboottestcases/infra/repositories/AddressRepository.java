package com.andregpereira.tests.springboottestcases.infra.repositories;

import com.andregpereira.tests.springboottestcases.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
