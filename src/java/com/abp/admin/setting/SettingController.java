/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.setting;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/admin/setting")
public class SettingController {

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

        model.addAttribute("setting", new SettingDAO());
        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/settings/settings.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String search(HttpServletRequest request, HttpServletResponse response, Model model, SettingDAO beanObj) {

        String getusername = request.getParameter("username");

        System.out.println(" Get username : " + getusername);
        String studentusername = beanObj.getStudentname();
        if(getusername!=null){
            studentusername =getusername;
        }
        
        ArrayList datastore = new ArrayList();
        Map param = new HashMap();
        param.put("username", studentusername);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        if (records.size() > 0) {

            SettingDAO usersetting = new SettingDAO();
            usersetting.setStudentname(studentusername);

            UserDAO userdao = (UserDAO) records.get(0);
            int userid = userdao.getID();
            int batchid = userdao.getBatchid();
            BatchesDAO batchdao = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), batchid);
            int totallogincount = batchdao.getLoginRestrict();

            Map param2 = new HashMap();
            param2.put("userid", userid);
            List<SuperBean> recordsurd = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param2);
            if (recordsurd.size() > 0) {
                UserResultDetailDAO userdetaildao = (UserResultDetailDAO) recordsurd.get(0);
                usersetting.setRecordid("" + userdetaildao.getID());
                if (totallogincount <= userdetaildao.getLogincount()) {

                    usersetting.setEnablelogin("disable");
                } else {
                    usersetting.setEnablelogin("enable");
                }

            }

            datastore.add(usersetting);

        }

        model.addAttribute("setting", beanObj);
        model.addAttribute("action", "search.io");
        model.addAttribute("records", datastore);

        request.getSession().setAttribute("body", "/admin/settings/settings.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initChange", method = RequestMethod.GET)
    public String initChange(HttpServletRequest request, HttpServletResponse response, Model model, SettingDAO beanObj) {

        String username = request.getParameter("username");
        String recid = request.getParameter("recid");
        String status = request.getParameter("status");
        UserResultDetailDAO userresultdao = (UserResultDetailDAO) this.superService.getObjectById(new UserResultDetailDAO(), new Integer(recid));
        if (status.equalsIgnoreCase("enable")) {
            userresultdao.setLogincount(3);
        }
        if (status.equalsIgnoreCase("disable")) {
            userresultdao.setLogincount(0);
        }
        this.superService.updateObject(userresultdao);
        beanObj.setStudentname(username);
        request.setAttribute("username", username);
        model.addAttribute("setting", beanObj);

        return "redirect:/admin/setting/search.io?username=" + username;
    }

    private boolean checkLoginCount(int userid, int totallogincount) {

        boolean flag = false;

        Map param = new HashMap();
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
        if (records.size() > 0) {
            UserResultDetailDAO userdetaildao = (UserResultDetailDAO) records.get(0);
            if (totallogincount <= userdetaildao.getLogincount()) {

                flag = true;
            }

        }

        return flag;
    }

}
