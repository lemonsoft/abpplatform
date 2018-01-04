/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.assessmentlocation;

import com.abp.superservice.SuperService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/location")
public class AssesmentLocationController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("admin/common");
        model.addObject("locationdao", new LocationDao());

        request.getSession().setAttribute("body", "/admin/location/location.jsp");
        return model;
    }

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String search(HttpServletRequest request, HttpServletResponse response, LocationDao beanObj, Model model) {

        String fromdate = beanObj.getFromdate();
        String todate = beanObj.getTodate();

        System.out.println("from date : " + fromdate);
        System.out.println("to date : " + todate);

        model.addAttribute("locationdao", new LocationDao());
        request.getSession().setAttribute("body", "/admin/location/location.jsp");
        return "admin/common";
    }
}
