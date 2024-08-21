/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.User;
import com.persistence.UserPersistence;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres Felipe Diaz
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserPersistence userPersistence;

    @Override
    public void saveUser(User u) {
        userPersistence.save(u);
    }

    @Override
    public void deleteUser(int id) {
        userPersistence.deleteById(id);
    }

    @Override
    public List<User> listUser() {
        return userPersistence.findAll();
    }

    @Override
    public Optional<User> listarUserId(int id) {
        return userPersistence.findById(id);
    }

}
