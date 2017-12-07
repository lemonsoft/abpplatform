/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.questionpaper;

import com.abp.admin.generateqp.QuestionPaperDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
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
@RequestMapping("/admin/questionpaper")
public class QuestionPaperController {

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

        model.addAttribute("questionpaperdao", new QuestionPaperDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/questionpaper/questionpaper.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/editQuestionPaper", method = RequestMethod.GET)
    public String editQuestionPaper(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpaperid = request.getParameter("qpaperid");
        QuestionPaperDAO beanObj = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), new Integer(qpaperid));

        model.addAttribute("israndom", beanObj.getIsrandom());
        model.addAttribute("isoptionrandom", beanObj.getIsoptionrandom());
        model.addAttribute("isactive", beanObj.getIsactive());

        model.addAttribute("qpaper", beanObj);
        model.addAttribute("mode", "update");

        request.getSession().setAttribute("body", "/admin/generateqp/questionpaper.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/updateQuestionPaper", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String updateQuestionPaper(@RequestParam("questionpaperid") String questionpaperid, @RequestParam("qpackid") String qpackid, @RequestParam("questionpapername") String questionpapername, @RequestParam("totaltime") String totaltime, @RequestParam("totalmarks") String totalmarks, @RequestParam("israndom") String israndom, @RequestParam("isoptionrandom") String isoptionrandom, @RequestParam("isactive") String isactive) {

        JSONObject jsonObj = new JSONObject();
        QuestionPaperDAO beanObj = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), new Integer(questionpaperid));
        beanObj.setQuestionpapername(questionpapername);
        beanObj.setTotaltime(new Integer(totaltime));
        beanObj.setTotalmarks(totalmarks);
        beanObj.setQpackid(new Integer(qpackid));
        beanObj.setIsrandom(israndom);
        beanObj.setIsoptionrandom(isoptionrandom);
        beanObj.setIsactive(isactive);
        try {
            this.superService.updateObject(beanObj);
            jsonObj.append("status", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObj.append("status", "fail");
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
    }

    @RequestMapping(value = "/getQuestionPaper", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestionPaper(@RequestParam("qpackid") String qpackid) {

        String jsonarr = getAllQuestionPapers(new Integer(qpackid));

        return jsonarr.toString();
    }

    @RequestMapping(value = "/viewquestions", method = RequestMethod.GET)
    public String openquestionpaper(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpackid = request.getParameter("qpackid");

        QualificationPackDAO qpObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));
        int totalmarksadded = 0;
        ArrayList dataview = new ArrayList();
        Map param = new HashMap();
        param.put("qpackid", new Integer(qpackid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionPaperDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionPaperDAO data = (QuestionPaperDAO) itr.next();
                String questionids[] = data.getQuestionids().split(",");
                for (int i = 0; i < questionids.length; i++) {

                    QuestionDAO beanObj = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), new Integer(questionids[i]));
                    if (beanObj != null) {
                        DisplayQuestion viewquestion = new DisplayQuestion();
                        viewquestion.setQuestionID("" + beanObj.getId());
                        viewquestion.setQuestionTitle(beanObj.getQuestion_title());
                        viewquestion.setOption1(beanObj.getOption1());
                        viewquestion.setOption2(beanObj.getOption2());
                        viewquestion.setOption3(beanObj.getOption3());
                        viewquestion.setOption4(beanObj.getOption4());
                        viewquestion.setOption5(beanObj.getOption5());
                        viewquestion.setMarks("" + beanObj.getMarks());
                        totalmarksadded = totalmarksadded + beanObj.getMarks();
                        viewquestion.setPcID(getPCID(beanObj.getPcid()));

                        dataview.add(viewquestion);
                    }
                }

            }
        }
        model.addAttribute("totalmarksadded", totalmarksadded);
        model.addAttribute("theorymarks", qpObj.getTotaltheorymarks());
        model.addAttribute("dataview", dataview);
        model.addAttribute("questionpaperdao", new QuestionPaperDAO());
        request.getSession().setAttribute("body", "/admin/questionpaper/viewQuestions.jsp");
        return "admin/common";
    }

    public String getPCID(int pcid) {

        String pcidname = "";
        try {
            PCDAO beanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcid);
            if (beanObj != null) {
                pcidname = beanObj.getPcid();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pcidname;
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

    public String getAllQuestionPapers(int qpackid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));

        Map param = new HashMap();
        param.put("qpackid", qpackid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionPaperDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionPaperDAO data = (QuestionPaperDAO) itr.next();

                jsonObj.append("questionpaperid", data.getQuestionpaperid());
                jsonObj.append("questionpapername", data.getQuestionpapername());
                jsonObj.append("totalmarks", data.getTotalmarks());
                jsonObj.append("qpackname", beanObj.getQpackname());
                jsonObj.append("israndom", data.getIsrandom());
                jsonObj.append("isoptionrandom", data.getIsoptionrandom());
                jsonObj.append("isactive", data.getIsactive());
                jsonObj.append("totaltime", data.getTotaltime());
                jsonObj.append("createddatetime", data.getCreateddatetime());
                jsonarr.put(jsonObj);

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
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
}
