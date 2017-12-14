/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.result;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.resultdisplay.DisplayTheoryResult;
import com.abp.admin.ssc.SSCDAO;
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
@RequestMapping("/admin/result")
public class ResultController {

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

        model.addAttribute("result", new ResultDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        request.getSession().setAttribute("body", "/admin/result/result.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/totalreport", method = RequestMethod.GET)
    public String totalreport(HttpServletRequest request, HttpServletResponse response, Model model) {

        String userresultdetailid = request.getParameter("userresultid");
        String userid = request.getParameter("userid");
        
        System.out.println(userid + " : " + userresultdetailid);
        
        model.addAttribute("result", new ResultDAO());
        
        ArrayList datatheorywise=displayTheoryWiseresult(new Integer(userid), new Integer(userresultdetailid));
        model.addAttribute("datatheorywise", datatheorywise);
        
        
        request.getSession().setAttribute("body", "/admin/result/detailReport.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String qpackdetail = getQualificationPackByID(sscid);

        return qpackdetail;
    }

    @RequestMapping(value = "/getBatchDetails", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getBatchDetails(@RequestParam("qp_id") String qpid) {

        System.out.println("   qpid   " + qpid);
        String batchids = getBatchDetailsByQP(qpid);

        return batchids;
    }

    @RequestMapping(value = "/getResultDetails", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getResultDetails(@RequestParam("batchid") String batchid, @RequestParam("qpackid") String qpackid) {

        System.out.println("   batchid   " + batchid);

        String resultsummary = getResultByBatchID(new Integer(batchid), new Integer(qpackid));

        return resultsummary;
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

        return jsonarr.toString();
    }

    public String getBatchDetailsByQP(String qpackid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackId", new Integer(qpackid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                BatchesDAO data = (BatchesDAO) itr.next();
                if (data.getQpackId() == new Integer(qpackid)) {
                    jsonObj.append("ID", data.getBatch_id());
                    jsonObj.append("NAME", data.getBatch_id());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
    }

    public String getResultByBatchID(int batchid, int qpackid) {

        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("batchid", batchid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            UserResultDetailDAO userresultdao = (UserResultDetailDAO) itr.next();
            UserDAO userdao = (UserDAO) this.superService.getObjectById(new UserDAO(), userresultdao.getUserid());
            jsonObj.append("ID", userresultdao.getID() + "," + userdao.getID());
            jsonObj.append("enrollmentno", userdao.getEnrollmentno());
            jsonObj.append("name", userdao.getTraineename());
            QualificationPackDAO qpackdao = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
            jsonObj.append("maxtheory", qpackdao.getTotaltheorymarks());
            jsonObj.append("theorycuttoff", qpackdao.getTheorycutoffmarks());
            jsonObj.append("scoredtheorymarks", getTotalTheoryMarks(userdao.getID(), userresultdao.getID()));
            jsonObj.append("maxpractical", qpackdao.getTotalpracticalmarks());
            jsonObj.append("practicalcuttoff", qpackdao.getPracticalcutoffmarks());
            jsonObj.append("scoredpracticalmarks", "-");
            jsonObj.append("overallcutoff", qpackdao.getOverallcutoffmarks());
            jsonObj.append("scoredweightedavg", "-");
            jsonarr.put(jsonObj);
        }

        return jsonarr.toString();

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

    private ArrayList displayTheoryWiseresult(int userid, int userresultdetailid) {

        ArrayList theorydao = new ArrayList();
        int totalmarks=0;
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
        }
        return theorydao;
    }

}
