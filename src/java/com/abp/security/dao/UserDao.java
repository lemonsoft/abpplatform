/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.security.dao;

import com.abp.security.model.User;

/**
 *
 * @author ss
 */
public interface UserDao {
    User findById(int id);
     
    User findBySSO(String sso);
}
