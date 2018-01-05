/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.locationanalysis;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.admin.ssc.SSCDAO;
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
@RequestMapping("/admin/locationwise")
public class LocationWiseAnalysis {

    private static final Logger logger = Logger.getLogger(LocationWiseAnalysis.class);
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

        model.addAttribute("locationdao", new LocationWiseDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/locationwise/Locationwiseanalysis.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, Model model, LocationWiseDAO locationdao) {

        System.out.println(" Sector " + locationdao.getSscid());
        System.out.println(" State " + locationdao.getState());
        System.out.println(" Month " + locationdao.getMonth());

        ArrayList disprecord=new ArrayList();
        int i=1;
        Map param = new HashMap();
        param.put("state_id", Integer.parseInt(locationdao.getState()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                
                BatchesDAO batchdao = (BatchesDAO) itr.next();
                LocationWiseDAO locatdao=new LocationWiseDAO();
                locatdao.setSrno(""+i);
                locatdao.setLocation(getStateIdByName(Integer.parseInt(locationdao.getState())));
                locatdao.setTpname(batchdao.getTpName());
                locatdao.setJobrole(getQualityPackName(batchdao.getQpackId()));
                locatdao.setNocandidateassesed("0");
                locatdao.setPass("0");
                locatdao.setFail("0");
                
                Map param2 = new HashMap();
                param2.put("ID", batchdao.getID());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new UserDAO(), param2);
                if (records2.size() > 0) {
                    Iterator itr2 = records2.iterator();
                    while (itr2.hasNext()) {
                        UserDAO userdao = (UserDAO) itr2.next();

                    }

                }
                disprecord.add(locatdao);
                i++;
            }
        }
        model.addAttribute("records", disprecord);
        model.addAttribute("locationdao", new LocationWiseDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/locationwise/Locationwiseanalysis.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String statename = getStateBySSCID(Integer.parseInt(sscid));

        return statename;
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

    private String getStateBySSCID(int sscid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        HashMap<String, String> states = new HashMap();
        System.out.println("   Sector skill counsil ID :::: " + sscid);
        try {

            Map param = new HashMap();
            param.put("sscid", "" + sscid);
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), param);
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    QualificationPackDAO qpackdata = (QualificationPackDAO) itr.next();

                    Map param2 = new HashMap();
                    param2.put("qpackId", qpackdata.getQpid());
                    List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param2);
                    if (records2.size() > 0) {
                        Iterator itr2 = records2.iterator();
                        while (itr2.hasNext()) {
                            BatchesDAO data = (BatchesDAO) itr2.next();
                            states.put("" + data.getState_id(), getStateIdByName(data.getState_id()));
                        }
                    }

                }
            }
            for (Map.Entry<String, String> entry : states.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                jsonObj.append("ID", entry.getKey());
                jsonObj.append("NAME", entry.getValue());
                jsonarr.put(jsonObj);
                jsonObj = new JSONObject();
            }

        } catch (Exception e) {

            e.printStackTrace();
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

    private String getStateIdByName(int stateid) {

        String statename = "";
        StateDAO statedao = (StateDAO) this.superService.getObjectById(new StateDAO(), stateid);
        if (statedao != null) {
            statename = statedao.getStateName();
        }
        return statename;
    }

    private String getMonthWiseDetails(int month) {

        return "";
    }

    private String getQualityPackName(int qpackid) {

        String qpackname = "";
        QualificationPackDAO qpackdao = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
        if (qpackdao != null) {
            qpackname = qpackdao.getQpackname();
        }
        return qpackname;

    }

    private boolean isAssessed(int userid) {

        boolean flag = false;
        UserResultDetailDAO userresultdao = (UserResultDetailDAO) this.superService.getObjectById(new UserResultDetailDAO(), userid);
        if (userresultdao != null) {
            flag = true;
        }

        return flag;
    }
    
    
    private boolean isPassFailed(int userid){
        
         boolean flag = false;
        
        
         return flag;
    }

}
