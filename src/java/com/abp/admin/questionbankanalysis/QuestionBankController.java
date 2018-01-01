/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.questionbankanalysis;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/qbankanalysis")
public class QuestionBankController {

    private static final Logger logger = Logger.getLogger(QuestionBankController.class);
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

        model.addAttribute("qbankdao", new QuestionBankAnalysisDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/qbankanalysis/qbankanalysis.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, QuestionBankAnalysisDAO beanObj, Model model)throws Exception {

        System.out.println(" Sector " + beanObj.getSscid());
        System.out.println(" Job role " + beanObj.getQpid());
        System.out.println(" Month " + beanObj.getMonth());
        String seldate=beanObj.getMonth();
        Map qpacks = new HashMap();
        qpacks.put("qpackId", new Integer(beanObj.getQpid()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                BatchesDAO data = (BatchesDAO) itr.next();
                String startdate = data.getAssessmentStartDate();
                String pattern = "yyyy-MM";
                SimpleDateFormat sdformat = new SimpleDateFormat(pattern);
                String stdate = sdformat.format(startdate);
                if (seldate.equals(stdate)) {

                    System.out.println(sdformat.parse(startdate)+"Test Date Code :  :  : "+seldate);
                }

            }
        }

        model.addAttribute("qbankdao", beanObj);
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/qbankanalysis/qbankanalysis.jsp");
        return "admin/common";

    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
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

    public Map<Integer, String> getQualificationPacks(String ssc_id) {

        Map qpacks = new HashMap();
        qpacks.put("sscid", new Integer(ssc_id));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QualificationPackDAO data = (QualificationPackDAO) itr.next();
                qpacks.put(data.getQpid(), data.getQpackname());
            }
        }

        return qpacks;
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
