/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth;

import com.abp.security.model.User;

/**
 *
 * @author ss
 */
public interface UserService {
 
    User findById(int id);
     
    User findBySso(String sso);
     
}