/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/welcome")
public class HelloController {

    @RequestMapping(value="/helloWorld", method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/common");
        
        System.out.println("hello world controller....");

        request.getSession().setAttribute("body", "/admin/hello.jsp");
        return model;
    }
    @RequestMapping(value="/login", method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/commonlogin");
        
        System.out.println("login controller....");

        request.getSession().setAttribute("body", "/admin/login/login.jsp");
        return model;
    }
}
