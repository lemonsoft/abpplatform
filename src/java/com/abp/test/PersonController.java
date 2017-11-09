/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.test;

import com.abp.service.PersonService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/person/**")
public class PersonController {

    private PersonService personService;

    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService ps) {
        this.personService = ps;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
        request.getSession().setAttribute("body", "/admin/testpage/person.jsp");
        return "/common";
    }

    //For add and update person both
    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p) {

        if (p.getId() == 0) {
            //new person, add it

            this.personService.addPerson(p);
        } else {
            //existing person, call update
            this.personService.updatePerson(p);
        }

        return "redirect:/person/persons.io";

    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {

        this.personService.removePerson(id);
        return "redirect:/persons.io";
    }
    @RequestMapping("/delete")
    public String delete() {

        this.personService.removePerson(2);
        return "redirect:/person/persons.io";
    }

    @RequestMapping(value ="**/edit/{id}", method = RequestMethod.GET)
    public String editPerson(@PathVariable("id") int id,HttpServletRequest request, Model model) {
        
        System.out.println("edit mode "+id);
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        request.getSession().setAttribute("body", "/admin/testpage/person.jsp");
        return "/common";
    }
    @RequestMapping(value ="/editmode", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model) {
        
        System.out.println("edit mode ");
        model.addAttribute("person", this.personService.getPersonById(1));
        model.addAttribute("listPersons", this.personService.listPersons());
        request.getSession().setAttribute("body", "/admin/testpage/person.jsp");
        return "/common";
    }

}
