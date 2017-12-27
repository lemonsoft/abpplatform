/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.parts2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ss
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
     
    @Autowired
    private RoleDAO roleDAO;
 
    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }
 
}
