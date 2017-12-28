/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.monitorassesment;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.result.TheoryWiseResultDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.admin.resultdisplay.DisplayTheoryResult;
import com.abp.admin.ssc.SSCDAO;
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
@RequestMapping("/admin/monitor")
public class MonitorAssesmentController {

    private static final Logger logger = Logger.getLogger(MonitorAssesmentController.class);

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        try {
            ArrayList record = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentdt = new Date();
            List<SuperBean> batchdaolist = this.superService.listAllObjects(new BatchesDAO());
            if (batchdaolist.size() > 0) {
                Iterator itr = batchdaolist.iterator();
                while (itr.hasNext()) {
                    BatchesDAO daoobj = (BatchesDAO) itr.next();
                    Date startdate = sdf.parse(daoobj.getAssessmentStartDate());
                    Date enddate = sdf.parse(daoobj.getAssessmentEndDate());

                    System.out.println("Start Date" + startdate);
                    System.out.println("End Date" + enddate);
                    System.out.println("Current  Date" + currentdt);

                    if (currentdt.after(startdate) && currentdt.before(enddate)) {
                        System.out.println("Records.... ");
                        String[] names = getQpackNameById(daoobj.getQpackId());
                        MonitorAssesmentDAO monitordao = new MonitorAssesmentDAO();
                        monitordao.setBatchid("" + daoobj.getID());
                        monitordao.setBatchID("" + daoobj.getBatch_id());
                        monitordao.setJobrole(names[0]);
                        monitordao.setSsc(names[1]);
                        monitordao.setTpname(daoobj.getTpName());
                        monitordao.setCenteraddr(daoobj.getCenterAddress());
                        monitordao.setStudentno("" + daoobj.getBatch_size());
                        monitordao.setActions("View");
                        record.add(monitordao);
                    }
                }

            }

            model.addAttribute("records", record);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/monitor/monitortoday.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/getUserResult", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public @ResponseBody
    String getUserResult(@RequestParam("batchid") String batchid, HttpServletRequest request) {
        System.out.println("getting data from jquery----------------" + batchid);

        JSONArray jsonarr = new JSONArray();

        try {
            Map param = new HashMap();
            param.put("batchid", new Integer(batchid));
            List<SuperBean> userdaolist = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
            if (userdaolist.size() > 0) {
                Iterator itr = userdaolist.iterator();
                while (itr.hasNext()) {
                    JSONObject jsonObj = new JSONObject();
                    UserDAO userdao = (UserDAO) itr.next();
                    jsonObj.append("enrollmentno", userdao.getEnrollmentno());
                    jsonObj.append("traineename", userdao.getTraineename());
                    Map paramurd = new HashMap();
                    paramurd.put("userid", userdao.getID());
                    List<SuperBean> urddaolist = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), paramurd);
                    if (urddaolist.size() > 0) {
                        UserResultDetailDAO urdao = (UserResultDetailDAO) urddaolist.get(0);
                        jsonObj.append("examstatus", urdao.getExamstatus());
                        jsonObj.append("view", urdao.getUserid() + "," + urdao.getID());
                    } else {
                        jsonObj.append("examstatus", "Not Started");
                        jsonObj.append("view", "NA");
                    }
                    jsonarr.put(jsonObj);
                }
            }
            System.out.println("jsonarr.toString()" + jsonarr.toString());
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return jsonarr.toString();
    }

