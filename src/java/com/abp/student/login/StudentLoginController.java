/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.student.login;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.generateqp.QuestionPaperDAO;
import com.abp.admin.language.LanguageDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.ObjectNotFoundException;
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
@RequestMapping("/student/login")
public class StudentLoginController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("language", new LanguageDAO());
        model.addAttribute("action", "add.io");
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/language/addlanguage.jsp");
        return "/common";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("student/commonlogin");

        System.out.println("Student login controller....");

        request.getSession().setAttribute("body", "/student/login/login.jsp");
        return model;
    }

    @RequestMapping(value = "/logincheck", method = {RequestMethod.POST})
    public String logincheck(HttpServletRequest request, HttpServletResponse response, Model model) {

        String forward = "student/common";
        boolean flag = true;
        String userIpAddress = getClientIp(request);
        String browser = request.getHeader("User-Agent");

        System.out.println(" IP Address : " + userIpAddress);
        System.out.println(" Browser : " + browser);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (password.matches("[0-9]+")) {
            try {
                Map param = new HashMap();
                param.put("username", username);
                param.put("enrollmentno", new Integer(password));
                List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
                if (records.size() > 0) {
                    UserDAO userdo = (UserDAO) records.get(0);
                    BatchesDAO batch = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), userdo.getBatchid());
                    String startdate = batch.getAssessmentStartDate();
                    String enddate = batch.getAssessmentEndDate();

                    SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
                    Date jodastarttime = outFormat.parse(startdate);
                    Date jodaendtime = outFormat.parse(enddate);
                    Date currentdt = new Date();

                    if (currentdt.after(jodastarttime) && currentdt.before(jodaendtime)) {

                        try {
                            UserResultDetailDAO userresultdao = (UserResultDetailDAO) this.superService.getObjectById(new UserResultDetailDAO(), userdo.getID());
                            if (userresultdao != null) {
                                /*
                            Check user login attempt
                                 */
                                if (batch.getLoginRestrict() > userresultdao.getLogincount()) {
                                    userresultdao.setBrowserversion(browser);
                                    userresultdao.setIpaddress(userIpAddress);
                                    int logincount = userresultdao.getLogincount() + 1;
                                    userresultdao.setLogincount(logincount);
                                    this.superService.updateObject(userresultdao);
                                } else {

                                    model.addAttribute("error", "Maximum Login Attempt Reached Contact Admin.");
                                    flag = false;
                                }
                            } else {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date datenow = new Date();
                                QuestionPaperDAO qpaperdao = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), batch.getQuestionPaperId());
                                UserResultDetailDAO userresultdaonew = new UserResultDetailDAO();
                                userresultdaonew.setBatchid(batch.getID());
                                userresultdaonew.setBrowserversion(browser);
                                userresultdaonew.setIpaddress(userIpAddress);
                                userresultdaonew.setLogincount(1);
                                userresultdaonew.setExamstatus("started");
                                userresultdaonew.setExamstarttime(sdf.format(datenow));
                                userresultdaonew.setTotaltime("" + qpaperdao.getTotaltime());
                                userresultdaonew.setUserid(userdo.getID());
                                this.superService.saveObject(userresultdaonew);

                            }
                        } catch (ObjectNotFoundException ofe) {

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date datenow = new Date();
                            QuestionPaperDAO qpaperdao = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), batch.getQuestionPaperId());
                            UserResultDetailDAO userresultdaonew = new UserResultDetailDAO();
                            userresultdaonew.setBatchid(batch.getID());
                            userresultdaonew.setBrowserversion(browser);
                            userresultdaonew.setIpaddress(userIpAddress);
                            userresultdaonew.setLogincount(1);
                            userresultdaonew.setExamstatus("started");
                            userresultdaonew.setExamstarttime(sdf.format(datenow));
                            userresultdaonew.setTotaltime("" + qpaperdao.getTotaltime());
                            userresultdaonew.setUserid(userdo.getID());
                            this.superService.saveObject(userresultdaonew);

                        }

                    } else {

                        model.addAttribute("error", "No Assessment Today");
                        flag = false;
                    }
                } else {

                    model.addAttribute("error", "User Not Found");
                    flag = false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
                model.addAttribute("error", "Exception");

            }
        } else {
            model.addAttribute("error", "Incorrect Password");
            flag = false;

            //error msg
        }
        if (flag) {
            request.getSession().setAttribute("body", "/student/login/dashboard.jsp");
        } else {
            forward = "student/commonlogin";
            request.getSession().setAttribute("body", "/student/login/login.jsp");
        }

        return forward;
    }

    private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}
