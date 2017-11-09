/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.dasboard;

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
@RequestMapping("/admin/dashboard")
public class DashBoardController {
    @RequestMapping(value="/init", method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/common");
        
        System.out.println("hello world controller....");

        request.getSession().setAttribute("body", "/admin/dashboard/dashboard.jsp");
        return model;
    }
}
