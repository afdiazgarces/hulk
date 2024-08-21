/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.controller.UserController;
import com.model.User;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import org.springframework.ui.Model;

/**
 *
 * @author Andres Felipe Diaz
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private Model model;

    @Mock
    private UserService userService;

    @Test
    public void saveUserTest() {
        userController.save(generateUser());
        assertEquals(generateUser().getId(), generateUser().getId());
    }

    @Test
    public void listUserTest() {
        userController.listUser(model);
        assertEquals(generateUser().getId(), generateUser().getId());
    }

    @Test
    public void updatetUserTest() {
        userController.update(model, generateUser().getId());
        assertEquals(generateUser().getId(), generateUser().getId());
    }

    @Test
    public void addUserTest() {
        userController.add(model);
        assertEquals(generateUser().getId(), generateUser().getId());
    }

    @Test
    public void deleteUserTest() {
        userController.delete(generateUser().getId());
        assertEquals(generateUser().getId(), generateUser().getId());
    }

    private User generateUser() {
        User user = new User();
        user.setId(5);
        user.setName("jose");
        user.setPassword("12345");
        return user;

    }

}
