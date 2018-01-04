/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.consolidatejobroleresult;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.statedistrict.DistrictDAO;
import com.abp.statedistrict.StateDAO;
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
@RequestMapping("/admin/jobroleresult")
public class ConsolidateJobRoleResult {

    private static final Logger logger = Logger.getLogger(ConsolidateJobRoleResult.class);
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

        model.addAttribute("jobroledao", new DisplayJobRoleResult());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/consolidatejobroleresult/jobroleresult.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, DisplayJobRoleResult beanObj, Model model) {

        System.out.println(" Sector " + beanObj.getSscid());
        System.out.println(" Job role " + beanObj.getQpackid());
        System.out.println(" Month " + beanObj.getMonth());

        ArrayList recordss=new ArrayList();
        QualificationPackDAO beanQpackObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(beanObj.getQpackid()));
        

        Map qpacks = new HashMap();
        qpacks.put("qpackId", new Integer(beanObj.getQpackid()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                BatchesDAO data = (BatchesDAO) itr.next();
                DisplayJobRoleResult dispjobroledao = new DisplayJobRoleResult();
                dispjobroledao.setJobrole(beanQpackObj.getQpackname());
                dispjobroledao.setTpname(data.getTpName());
                dispjobroledao.setBatchid("" + data.getBatch_id());
                dispjobroledao.setCenterid("" + data.getCenter_id());
                dispjobroledao.setState(getStateNameById(data.getState_id()));
                dispjobroledao.setLocation(getDistrictNameById(data.getDistrict_id()));
                dispjobroledao.setAssesmentdate(data.getAssessmentEndDate());
                dispjobroledao.setAssesorname(getAssessorNameById(data.getAssessorId()));
                dispjobroledao.setTotalnostudent(""+getTotalStudent(data.getID()));
                recordss.add(dispjobroledao);
            }
        }
        model.addAttribute("records", recordss);
        model.addAttribute("jobroledao", new DisplayJobRoleResult());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/consolidatejobroleresult/jobroleresult.jsp");
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

    private String getStateNameById(int stateid) {

        String statename = "";
        StateDAO beanQpackObj = (StateDAO) this.superService.getObjectById(new StateDAO(), stateid);
        if (beanQpackObj != null) {
            statename = beanQpackObj.getStateName();
        }
        return statename;
    }

    private String getDistrictNameById(int districtid) {

        String districtname = "";
        DistrictDAO beanQpackObj = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), districtid);
        if (beanQpackObj != null) {
            districtname = beanQpackObj.getDistrictName();
        }
        return districtname;
    }

    private String getAssessorNameById(int assessorid) {

        String assessorname = "";
        AssessorDAO beanQpackObj = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), assessorid);
        if (beanQpackObj != null) {
            assessorname = beanQpackObj.getFirstname();
        }
        return assessorname;
    }

    private int getTotalStudent(int batchid) {

        System.out.println("  batchid  : : : "+batchid);
        int totalstudent = 0;
        Map param = new HashMap();
        param.put("batchid", batchid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        if (records.size() > 0) {
            totalstudent = records.size();
        }

        return totalstudent;
    }
}
