/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.monthlydashboard;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/admin/monthlydashboard")
public class MonthlyDashBoardController {

    private static final Logger logger = Logger.getLogger(MonthlyDashBoardController.class);
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

        model.addAttribute("monthlydashboarddao", new DisplayMonthlyDashboard());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/monthlydashboard/monthlydashboard.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, DisplayMonthlyDashboard beanObj, Model model) {

        System.out.println(" Sector " + beanObj.getSscid());
        System.out.println(" Month " + beanObj.getMonth());
        int i = 1;
        ArrayList dataall = new ArrayList();
        Map qpacks = new HashMap();
        qpacks.put("sscid", beanObj.getSscid());
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            
            while (itr.hasNext()) {
                QualificationPackDAO qpackdao = (QualificationPackDAO) itr.next();

                Map batchdo = new HashMap();
                batchdo.put("qpackId", qpackdao.getQpid());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new BatchesDAO(), batchdo);
                if (records2.size() > 0) {
                    Iterator itr2 = records2.iterator();
                    while (itr2.hasNext()) {
                        BatchesDAO data = (BatchesDAO) itr2.next();
                        DisplayMonthlyDashboard dispmdao = new DisplayMonthlyDashboard();
                        dispmdao.setSrno("" + i);
                        dispmdao.setTotalnostudent("" + getTotalNoStudent(data.getID()));
                        dispmdao.setNotappeared("" + getTotalNoStudent(data.getID()));
                        dispmdao.setPass("0");
                        dispmdao.setFail("0");
                        dispmdao.setJobrole(qpackdao.getQpackname());
                        dataall.add(dispmdao);
                        i++;
                    }
                }

            }
        }

        model.addAttribute("records", dataall);
        model.addAttribute("monthlydashboarddao", new DisplayMonthlyDashboard());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/monthlydashboard/monthlydashboard.jsp");
        return "admin/common";

    }

    public Map<Integer, String> getSectorSkillCouncil() {

        Map<Integer, String> states = new HashMap();
        List<SuperBean> records = this.superService.listAllObjects(new SSCDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                SSCDAO data = (SSCDAO) itr.next();
                states.put(data.getSscId(), data.getSscName());
            }
        }

        return states;
    }

    private int getTotalNoStudent(int batchid) {

        int totalusers = 0;
        Map batchdo = new HashMap();
        batchdo.put("batchid", batchid);
        List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new UserDAO(), batchdo);
        if (records2.size() > 0) {
            totalusers = records2.size();
        }

        return totalusers;
    }
}
