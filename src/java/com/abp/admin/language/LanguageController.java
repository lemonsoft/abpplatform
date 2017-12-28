/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.language;

import com.abp.superservice.SuperService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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
@RequestMapping("/admin/language")
public class LanguageController {

    private static final Logger logger = Logger.getLogger(LanguageController.class);
    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("language", new LanguageDAO());
        model.addAttribute("action", "add.io");
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/language/addlanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("language") LanguageDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            this.superService.saveObject(beanObj);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return "redirect:/admin/language/init.io";
    }

    @RequestMapping(value = "/initSearch", method = RequestMethod.GET)
    public String initSearch(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("language", new LanguageDAO());
            model.addAttribute("records", this.superService.listAllObjects(new LanguageDAO()));
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/language/searchlanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
    public String initUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            String recid = (String) request.getParameter("recid");
            model.addAttribute("language", this.superService.getObjectById(new LanguageDAO(), new Integer(recid)));
            model.addAttribute("action", "update.io");
            model.addAttribute("mode", "update");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/language/addlanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("language") LanguageDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String forward = "redirect:/admin/language/initSearch.io";
        try {
            this.superService.updateObject(beanObj);
        } catch (Exception e) {
            logger.error("This is Error message", e);
            model.addAttribute("language", beanObj);
            model.addAttribute("action", "update.io");
            model.addAttribute("mode", "update");
            request.getSession().setAttribute("body", "/admin/language/addlanguage.jsp");
            forward = "admin/common";
        }

        return forward;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            String recid = (String) request.getParameter("recid");
            this.superService.deleteObjectById(new LanguageDAO(), new Integer(recid));
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return "redirect:/admin/language/initSearch.io";
    }

}
