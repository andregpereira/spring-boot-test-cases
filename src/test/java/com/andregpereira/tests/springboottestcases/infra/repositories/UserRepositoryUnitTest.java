package com.andregpereira.tests.springboottestcases.infra.repositories;

import com.andregpereira.tests.springboottestcases.domain.entities.Address;
import com.andregpereira.tests.springboottestcases.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryUnitTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TestEntityManager em;
//    @BeforeEach
//    public void beforeEach

    @Test
    @Rollback(value = false)
    public void saveUser() {
        User u = new User(null, "name", "12345678901", new Address(null, "city"));
        em.persistFlushFind(u.getAddress());
        User sut = userRepository.save(u);
        assertThat(userRepository.findById(sut.getId()).get().getId()).isEqualTo(u.getId());
    }

    @Test
    public void findById() {
        User sut = userRepository.findById(1L).get();
        assertThat(sut.getId()).isEqualTo(1L);
    }

    @Test
    public void deleteById() {
        User sut = userRepository.findById(1L).get();
        userRepository.deleteById(sut.getId());
        Optional<User> optionalUser = userRepository.findById(1L);
        User nullUser = null;
        if (optionalUser.isPresent())
            nullUser = optionalUser.get();
        assertThat(nullUser).isNull();
    }

}
