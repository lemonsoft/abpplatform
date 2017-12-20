/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.assessor.login;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.batches.BatchesDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/assessor/auth")
public class AssessorLoginController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("assessor/commonlogin");

        System.out.println("Assessor login controller....");

        request.getSession().setAttribute("body", "/assessor/login/login.jsp");
        return model;
    }

    @RequestMapping(value = "/logincheck", method = {RequestMethod.POST})
    public String logincheck(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String forward = "assessor/common";
        boolean flag=false;
        ArrayList datalist = new ArrayList();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map param = new HashMap();
        param.put("loginid", username);
        param.put("password", password);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
        if (records.size() > 0) {
            AssessorDAO assessordo = (AssessorDAO) records.get(0);

            Map param2 = new HashMap();
            param2.put("assessorId", assessordo.getAssessorid());
            List<SuperBean> batches = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param2);
            if (batches.size() > 0) {
                Iterator itr = batches.iterator();
                while (itr.hasNext()) {
                    BatchesDAO batchdao = (BatchesDAO) itr.next();

                    String startdate = batchdao.getAssessmentStartDate();
                    String enddate = batchdao.getAssessmentEndDate();

                    SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
                    Date jodastarttime = outFormat.parse(startdate);
                    Date jodaendtime = outFormat.parse(enddate);
                    Date currentdt = new Date();
                    if (currentdt.after(jodastarttime) && currentdt.before(jodaendtime)) {
                        DisplayBatches disp = new DisplayBatches();
                        disp.setBatchid("" + batchdao.getBatch_id());
                        disp.setTpname(batchdao.getTpName());
                        disp.setCenteraddr(batchdao.getCenterAddress());
                        disp.setStartdate(batchdao.getAssessmentStartDate());
                        disp.setEnddate(batchdao.getAssessmentEndDate());
                        disp.setAttendance("");
                        flag=true;
                    }

                }

            }else{
                System.out.println("No Active Batches");
            }

        }else{
            System.out.println("No Active Assessor");
        }
        if(!flag){
            System.out.println("No Active Batches");
        }

        request.getSession().setAttribute("body", "/assessor/login/dashboard.jsp");

        return forward;
    }
}
