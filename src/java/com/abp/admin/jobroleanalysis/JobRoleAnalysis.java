/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.jobroleanalysis;

import com.abp.superservice.SuperService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/jobanalysis")
public class JobRoleAnalysis {

    private static final Logger logger = Logger.getLogger(JobRoleAnalysis.class);

    @Autowired
    private ServletContext servletContext;

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("jobroleanalysisdao", new JobRoleAnalysisDAO());

        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/jobroleanalysis/jobroleanalysis.jsp");
        return "admin/common";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, JobRoleAnalysisDAO beanObj, Model model) {
        
        System.out.println(" fromdate " + beanObj.getFromdate());
        System.out.println(" todate " + beanObj.getTodate());
        
        model.addAttribute("jobroleanalysisdao", new JobRoleAnalysisDAO());

        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/jobroleanalysis/jobroleanalysis.jsp");
        return "admin/common";
    }

}