    @RequestMapping(value = "/totalreport", method = RequestMethod.GET)
    public String totalreport(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            String userresultdetailid = request.getParameter("userresultid");
            String userid = request.getParameter("userid");

            System.out.println(userid + " : " + userresultdetailid);
            UserDAO userdao = (UserDAO) this.superService.getObjectById(new UserDAO(), new Integer(userid));

            UserResultDetailDAO userdetaildao = (UserResultDetailDAO) this.superService.getObjectById(new UserResultDetailDAO(), new Integer(userresultdetailid));

            Map datatheorywise = displayTheoryWiseresult(new Integer(userid), new Integer(userresultdetailid));

            model.addAttribute("studentname", userdao.getTraineename());
            model.addAttribute("enrollmentno", userdao.getEnrollmentno());
            model.addAttribute("startdatetime", userdetaildao.getExamstarttime());
            model.addAttribute("enddatetime", userdetaildao.getExamendtime());
            model.addAttribute("totaltime", userdetaildao.getTotaltime());
            model.addAttribute("timetaken", userdetaildao.getTimetaken());
            model.addAttribute("ipaddress", userdetaildao.getIpaddress());
            model.addAttribute("browser", userdetaildao.getBrowserversion());
            model.addAttribute("examstatus", userdetaildao.getExamstatus());
            String[] questionid = userdetaildao.getQuestionids().split(",");
            int totalquestions = questionid.length;
            int answered = getTotalQuestionAnswer(new Integer(userid), new Integer(userresultdetailid));
            model.addAttribute("remainingquestion", (totalquestions - answered));
            model.addAttribute("totalanswer", getTotalQuestionAnswer(new Integer(userid), new Integer(userresultdetailid)));

            model.addAttribute("datatheorywise", datatheorywise.get("datalist"));
            model.addAttribute("totalmarks", datatheorywise.get("totalmarks"));
            model.addAttribute("totaltheorymarks", getTotalTheoryMarks(new Integer(userid), new Integer(userresultdetailid)));

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/monitor/viewresult.jsp");
        return "admin/commonmodal";
    }

    private Map displayTheoryWiseresult(int userid, int userresultdetailid) {

        ArrayList theorydao = new ArrayList();
        int totalmarks = 0;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {
            DisplayTheoryResult displayObj = new DisplayTheoryResult();
            TheoryWiseResultDAO theorywisedao = (TheoryWiseResultDAO) itr.next();
            displayObj.setSno("" + theorywisedao.getQuestionno());
            QuestionDAO questiondao = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), theorywisedao.getQuestionid());
            displayObj.setQuestion(questiondao.getQuestion_title());
            displayObj.setOption1(questiondao.getOption1());
            displayObj.setOption2(questiondao.getOption2());
            displayObj.setOption3(questiondao.getOption3());
            displayObj.setOption4(questiondao.getOption4());
            displayObj.setCorrectanswer("Option " + questiondao.getCorrect_option());
            displayObj.setSelectedanswer("Option " + theorywisedao.getCorrectanswer());
            displayObj.setReviewlater(theorywisedao.getReviewlater());
            displayObj.setTimetaken(theorywisedao.getTimetaken());
            displayObj.setMarks("" + questiondao.getMarks());
            displayObj.setScoredmarks("" + getMarksByQuestionID(theorywisedao.getQuestionid(), theorywisedao.getCorrectanswer()));
            theorydao.add(displayObj);
            totalmarks = totalmarks + questiondao.getMarks();
        }
        Map data = new HashMap();
        data.put("datalist", theorydao);
        data.put("totalmarks", totalmarks);

        return data;
    }

    public int getTotalTheoryMarks(int userid, int userresultdetailid) {

        int totaltheorymarks = 0;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {

            TheoryWiseResultDAO theorywisedao = (TheoryWiseResultDAO) itr.next();
            totaltheorymarks = totaltheorymarks + getMarksByQuestionID(theorywisedao.getQuestionid(), theorywisedao.getCorrectanswer());
            System.out.println(" totaltheorymarks   " + totaltheorymarks);
        }

        System.out.println(" total totaltheorymarks   " + totaltheorymarks);

        return totaltheorymarks;
    }

    public int getMarksByQuestionID(int questionid, String selectOption) {

        int marks = 0;
        QuestionDAO questiondao = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), questionid);
        System.out.println(" correct Option  " + questiondao.getCorrect_option());
        System.out.println(" select Option  " + selectOption);
        if (questiondao.getCorrect_option().equalsIgnoreCase(selectOption)) {
            marks = questiondao.getMarks();
        }

        return marks;
    }

    private String[] getQpackNameById(int qpackid) {

        String name[] = new String[2];
        QualificationPackDAO qpackdao = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
        if (qpackdao != null) {
            name[0] = qpackdao.getQpackname();
            System.out.println("+++++++++++++++++++++" + qpackdao.getSscid());
            SSCDAO sscdao = (SSCDAO) this.superService.getObjectById(new SSCDAO(), new Integer(qpackdao.getSscid()));
            if (sscdao != null) {
                name[1] = sscdao.getSscName();
            }
        }

        return name;
    }

    private int getTotalQuestionAnswer(int userid, int userresultdetailid) {

        int totalanswer = 0;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        if (records.size() > 0) {
            totalanswer = records.size();
        }
        return totalanswer;
    }
}
