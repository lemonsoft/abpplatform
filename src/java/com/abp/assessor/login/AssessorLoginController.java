/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.assessor.login;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.result.AssesmentResult;
import com.abp.statedistrict.DistrictDAO;
import com.abp.statedistrict.StateDAO;
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
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/assessor/auth")
public class AssessorLoginController {

    private static final Logger logger = Logger.getLogger(AssessorLoginController.class);

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

    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("assessor/commonlogin");

        request.getSession().removeAttribute("assessorId");
        request.getSession().removeAttribute("assessor");

        System.out.println("Assessor login controller....");

        request.getSession().setAttribute("body", "/assessor/login/login.jsp");
        return model;
    }

    @RequestMapping(value = "/logincheck", method = {RequestMethod.POST})
    public String logincheck(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String forward = "redirect:/assessor/auth/dashboard.io";

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Map param = new HashMap();
            param.put("loginid", username);
            param.put("password", password);
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
            if (records.size() > 0) {
                AssessorDAO assessordo = (AssessorDAO) records.get(0);
                request.getSession().setAttribute("assessorId", assessordo.getAssessorid());
                request.getSession().setAttribute("assessor", assessordo.getFirstname());
                request.getSession().setAttribute("body", "/assessor/login/dashboard.jsp");

            } else {
                System.out.println("No Active Assessor");
                forward = "assessor/commonlogin";
                request.getSession().setAttribute("body", "/assessor/login/login.jsp");
            }
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return forward;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String forward = "assessor/common";

        try {
            ArrayList datalist = new ArrayList();
            Integer assessorid = (Integer) request.getSession().getAttribute("assessorId");
            AssessorDAO assessordo = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), assessorid);
            Map param2 = new HashMap();
            param2.put("assessorId", assessorid);
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
                        disp.setBatchID("" + batchdao.getID());
                        disp.setBatchid("" + batchdao.getBatch_id());
                        disp.setBatchsize("" + batchdao.getBatch_size());
                        disp.setTpname(batchdao.getTpName());
                        disp.setCenteraddr(batchdao.getCenterAddress());
                        disp.setStartdate(batchdao.getAssessmentStartDate());
                        disp.setEnddate(batchdao.getAssessmentEndDate());
                        disp.setAttendance("");
                        datalist.add(disp);

                        model.addAttribute("name", assessordo.getFirstname());
                        model.addAttribute("qualification", assessordo.getQualification());
                        model.addAttribute("state", getStateNameByID("" + assessordo.getStateid()));
                        model.addAttribute("district", getDistrictNameByID("" + assessordo.getDistrictid()));
                        model.addAttribute("emailid", assessordo.getEmailid());
                        model.addAttribute("records", datalist);
                    }

                }

            } else {
                System.out.println("No Active Batches");
            }
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/assessor/login/dashboard.jsp");

        return forward;
    }

    @RequestMapping(value = "/captureImage", method = RequestMethod.GET)
    public String captureImage(HttpServletRequest request, HttpServletResponse response, Model model) {

        String batchid = request.getParameter("batchid");

        model.addAttribute("batchid", batchid);

        request.getSession().setAttribute("body", "/assessor/login/captureimage.jsp");
        return "assessor/commonmodal";
    }

    @RequestMapping(value = "/takeattendance", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public @ResponseBody
    String takeattendance(@RequestParam("batchid") String batchid, HttpServletRequest request) {

        JSONObject jsonObj = new JSONObject();
        System.out.println("getting data from jquery----------------" + batchid);
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        Date currentdt = new Date();
        AssesmentResult assessmentdao = new AssesmentResult();
        assessmentdao.setBatchid(new Integer(batchid));
        assessmentdao.setStartdate(outFormat.format(currentdt));
        try {
            this.superService.saveObject(assessmentdao);
            jsonObj.append("status", "save");
        } catch (Exception e) {
            logger.error("This is Error message", e);
            jsonObj.append("status", "fail");
        }

        return jsonObj.toString();
    }

    public String getStateNameByID(String stateid) {
        StateDAO state = (StateDAO) this.superService.getObjectById(new StateDAO(), new Integer(stateid));
        return state.getStateName();
    }

    public String getDistrictNameByID(String districtid) {

        DistrictDAO district = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), new Integer(districtid));
        return district.getDistrictName();
    }
}
