package com.andregpereira.tests.springboottestcases.domain.services;

import com.andregpereira.tests.springboottestcases.domain.entities.Address;
import com.andregpereira.tests.springboottestcases.domain.entities.User;
import com.andregpereira.tests.springboottestcases.infra.exceptions.UserAlreadyExistsException;
import com.andregpereira.tests.springboottestcases.infra.exceptions.UserNotFoundException;
import com.andregpereira.tests.springboottestcases.infra.repositories.AddressRepository;
import com.andregpereira.tests.springboottestcases.infra.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void create() {
        User u = new User(null, "name", "", new Address(null, ""));
        given(userRepository.save(u)).willReturn(u);
        User userCreated = userService.create(u);
        System.out.println(userCreated.getName());
        assertThat(userCreated.getName()).isEqualTo(u.getName());
        assertThat(userCreated.getCpf()).isEqualTo(u.getCpf());
    }

    @Test
    public void createWithExistingCpf() {
        User u = new User(null, "name", "", new Address(null, ""));
        given(userRepository.existsByCpf("")).willReturn(true);
        assertThatThrownBy(() -> userService.create(u)).isInstanceOf(UserAlreadyExistsException.class).hasMessage(
                "CPF repetido");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void foundById() {
        User u = new User(null, "name", "", new Address(null, ""));
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));
        Optional<User> user = Optional.ofNullable(userService.findById(1L));
        assertThat(user).isNotEmpty();
        assertThat(user.get()).isEqualTo(u);
    }

    @Test
    public void notFoundById() {
        given(userRepository.findById(1L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> userService.findById(1L)).isInstanceOf(UserNotFoundException.class).hasMessage("404");
    }

}
