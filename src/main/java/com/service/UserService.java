/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres Felipe Diaz
 */
public interface UserService {

    void saveUser(User u);

    void deleteUser(int id);

    List<User> listUser();
    
    public Optional<User> listarUserId(int id);

    
}
