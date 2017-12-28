/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.assessor.practicalexam;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.generateqp.QuestionPaperDAO;
import com.abp.admin.practicalmmq.PracticalMMQDAO;
import com.abp.admin.practicalmmq.SenarioQuestionDAO;
import com.abp.admin.result.PracticalWiseresultDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@RequestMapping("/assessor/practicalexam")
public class PracticalExamController {

    private static final Logger logger = Logger.getLogger(PracticalExamController.class);

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        ArrayList datalist = new ArrayList();

        try {
            Integer assesorid = (Integer) request.getSession().getAttribute("assessorId");
            Map param2 = new HashMap();
            param2.put("assessorId", assesorid);
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

                        datalist.add(batchdao.getID());

                    }

                }

            }
            model.addAttribute("practical", new PracticalExamDAO());
            model.addAttribute("batchlist", datalist);
            model.addAttribute("action", "search.io");

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/assessor/practicalexam/practicalexam.jsp");
        return "assessor/common";
    }

    @RequestMapping(value = "/getBatchUser", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getBatchUser(@RequestParam("batchid") String batchid) {

        System.out.println("Batch ID::" + batchid);
        String users = loadBatchUsers(batchid);

        return users;
    }

    @RequestMapping(value = "/captureUserImage", method = RequestMethod.GET)
    public String captureUserImage(HttpServletRequest request, HttpServletResponse response, Model model) {

        String userid = request.getParameter("userid");
        String batchid = request.getParameter("batchid");

        model.addAttribute("userid", userid);
        model.addAttribute("batchid", batchid);

        request.getSession().setAttribute("body", "/assessor/practicalexam/captureimage.jsp");
        return "assessor/commonmodal";
    }

    @RequestMapping(value = "/continueExam", method = RequestMethod.GET)
    public String continueExam(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            Map<String, ArrayList> questions = new HashMap();
            String userid = request.getParameter("userid");
            String batchid = request.getParameter("batchid");
            UserDAO userdao = (UserDAO) this.superService.getObjectById(new UserDAO(), new Integer(userid));
            model.addAttribute("studentname", userdao.getTraineename());
            model.addAttribute("enrollno", userdao.getEnrollmentno());

            boolean flag = checkUserAlreadyExamed(batchid, userid);

            System.out.println("Flag ::::::::::::::" + flag);
            if (flag) {
                BatchesDAO batchdao = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
                if (batchdao != null) {
                    int questionid = batchdao.getQuestionPaperId();
                    QuestionPaperDAO questiondao = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), questionid);
                    String[] practicalqids = questiondao.getPracticalmmqids().split(",");
                    Set<String> questionids = new HashSet<String>(Arrays.asList(practicalqids));

                    if (questionids.size() > 0) {
                        Iterator itr = questionids.iterator();
                        while (itr.hasNext()) {
                            String qid = (String) itr.next();
                            SenarioQuestionDAO qdao = (SenarioQuestionDAO) this.superService.getObjectById(new SenarioQuestionDAO(), new Integer(qid));
                            if (questions.get("" + qdao.getSenario_id()) != null) {
                                ArrayList questionsall = (ArrayList) questions.get("" + qdao.getSenario_id());
                                DisplayQuestion dispquest = new DisplayQuestion();
                                dispquest.setQuestionid(new Integer(qid));
                                dispquest.setQuestion(qdao.getQuestion());
                                String option = getAnswerByQuestionNo(qid, userid, qdao.getSenario_id());
                                dispquest.setOptions(option);
                                questionsall.add(dispquest);
                                questions.put("" + qdao.getSenario_id(), questionsall);
                            } else {
                                ArrayList questionsall = new ArrayList();
                                DisplayQuestion dispquest = new DisplayQuestion();
                                dispquest.setQuestionid(new Integer(qid));
                                dispquest.setQuestion(qdao.getQuestion());
                                String option = getAnswerByQuestionNo(qid, userid, qdao.getSenario_id());
                                dispquest.setOptions(option);
                                questionsall.add(dispquest);
                                questions.put("" + qdao.getSenario_id(), questionsall);
                            }
                        }
                    }
                }
            } else {

                BatchesDAO batchdao = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
                if (batchdao != null) {
                    int questionid = batchdao.getQuestionPaperId();
                    QuestionPaperDAO questiondao = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), questionid);
                    String[] practicalqids = questiondao.getPracticalmmqids().split(",");
                    Set<String> questionids = new HashSet<String>(Arrays.asList(practicalqids));

                    if (questionids.size() > 0) {
                        Iterator itr = questionids.iterator();
                        while (itr.hasNext()) {
                            String qid = (String) itr.next();
                            SenarioQuestionDAO qdao = (SenarioQuestionDAO) this.superService.getObjectById(new SenarioQuestionDAO(), new Integer(qid));
                            if (questions.get("" + qdao.getSenario_id()) != null) {
                                ArrayList questionsall = (ArrayList) questions.get("" + qdao.getSenario_id());
                                DisplayQuestion dispquest = new DisplayQuestion();
                                dispquest.setQuestionid(new Integer(qid));
                                dispquest.setQuestion(qdao.getQuestion());

                                dispquest.setOptions("");
                                questionsall.add(dispquest);
                                questions.put("" + qdao.getSenario_id(), questionsall);
                            } else {
                                ArrayList questionsall = new ArrayList();
                                DisplayQuestion dispquest = new DisplayQuestion();
                                dispquest.setQuestionid(new Integer(qid));
                                dispquest.setQuestion(qdao.getQuestion());

                                dispquest.setOptions("");
                                questionsall.add(dispquest);
                                questions.put("" + qdao.getSenario_id(), questionsall);
                            }
                        }
                    }
                }
            }

            for (String key : questions.keySet()) {
                System.out.println(key);
                ArrayList values = (ArrayList) questions.get(key);
                String quest = getSenarioQuestionByID(key);
                questions.remove(key);
                questions.put(quest, values);
            }
            model.addAttribute("userid", userid);
            model.addAttribute("batchid", batchid);
            model.addAttribute("questionlist", questions);
            model.addAttribute("mode", "add");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/assessor/practicalexam/exam.jsp");
        return "assessor/commonmodal";
    }

    @RequestMapping(value = "/saveanswer", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String saveanswer(@RequestParam("userid") String userid, @RequestParam("batchid") String batchid, @RequestParam("quesno") String quesno, @RequestParam("answer") String answer) {

        JSONObject jsonObj = new JSONObject();

        try {
            Map param = new HashMap();
            param.put("batchid", new Integer(batchid));
            param.put("userid", new Integer(userid));
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
            if (records.size() > 0) {
                UserResultDetailDAO urdo = (UserResultDetailDAO) records.get(0);
                int userresultdetailid = urdo.getID();
                int senarioid = getSenarioId(quesno);

                Map param2 = new HashMap();
                param2.put("userresultdetailid", userresultdetailid);
                param2.put("userid", new Integer(userid));
                param2.put("questionid", new Integer(quesno));
                param2.put("senarioid", senarioid);
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param2);
                if (records2.size() > 0) {
                    System.out.println("System going to update :  : :");
                    PracticalWiseresultDAO pactwisedao = (PracticalWiseresultDAO) records2.get(0);
                    pactwisedao.setAnswerstatus(answer);
                    this.superService.updateObject(pactwisedao);
                    jsonObj.append("status", "update");

                } else {
                    System.out.println("System going to save :  : :");
                    PracticalWiseresultDAO pactwisedao = new PracticalWiseresultDAO();
                    pactwisedao.setSenarioid(senarioid);
                    pactwisedao.setUserid(new Integer(userid));
                    pactwisedao.setUserresultdetailid(userresultdetailid);
                    pactwisedao.setQuestionid(new Integer(quesno));
                    pactwisedao.setAnswerstatus(answer);

                    try {
                        this.superService.saveObject(pactwisedao);
                        jsonObj.append("status", "save");
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonObj.append("status", "fail");
                    }

                }

            }
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        System.out.println(" Json  :  :" + jsonObj.toString());
        return jsonObj.toString();
    }

    public String loadBatchUsers(String batchid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        try {
            Map param = new HashMap();
            param.put("batchid", new Integer(batchid));
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    UserDAO data = (UserDAO) itr.next();
                    if (data.getBatchid() == new Integer(batchid)) {
                        jsonObj.append("photo", data.getPhotoname());
                        jsonObj.append("enrollno", data.getEnrollmentno());
                        jsonObj.append("studentname", data.getTraineename());
                        jsonObj.append("userid", data.getID());
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

    private String getSenarioQuestionByID(String id) {

        String questions = "";
        PracticalMMQDAO pmmqdao = (PracticalMMQDAO) this.superService.getObjectById(new PracticalMMQDAO(), new Integer(id));
        questions = pmmqdao.getSenario();
        return questions;

    }

    private int getSenarioId(String qno) {

        int senarioid = 0;
        SenarioQuestionDAO senariodao = (SenarioQuestionDAO) this.superService.getObjectById(new SenarioQuestionDAO(), new Integer(qno));
        if (senariodao != null) {
            senarioid = senariodao.getSenario_id();
        }

        return senarioid;
    }

    private boolean checkUserAlreadyExamed(String batchid, String userid) {

        boolean flag = false;

        try {
            Map param = new HashMap();
            param.put("batchid", new Integer(batchid));
            param.put("userid", new Integer(userid));
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
            if (records.size() > 0) {
                UserResultDetailDAO urdo = (UserResultDetailDAO) records.get(0);
                int userresultdetailid = urdo.getID();

                Map param2 = new HashMap();
                param2.put("userresultdetailid", userresultdetailid);
                param2.put("userid", new Integer(userid));
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param2);
                if (records2.size() > 0) {
                    flag = true;
                }

            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        return flag;
    }

    private String getAnswerByQuestionNo(String quesno, String userid, int senarioid) {

        System.out.println(userid + " answer :::" + quesno + "    " + senarioid);
        String answer = "";

        try {
            Map param2 = new HashMap();

            param2.put("userid", new Integer(userid));
            param2.put("questionid", new Integer(quesno));
            param2.put("senarioid", senarioid);
            List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param2);
            if (records2.size() > 0) {

                PracticalWiseresultDAO pactwisedao = (PracticalWiseresultDAO) records2.get(0);
                answer = pactwisedao.getAnswerstatus();
                System.out.println("answer :::" + answer);
            }
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return answer;
    }
}
