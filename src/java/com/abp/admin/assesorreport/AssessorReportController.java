/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.assesorreport;

import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
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
@RequestMapping("/admin/assesorreport")
public class AssessorReportController {

    private static final Logger logger = Logger.getLogger(AssessorReportController.class);
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

        model.addAttribute("assessordao", new AssessorReportDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/assessorreport/assessorreport.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response,AssessorReportDAO assesordao, Model model) {
        
        System.out.println("Sector Skill Council : : : : "+assesordao.getSscid());
        System.out.println("Month : : : : "+assesordao.getMonth());
        
        model.addAttribute("assessordao", new AssessorReportDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/assessorreport/assessorreport.jsp");
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

    public String getQualificationPackByID(String sscid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        try {
            Map param = new HashMap();
            param.put("sscid", sscid);
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), param);
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    QualificationPackDAO data = (QualificationPackDAO) itr.next();
                    if (data.getSscid().equals(sscid)) {
                        jsonObj.append("ID", data.getQpid());
                        jsonObj.append("NAME", data.getQpackname());
                        jsonarr.put(jsonObj);
                    }

                    jsonObj = new JSONObject();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

}
