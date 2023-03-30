package com.andregpereira.tests.springboottestcases.infra.repositories;

import com.andregpereira.tests.springboottestcases.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(String cpf);

}
