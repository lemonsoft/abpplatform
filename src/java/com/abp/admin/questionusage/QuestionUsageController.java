/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.questionusage;

import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.NOSDAO;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/admin/questionusage")
public class QuestionUsageController {

    private static final Logger logger = Logger.getLogger(QuestionUsageController.class);

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

        model.addAttribute("questions", new QuestionDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/questionusage/questionusage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
    }

    @RequestMapping(value = "/getnos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getnos(@RequestParam("qpid") String qpid) {

        System.out.println("QP ID::" + qpid);
        String nos = getNOSByID(qpid);

        return nos;
    }

    @RequestMapping(value = "/getpc", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getpc(@RequestParam("nosid") String nosid) {

        System.out.println("NOS ID::" + nosid);
        String pcdata = getPCByID(nosid);

        return pcdata;
    }

    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestions(@RequestParam("qp_id") String qpid) {

        System.out.println("qpid ID::" + qpid);
        String questions = getAllQuestions(new Integer(qpid));

        return questions;
    }

    @RequestMapping(value = "/getQuestionsbynos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestionsbynos(@RequestParam("qp_id") String qpid, @RequestParam("nosid") String nosid) {

        String questions = getAllQuestionsByNos(new Integer(qpid), new Integer(nosid));

        return questions;
    }

    @RequestMapping(value = "/getQuestionsbypc", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestionsbypc(@RequestParam("qp_id") String qpid, @RequestParam("nosid") String nosid, @RequestParam("pcid") String pcid) {

        String questions = getAllQuestionsByPC(new Integer(qpid), new Integer(nosid), new Integer(pcid));
        System.out.println("Get question by pc");
        return questions;
    }

    @RequestMapping(value = "/initChange", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String initChange(@RequestParam("questionid") String questionid, @RequestParam("isactive") String isactive, Model model) {

        JSONObject jsonObj = new JSONObject();
        System.out.println("questionid   " + questionid);
        QuestionDAO questiondao = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), new Integer(questionid));

        if (isactive.equalsIgnoreCase("Y")) {
            questiondao.setIsphaseout("N");
        } else {
            questiondao.setIsphaseout("Y");
        }
        try {
            this.superService.updateObject(questiondao);
            jsonObj.append("status", "save");
        } catch (Exception e) {
            logger.error("This is Error message", e);
            jsonObj.append("status", "fail");
        }

        return jsonObj.toString();
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
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

    public String getNOSByID(String qpid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        try {

            Map param = new HashMap();
            param.put("qpackid", qpid);
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new NOSDAO(), param);
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    NOSDAO data = (NOSDAO) itr.next();
                    if (data.getQpackid().equals(qpid)) {
                        jsonObj.append("ID", data.getNosID());
                        jsonObj.append("NAME", data.getNosname());
                        jsonarr.put(jsonObj);
                    }

                    jsonObj = new JSONObject();
                }
            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

    public String getPCByID(String nosid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        try {
            Map param = new HashMap();
            param.put("nosid", nosid);
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    PCDAO data = (PCDAO) itr.next();
                    if (data.getNosid().equals(nosid)) {
                        jsonObj.append("ID", data.getPcID());
                        jsonObj.append("NAME", data.getPcname());
                        jsonarr.put(jsonObj);
                    }

                    jsonObj = new JSONObject();
                }
            }
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

    public String getAllQuestions(int qpid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        try {

            Map param = new HashMap();
            param.put("qpackid", qpid);
            param.put("isapproved", "Y");
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
            System.out.println("Get Record Size :" + records.size());
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    QuestionDAO data = (QuestionDAO) itr.next();
                    if (data.getQpackid() == qpid) {
                        System.out.println(data.getId());
                        jsonObj.append("ID", data.getId());
                        jsonObj.append("question_title", data.getQuestion_title());
                        jsonObj.append("questionimgurl", data.getQuestionimgurl());
                        System.out.println("qimg::::" + data.getQuestionimgurl());
                        jsonObj.append("option1", data.getOption1());
                        jsonObj.append("imageurl1", data.getImageurl1());
                        jsonObj.append("option2", data.getOption2());
                        jsonObj.append("imageurl2", data.getImageurl2());
                        jsonObj.append("option3", data.getOption3());
                        jsonObj.append("imageurl3", data.getImageurl3());
                        jsonObj.append("option4", data.getOption4());
                        jsonObj.append("imageurl4", data.getImageurl4());
                        jsonObj.append("option5", data.getOption5());
                        jsonObj.append("imageurl5", data.getImageurl5());
                        jsonObj.append("appeared", "0%");
                        jsonObj.append("answered", "0%");
                        jsonObj.append("isphaseout", data.getIsphaseout());
                        jsonarr.put(jsonObj);
                    }

                    jsonObj = new JSONObject();
                }
            }
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

    public String getAllQuestionsByNos(int qpid, int nosid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        try {
            Map param = new HashMap();
            param.put("qpackid", "" + qpid);
            param.put("nosid", "" + nosid);
            param.put("isapproved", "Y");
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
            System.out.println("Get Record Size :" + records.size());
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    QuestionDAO data = (QuestionDAO) itr.next();
                    if (data.getQpackid() == qpid && data.getNosid() == nosid) {
                        System.out.println(data.getId());
                        jsonObj.append("ID", data.getId());
                        jsonObj.append("question_title", data.getQuestion_title());
                        jsonObj.append("questionimgurl", data.getQuestionimgurl());
                        jsonObj.append("option1", data.getOption1());
                        jsonObj.append("imageurl1", data.getImageurl1());
                        jsonObj.append("option2", data.getOption2());
                        jsonObj.append("imageurl2", data.getImageurl2());
                        jsonObj.append("option3", data.getOption3());
                        jsonObj.append("imageurl3", data.getImageurl3());
                        jsonObj.append("option4", data.getOption4());
                        jsonObj.append("imageurl4", data.getImageurl4());
                        jsonObj.append("option5", data.getOption5());
                        jsonObj.append("imageurl5", data.getImageurl5());
                        jsonObj.append("appeared", "0%");
                        jsonObj.append("answered", "0%");
                        jsonObj.append("isphaseout", data.getIsphaseout());
                        jsonarr.put(jsonObj);
                    }

                    jsonObj = new JSONObject();
                }
            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

    public String getAllQuestionsByPC(int qpid, int nosid, int pcid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        try {
            Map param = new HashMap();
            param.put("qpackid", qpid);
            param.put("nosid", nosid);
            param.put("pcid", pcid);
            param.put("isapproved", "Y");
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
            System.out.println("Get Record Size :" + records.size());
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    QuestionDAO data = (QuestionDAO) itr.next();
                    if (data.getQpackid() == qpid && data.getNosid() == nosid && data.getPcid() == pcid) {
                        System.out.println(data.getId());
                        jsonObj.append("ID", data.getId());
                        jsonObj.append("question_title", data.getQuestion_title());
                        jsonObj.append("questionimgurl", data.getQuestionimgurl());
                        jsonObj.append("option1", data.getOption1());
                        jsonObj.append("imageurl1", data.getImageurl1());
                        jsonObj.append("option2", data.getOption2());
                        jsonObj.append("imageurl2", data.getImageurl2());
                        jsonObj.append("option3", data.getOption3());
                        jsonObj.append("imageurl3", data.getImageurl3());
                        jsonObj.append("option4", data.getOption4());
                        jsonObj.append("imageurl4", data.getImageurl4());
                        jsonObj.append("option5", data.getOption5());
                        jsonObj.append("imageurl5", data.getImageurl5());
                        jsonObj.append("appeared", "0%");
                        jsonObj.append("answered", "0%");
                        jsonObj.append("isphaseout", data.getIsphaseout());
                        jsonarr.put(jsonObj);
                    }

                    jsonObj = new JSONObject();
                }
            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }
}
