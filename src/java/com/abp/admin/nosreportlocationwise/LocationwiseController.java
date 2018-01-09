/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.nosreportlocationwise;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
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
@RequestMapping("/admin/locationwisenosreport")
public class LocationwiseController {

    private static final Logger logger = Logger.getLogger(LocationwiseController.class);

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

        model.addAttribute("locationnoswisedao", new LocationnoswiseDAO());
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/locationnoswise/locationnoswise.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, LocationnoswiseDAO beanObj, Model model) {

        System.out.println(" State " + beanObj.getState());
        System.out.println(" District " + beanObj.getDistrict());
        System.out.println(" Month " + beanObj.getMonth());

        ArrayList recordsall = new ArrayList();
        Map qpacks = new HashMap();
        qpacks.put("state_id", new Integer(beanObj.getState()));
        qpacks.put("district_id", new Integer(beanObj.getDistrict()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                LocationnoswiseDAO locdao = new LocationnoswiseDAO();
                BatchesDAO data = (BatchesDAO) itr.next();
                locdao.setJobrole(getQualificationpackById(data.getQpackId()));
                locdao.setTheorypass("--");
                locdao.setTheorypass("--");
                locdao.setVivafail("--");
                locdao.setVivapass("--");
                locdao.setNoswisepass("--");
                locdao.setNoswisepass("--");
                recordsall.add(locdao);
            }
        }

        model.addAttribute("records", recordsall);
        model.addAttribute("locationnoswisedao", new LocationnoswiseDAO());
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/locationnoswise/locationnoswise.jsp");
        return "admin/common";
    }

    public Map<Integer, String> getAllStatesValues() {

        Map<Integer, String> states = new HashMap();
        List<SuperBean> records = this.superService.listAllObjects(new StateDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                StateDAO data = (StateDAO) itr.next();
                states.put(data.getStateID(), data.getStateName());
            }
        }

        return states;
    }

    @RequestMapping(value = "/getDistricts", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getDistricts(@RequestParam("stateid") String stateid) {

        System.out.println("State ID::" + stateid);
        String districts = getAllDistricts(stateid);

        return districts;
    }

    public String getAllDistricts(String stateid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        List<SuperBean> records = this.superService.listAllObjects(new DistrictDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                DistrictDAO data = (DistrictDAO) itr.next();
                if (data.getState().getStateID() == Integer.parseInt(stateid)) {
                    jsonObj.append("ID", data.getDistrictID());
                    jsonObj.append("NAME", data.getDistrictName());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
    }

    private String getQualificationpackById(int qpackid) {

        String qpname = "";
        QualificationPackDAO qpackdao = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
        if (qpackdao != null) {
            qpname = qpackdao.getQpackname();
        }

        return qpname;
    }

}
