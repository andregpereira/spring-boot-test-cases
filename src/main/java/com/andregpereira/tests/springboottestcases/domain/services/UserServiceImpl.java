package com.andregpereira.tests.springboottestcases.domain.services;

import com.andregpereira.tests.springboottestcases.domain.entities.User;
import com.andregpereira.tests.springboottestcases.infra.exceptions.UserAlreadyExistsException;
import com.andregpereira.tests.springboottestcases.infra.exceptions.UserNotFoundException;
import com.andregpereira.tests.springboottestcases.infra.repositories.AddressRepository;
import com.andregpereira.tests.springboottestcases.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.existsByCpf(user.getCpf()))
            throw new UserAlreadyExistsException("CPF repetido");
        addressRepository.save(user.getAddress());
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("404");
        });
    }

}
