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
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.result.AssesmentLogDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(StudentLoginController.class);
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
                    model.addAttribute("father", userdo.getFathername());
                    model.addAttribute("state", userdo.getStates());
                    model.addAttribute("district", userdo.getDistrict());
                    model.addAttribute("enrollno", userdo.getEnrollmentno());

                    BatchesDAO batch = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), userdo.getBatchid());
                    String startdate = batch.getAssessmentStartDate();
                    String enddate = batch.getAssessmentEndDate();

                    SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
                    Date jodastarttime = outFormat.parse(startdate);
                    Date jodaendtime = outFormat.parse(enddate);
                    Date currentdt = new Date();

                    request.getSession().setAttribute("batchid", batch.getID());
                    request.getSession().setAttribute("qpaperid", batch.getQuestionPaperId());

                    if (currentdt.after(jodastarttime) && currentdt.before(jodaendtime)) {

                        try {
                            Map param2 = new HashMap();
                            param2.put("userid", userdo.getID());
                            List<SuperBean> userresultdaolist = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param2);

                            if (userresultdaolist.size() > 0) {
                                UserResultDetailDAO userresultdao = (UserResultDetailDAO) userresultdaolist.get(0);
                                /*
                                Check user login attempt
                                 */
                                if (batch.getLoginRestrict() > userresultdao.getLogincount()) {

                                    userresultdao.setBrowserversion(browser);
                                    userresultdao.setIpaddress(userIpAddress);
                                    int logincount = userresultdao.getLogincount() + 1;
                                    userresultdao.setLogincount(logincount);
                                    this.superService.updateObject(userresultdao);
                                    addAssesmentLog(userresultdao.getID(), userdo.getID(), "Login");
                                    request.getSession().setAttribute("userresultdetailid", userresultdao.getID());
                                    request.getSession().setAttribute("userid", userdo.getID());
                                    request.getSession().setAttribute("questionids", userresultdao.getQuestionids());

                                } else {

                                    model.addAttribute("error", "Maximum Login Attempt Reached Contact Admin.");
                                    flag = false;
                                }
                            } else {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
                                Date datenow = new Date();
                                QuestionPaperDAO qpaperdao = (QuestionPaperDAO) this.superService.getObjectByIdGet(new QuestionPaperDAO(), batch.getQuestionPaperId());
                                if (qpaperdao != null) {
                                    UserResultDetailDAO userresultdaonew = new UserResultDetailDAO();
                                    userresultdaonew.setBatchid(batch.getID());
                                    userresultdaonew.setBrowserversion(browser);
                                    userresultdaonew.setIpaddress(userIpAddress);
                                    userresultdaonew.setLogincount(1);
                                    userresultdaonew.setExamstatus("started");
                                    userresultdaonew.setExamstarttime(sdf.format(datenow));
                                    userresultdaonew.setTotaltime("" + qpaperdao.getTotaltime() + ":00");
                                    userresultdaonew.setTimetaken("" + qpaperdao.getTotaltime() + ":00");
                                    userresultdaonew.setUserid(userdo.getID());
                                    if (qpaperdao.getIsrandom().equals("on")) {
                                        String questionafterrandom = randomiseQuestion(qpaperdao.getQuestionids());
                                        userresultdaonew.setQuestionids(questionafterrandom);
                                        request.getSession().setAttribute("questionids", questionafterrandom);
                                    } else {
                                        userresultdaonew.setQuestionids(qpaperdao.getQuestionids());
                                        request.getSession().setAttribute("questionids", qpaperdao.getQuestionids());
                                    }

                                    this.superService.saveObject(userresultdaonew);
                                    addAssesmentLog(userresultdaonew.getID(), userdo.getID(), "Login");
                                    request.getSession().setAttribute("userresultdetailid", userresultdaonew.getID());
                                    request.getSession().setAttribute("userid", userdo.getID());

                                }

                            }
                        } catch (Exception ofe) {

                            ofe.printStackTrace();
                            flag = false;
                            model.addAttribute("error", "Exception");

                        }
                        model.addAttribute("qpack", getQualityPackName(batch.getQpackId()));
                        model.addAttribute("assesmentdate", batch.getAssessmentStartDate());
                        model.addAttribute("centercontactno", batch.getCenterContactno());
                        model.addAttribute("centeraddr", batch.getCenterAddress());
                        model.addAttribute("ssc", userdo.getEnrollmentno());
                        model.addAttribute("trainingpartner", batch.getTpName());

                    } else {

                        model.addAttribute("error", "No Assessment Today");
                        flag = false;
                    }

                } else {

                    model.addAttribute("error", "User Not Found");
                    flag = false;
                }

            } catch (Exception e) {
                logger.error("This is Error message", e);
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

    @RequestMapping(value = "/closeexam", method = {RequestMethod.POST, RequestMethod.GET})
    public String closeexam(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            request.getSession().removeAttribute("batchid");
            request.getSession().removeAttribute("qpaperid");
            request.getSession().removeAttribute("userresultdetailid");
            request.getSession().removeAttribute("userid");
            request.getSession().removeAttribute("questionids");

        } catch (Exception e) {
            logger.error("This is Error message", e);

        }

        request.getSession().setAttribute("body", "/student/login/login.jsp");
        return "student/commonlogin";
    }

    @RequestMapping(value = "/finishexam", method = {RequestMethod.POST, RequestMethod.GET})
    public String finishexam(HttpServletRequest request, HttpServletResponse response, Model model) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        Date datenow = new Date();
        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");

        try {
            Map param2 = new HashMap();
            param2.put("ID", userresultdetailid);
            param2.put("userid", userid);
            List<SuperBean> userresultdaolist = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param2);
            if (userresultdaolist.size() > 0) {
                UserResultDetailDAO userdao = (UserResultDetailDAO) userresultdaolist.get(0);
                userdao.setExamendtime(sdf.format(datenow));
                userdao.setExamstatus("completed");
                this.superService.updateObject(userdao);
            }

            addAssesmentLog(userresultdetailid, userid, "Exam Finished");

            request.getSession().removeAttribute("batchid");
            request.getSession().removeAttribute("qpaperid");
            request.getSession().removeAttribute("userresultdetailid");
            request.getSession().removeAttribute("userid");
            request.getSession().removeAttribute("questionids");

        } catch (Exception e) {
            logger.error("This is Error message", e);

        }

        request.getSession().setAttribute("body", "/student/login/login.jsp");
        return "student/commonlogin";
    }

    @RequestMapping(value = "/examinstruction", method = {RequestMethod.POST, RequestMethod.GET})
    public String examInstruction(HttpServletRequest request, HttpServletResponse response, Model model) {

        int userresultdetailid = (int) request.getSession().getAttribute("userresultdetailid");
        int userid = (int) request.getSession().getAttribute("userid");
        try {
            addAssesmentLog(userresultdetailid, userid, "Reached Exam Insructions");

        } catch (Exception e) {
            logger.error("This is Error message", e);

        }

        request.getSession().setAttribute("body", "/student/instruction/examinstruction.jsp");
        return "student/common";
    }

    private void addAssesmentLog(int userresultdetailid, int userid, String action) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        Date datenow = new Date();
        AssesmentLogDAO asseslog = new AssesmentLogDAO();
        asseslog.setActiontaken(action);
        asseslog.setUserresultdetailid(userresultdetailid);
        asseslog.setUserid(userid);
        asseslog.setLogactiondate(sdf.format(datenow));
        try {
            this.superService.saveObject(asseslog);

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
    }

    private String getQualityPackName(int qpackid) {

        String qpackname = "";
        try {
            QualificationPackDAO qpObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
            qpackname = qpObj.getQpackname();
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return qpackname;
    }

    private String getSectorSkillName(int sscid) {

        String sscname = "";
        try {
            SSCDAO sscObj = (SSCDAO) this.superService.getObjectById(new SSCDAO(), sscid);
            sscname = sscObj.getSscName();
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return sscname;
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

    public String randomiseQuestion(String questionids) {

        String randomquestion = "";
        String[] questionid = questionids.split(",");
        ArrayList<String> questionidss = new ArrayList<String>(Arrays.asList(questionid));
        long seed = System.nanoTime();
        Collections.shuffle(questionidss, new Random(seed));
        String[] questionrandom = new String[questionidss.size()];
        questionrandom = questionidss.toArray(questionrandom);
        for (int i = 0; i < questionrandom.length; i++) {
            randomquestion = randomquestion + questionrandom[i] + ",";
        }
        randomquestion = randomquestion.substring(0, randomquestion.length() - 1);

        return randomquestion;
    }

}
