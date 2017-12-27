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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.abp.security.dao.UserDao;
import com.abp.security.model.User;
import org.springframework.beans.factory.annotation.Configurable;
 
@Service("userService")
@Configurable
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao dao;
 
    public User findById(int id) {
        return dao.findById(id);
    }
 
    public User findBySso(String sso) {
        System.out.println("user login : "+sso);
        return dao.findBySSO(sso);
    }
 
}
