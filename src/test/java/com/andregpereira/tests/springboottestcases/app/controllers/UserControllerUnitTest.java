package com.andregpereira.tests.springboottestcases.app.controllers;

import com.andregpereira.tests.springboottestcases.app.UserController;
import com.andregpereira.tests.springboottestcases.domain.entities.Address;
import com.andregpereira.tests.springboottestcases.domain.entities.User;
import com.andregpereira.tests.springboottestcases.domain.services.UserServiceImpl;
import com.andregpereira.tests.springboottestcases.infra.exceptions.UserAlreadyExistsException;
import com.andregpereira.tests.springboottestcases.infra.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    @MockBean
    private UserServiceImpl service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void create() throws Exception {
        User u = new User(null, "name", "", new Address(null, ""));
        given(service.create(u)).willReturn(u);
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(
                objectMapper.writeValueAsString(u))).andExpectAll(status().isCreated(), jsonPath("$").value(u));
    }

    @Test
    public void createWithInvalidData() throws Exception {
        User u = new User(null, null, null, new Address(null, null));
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(
                objectMapper.writeValueAsString(u))).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createExistingCpf() throws Exception {
        User u = new User(null, "name", "", new Address(null, ""));
        given(service.create(u)).willThrow(UserAlreadyExistsException.class);
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(
                objectMapper.writeValueAsString(u))).andExpect(status().isConflict());
    }

    @Test
    public void findById() throws Exception {
        User u = new User(null, "", "", new Address(null, ""));
        given(service.findById(10L)).willReturn(u);
        mockMvc.perform(get("/users/10")).andExpect(status().isOk());
    }

    @Test
    public void notFoundById() throws Exception {
        given(service.findById(10L)).willThrow(UserNotFoundException.class);
        mockMvc.perform(get("/users/10")).andExpect(status().isNotFound());
    }

    @Test
    public void findByInvalidId() throws Exception {
        mockMvc.perform(get("/users/a")).andExpect(status().isBadRequest());
    }

}
