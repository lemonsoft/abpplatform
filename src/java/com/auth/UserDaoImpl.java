/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth;

/**
 *
 * @author ss
 */
public class UserDaoImpl implements UserDao {
    
    @Override
    public User findByUserName(String username) {
        
        System.out.println("Login with this username --------->>>>>>>>"+username);
        User user = new User();
        user.setUsername(username);
        user.setPassword("admin");
        return user;
    }
}
