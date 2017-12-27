/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.parts3;

/**
 *
 * @author ss
 */
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

 
@Repository
public class UserDAOImpl {
    
     public CustomUser loadUserByUsername(final String username) {
         //Write your DB call code to get the user details from DB,But I am just hard coding the user
         
           System.out.println("Data comes here : "+username);
            CustomUser user = new CustomUser();
            user.setFirstName("kb");
            user.setLastName("gc");
            user.setUsername("kb");
            user.setPassword("1234");
            Role r = new Role();
            r.setName("ROLE_ADMIN");
            List<Role> roles = new ArrayList<Role>();
            roles.add(r);
            user.setAuthorities(roles);
            return user;
        }
 
}