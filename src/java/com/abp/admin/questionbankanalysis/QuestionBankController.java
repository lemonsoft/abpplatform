/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.questionbankanalysis;

import com.abp.admin.generateqp.QuestionPaperDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.result.TheoryWiseResultDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@RequestMapping("/admin/qbankanalysis")
public class QuestionBankController {

    private static final Logger logger = Logger.getLogger(QuestionBankController.class);
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

        model.addAttribute("qbankdao", new QuestionBankAnalysisDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/qbankanalysis/qbankanalysis.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, QuestionBankAnalysisDAO beanObj, Model model) throws Exception {

        System.out.println(" Sector " + beanObj.getSscid());
        System.out.println(" Job role " + beanObj.getQpid());
        System.out.println(" Month " + beanObj.getMonth());
        Set questionBank = new HashSet();
        String seldate = beanObj.getMonth();
        Map qpacks = new HashMap();
        qpacks.put("qpackid", new Integer(beanObj.getQpid()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionPaperDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionPaperDAO data = (QuestionPaperDAO) itr.next();
                String questionids[] = data.getQuestionids().split(",");

                for (int i = 0; i < questionids.length; i++) {
                    questionBank.add(questionids[i]);
                }

            }
        }

        System.out.println("questionBank : " + questionBank);

        ArrayList record = new ArrayList();
        if (questionBank.size() > 0) {
            Iterator itr = questionBank.iterator();
            while (itr.hasNext()) {
                String questids = (String) itr.next();
                Map questdetails = new HashMap();
                questdetails.put("questionid", new Integer(questids));
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), questdetails);
                if (records2.size() > 0) {
                    Iterator itr2 = records2.iterator();
                    String questionid = "";
                    int correctat = 0;
                    int incorrect = 0;
                    int noattempt = 0;
                    boolean flag = false;
                    while (itr2.hasNext()) {
                        TheoryWiseResultDAO theorywisedao = (TheoryWiseResultDAO) itr2.next();
                        String compdate = theorywisedao.getRecorddate();
                        compdate = compdate.substring(0, 7);
                        System.out.println("Compare For date " + compdate);

                        if (seldate.equals(compdate)) {
                            flag = true;
                            questionid = "" + theorywisedao.getQuestionid();
                            if (isCorrectAttempt(questionid, theorywisedao.getCorrectanswer())) {
                                correctat++;
                            } else {
                                incorrect++;
                            }
                        }

                    }
                    if (flag) {
                        DisplayQuestionBank dispbank = new DisplayQuestionBank();
                        dispbank.setQuestionid(questionid);
                        dispbank.setQuestion(getQuestionbyId(questids));
                        dispbank.setNoofattempt("" + records2.size());
                        dispbank.setCorrectatmpt("" + correctat);
                        dispbank.setIncorrectattempt("" + incorrect);
                        dispbank.setNotattempt("" + noattempt);
                        record.add(dispbank);
                    }

                }

            }

        }

        model.addAttribute("records", record);
        model.addAttribute("qbankdao", beanObj);
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/qbankanalysis/qbankanalysis.jsp");
        return "admin/common";

    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
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

    public Map<Integer, String> getQualificationPacks(String ssc_id) {

        Map qpacks = new HashMap();
        qpacks.put("sscid", new Integer(ssc_id));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QualificationPackDAO data = (QualificationPackDAO) itr.next();
                qpacks.put(data.getQpid(), data.getQpackname());
            }
        }

        return qpacks;
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

    public String getQuestionbyId(String quesid) {

        String question = "";
        QuestionDAO beanObj = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), new Integer(quesid));
        if (beanObj != null) {
            System.out.println("Question : " + beanObj.getQuestion_title());
            question = beanObj.getQuestion_title();
        }
        return question;
    }

    public boolean isCorrectAttempt(String quesid, String answer) {

        boolean flag = false;
        QuestionDAO beanObj = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), new Integer(quesid));
        if (beanObj != null) {
            System.out.println("Correct option : " + beanObj.getCorrect_option());
            if (Integer.parseInt(beanObj.getCorrect_option()) == Integer.parseInt(answer)) {
                flag = true;
            }
        }
        return flag;
    }

}
