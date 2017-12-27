/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.parts2;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ss
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
     
    
    private UserDAO userDAO;
 
    public Userss getUser(String login) {
        return userDAO.getUser(login);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    
 
}