/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.statedistrict;

import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/statedistricts")
public class StateDistrictController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("state", new StateDAO());
        model.addAttribute("action", "initSearch.io");
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/statedistricts/districts.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initDistrict", method = RequestMethod.GET)
    public String initDistrict(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("district", new DistrictDAO());
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("action", "add.io");
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/statedistricts/adddistricts.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/saveState", method = RequestMethod.GET)
    public @ResponseBody
    String saveState(@RequestParam("name") String name) {
        String status = "Record Save Successfully";
        try {
            if (!name.isEmpty() && name != null) {
                StateDAO stateObj = new StateDAO();
                stateObj.setStateName(name);
                this.superService.saveObject(stateObj);
            }
        } catch (Exception e) {
            status = "Record Save Failed";
        }

        return status;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("district") DistrictDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        StateDAO stateObj = new StateDAO();
        stateObj.setStateID(Integer.parseInt(beanObj.getState_id()));
        beanObj.setState(stateObj);
        stateObj.getDistricts().add(beanObj);

        this.superService.saveObject(beanObj);

        return "redirect:/admin/statedistricts/init.io";
    }
//

    @RequestMapping(value = "/initSearch", method = {RequestMethod.GET, RequestMethod.POST})
    public String initSearch(@ModelAttribute("state") StateDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        System.out.println("State ...." + beanObj.getStateName());
        Map param = new HashMap();
        //param.put("state_id", beanObj.getStateName());
        List<DistrictDAO> data = new ArrayList<>();
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new DistrictDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {
            DistrictDAO dist = (DistrictDAO) itr.next();
            if (beanObj!=null&& beanObj.getStateName() != null&&!beanObj.getStateName().isEmpty() ) {
                if (dist.getState().getStateID() == Integer.parseInt(beanObj.getStateName())) {
                    data.add(dist);
                }
            } else {
                data.add(dist);
            }

        }
        model.addAttribute("records", data);
        model.addAttribute("state", new StateDAO());
        model.addAttribute("action", "initSearch.io");
        model.addAttribute("allstates", getAllStatesValues());
        request.getSession().setAttribute("body", "/admin/statedistricts/districts.jsp");
        return "admin/common";
    }
//

    @RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
    public String initUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = (String) request.getParameter("recid");
        DistrictDAO dist = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), new Integer(recid));
        DistrictDAO distSend = new DistrictDAO();
        distSend.setDistrictID(dist.getDistrictID());
        distSend.setDistrictName(dist.getDistrictName());
        distSend.setState_id("" + dist.getState().getStateID());
        model.addAttribute("district", distSend);
        model.addAttribute("action", "update.io");
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("mode", "update");
        request.getSession().setAttribute("body", "/admin/statedistricts/adddistricts.jsp");
        return "admin/common";
    }
//

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("district") DistrictDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String forward = "redirect:/admin/statedistricts/init.io";
        try {

            StateDAO stateObj = (StateDAO) this.superService.getObjectById(new StateDAO(), new Integer(beanObj.getState_id()));
            System.out.println(stateObj.getStateName()+":::::"+beanObj.getDistrictID() + ":::::::::" + beanObj.getDistrictName());
            Iterator itr = stateObj.getDistricts().iterator();
            while (itr.hasNext()) {
                DistrictDAO ddobj = (DistrictDAO) itr.next();
                if (ddobj.getDistrictID() == beanObj.getDistrictID()) {
                    //stateObj.getDistricts().remove(ddobj);
                    ddobj.setDistrictName(beanObj.getDistrictName());
                    System.out.println(beanObj.getDistrictID() + ":::::::::" + beanObj.getDistrictName());
                }
            }

            beanObj.setState(stateObj);
            //stateObj.getDistricts().add(beanObj);
            this.superService.updateObject(stateObj);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("district", beanObj);
            model.addAttribute("action", "update.io");
            model.addAttribute("allstates", getAllStatesValues());
            model.addAttribute("mode", "update");
            request.getSession().setAttribute("body", "/admin/statedistricts/adddistricts.jsp");
            forward = "admin/common";
        }

        return forward;
    }
//

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = (String) request.getParameter("recid");

        DistrictDAO dist = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), new Integer(recid));
//       this.superService.deleteObjectById(new DistrictDAO(), new Integer(recid));
        StateDAO stateObj = (StateDAO) this.superService.getObjectById(new StateDAO(), dist.getState().getStateID());
        List<DistrictDAO> myList = new CopyOnWriteArrayList<DistrictDAO>();
        myList =stateObj.getDistricts();
        Iterator itr = myList.iterator();
        while (itr.hasNext()) {
            DistrictDAO ddobj = (DistrictDAO) itr.next();
            if (ddobj.getDistrictID() == dist.getDistrictID()) {
                //stateObj.getDistricts().remove(ddobj);
                System.out.println(ddobj.getDistrictID() + ":::::::::" + ddobj.getDistrictName());
                this.superService.deleteObject(ddobj);
            }
        }

        return "redirect:/admin/statedistricts/init.io";
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
}
