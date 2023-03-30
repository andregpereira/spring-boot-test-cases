package com.andregpereira.tests.springboottestcases.domain.services;

import com.andregpereira.tests.springboottestcases.domain.entities.User;

public interface UserService {

    public User create(User user);

    public User findById(Long id);

}
