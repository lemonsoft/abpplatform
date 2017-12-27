/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.parts2;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ss
 */
@Repository
public class UserDAOImpl implements UserDAO {
     
    
    private SessionFactory sessionFactory;
     
   
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
 
    @Override
    public Userss getUser(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println("Getting login values  "+login);
        List<Userss> userList = new ArrayList<Userss>();
        Query query = session.createQuery("from Userss u where u.login = :login");
        query.setParameter("login", login);
        userList = query.list();
        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;    
    }
 
}