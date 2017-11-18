/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.project;

import com.abp.superservice.SuperService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/project")
public class ProjectController {
    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("project", new ProjectDAO());
        model.addAttribute("action", "add.io");
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/project/addproject.jsp");
        return "/common";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("project") ProjectDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {
        this.superService.saveObject(beanObj);
        return "redirect:/admin/project/init.io";
    }

    @RequestMapping(value = "/initSearch", method = RequestMethod.GET)
    public String initSearch(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("project", new ProjectDAO());
        model.addAttribute("records", this.superService.listAllObjects(new ProjectDAO()));
        request.getSession().setAttribute("body", "/admin/project/searchproject.jsp");
        return "/common";
    }

    @RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
    public String initUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = (String) request.getParameter("recid");
        model.addAttribute("project", this.superService.getObjectById(new ProjectDAO(), new Integer(recid)));
        model.addAttribute("action", "update.io");
        model.addAttribute("mode", "update");
        request.getSession().setAttribute("body", "/admin/project/addproject.jsp");
        return "/common";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("project") ProjectDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String forward = "redirect:/admin/project/initSearch.io";
        try {
            this.superService.updateObject(beanObj);
        } catch (Exception e) {
            model.addAttribute("project", beanObj);
            model.addAttribute("action", "update.io");
            model.addAttribute("mode", "update");
            request.getSession().setAttribute("body", "/admin/project/addproject.jsp");
            forward = "/common";
        }

        return forward;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = (String) request.getParameter("recid");
        this.superService.deleteObjectById(new ProjectDAO(), new Integer(recid));
        
        return "redirect:/admin/project/initSearch.io";
    }
}
