/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.result;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.practicalmmq.PCWithMarksDAO;
import com.abp.admin.practicalmmq.PracticalMMQDAO;
import com.abp.admin.practicalmmq.SenarioQuestionDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.NOSDAO;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.resultdisplay.AssesmentLogDisplay;
import com.abp.admin.resultdisplay.DisplayTheoryResult;
import com.abp.admin.resultdisplay.NosWiseLogDisplay;
import com.abp.admin.resultdisplay.PCWiseLogDisplay;
import com.abp.admin.resultdisplay.QuestionWiseLog;
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
        UserResultDetailDAO userdetaildao = (UserResultDetailDAO) this.superService.getObjectById(new UserResultDetailDAO(), new Integer(userresultdetailid));

        model.addAttribute("result", new ResultDAO());

        Map datatheorywise = displayTheoryWiseresult(new Integer(userid), new Integer(userresultdetailid));

        model.addAttribute("startdatetime", userdetaildao.getExamstarttime());
        model.addAttribute("enddatetime", userdetaildao.getExamendtime());
        model.addAttribute("totaltime", userdetaildao.getTotaltime());
        model.addAttribute("timetaken", userdetaildao.getTimetaken());
        model.addAttribute("ipaddress", userdetaildao.getIpaddress());
        model.addAttribute("browser", userdetaildao.getBrowserversion());

        model.addAttribute("datatheorywise", datatheorywise.get("datalist"));
        model.addAttribute("totalmarks", datatheorywise.get("totalmarks"));
        model.addAttribute("totaltheorymarks", getTotalTheoryMarks(new Integer(userid), new Integer(userresultdetailid)));

        ArrayList asseslogdao = getAssesmentLog(new Integer(userid), new Integer(userresultdetailid));
        model.addAttribute("asseslogdao", asseslogdao);

        Map<String, ArrayList> practicallog = getPracticalwiseLog(new Integer(userid), new Integer(userresultdetailid));
        model.addAttribute("practicallog", practicallog);

        ArrayList questlogdao = getQuestionwiseLog(new Integer(userid), new Integer(userresultdetailid));
        model.addAttribute("questlogdao", questlogdao);

        request.getSession().setAttribute("body", "/admin/result/detailReport.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/nosreport", method = RequestMethod.GET)
    public String nosreport(HttpServletRequest request, HttpServletResponse response, Model model) {

        String userresultdetailid = request.getParameter("userresultid");
        String userid = request.getParameter("userid");
        ArrayList record = new ArrayList();
        Map<Integer, NosWiseLogDisplay> noswiselog = getNoswiseLog(new Integer(userid), new Integer(userresultdetailid));
        for (Map.Entry<Integer, NosWiseLogDisplay> entry : noswiselog.entrySet()) {
            record.add(entry.getValue());

        }
        Iterator itr = record.iterator();
        while (itr.hasNext()) {
            NosWiseLogDisplay noswiselogdao = (NosWiseLogDisplay) itr.next();
            int totalscored = noswiselogdao.getScoredtheorymarks() + Integer.parseInt(noswiselogdao.getPracticalscoredmarks());
            noswiselogdao.setScoredtotalmarks("" + totalscored);
            int overallcutoff = Integer.parseInt(noswiselogdao.getOverallcutoff());
            if (totalscored > overallcutoff) {
                noswiselogdao.setResult("Pass");
            } else {
                noswiselogdao.setResult("Fail");
            }

        }
        model.addAttribute("noswiselog", record);

        request.getSession().setAttribute("body", "/admin/result/nosReport.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/pcreport", method = RequestMethod.GET)
    public String pcreport(HttpServletRequest request, HttpServletResponse response, Model model) {

        int totalpractical = 0;
        int totaltheory = 0;
        String userresultdetailid = request.getParameter("userresultid");
        String userid = request.getParameter("userid");
        ArrayList record = new ArrayList();
        Map<Integer, PCWiseLogDisplay> pcwiselog = getPcwiseLog(new Integer(userid), new Integer(userresultdetailid));
        for (Map.Entry<Integer, PCWiseLogDisplay> entry : pcwiselog.entrySet()) {
            record.add(entry.getValue());

        }
        Iterator itr = record.iterator();
        while (itr.hasNext()) {
            PCWiseLogDisplay pcwiselogdao = (PCWiseLogDisplay) itr.next();
            totaltheory = totaltheory + pcwiselogdao.getScoredtheorymarks();
            totalpractical = totalpractical + pcwiselogdao.getScoredpracticalmarks();
            int totalscored = pcwiselogdao.getScoredtheorymarks() + pcwiselogdao.getScoredpracticalmarks();
            pcwiselogdao.setScoredtotalmarks("" + totalscored);

        }
        model.addAttribute("totaltheory", totaltheory);
        model.addAttribute("totalpractical", totalpractical);
        model.addAttribute("pcwiselog", record);

        request.getSession().setAttribute("body", "/admin/result/pcreport.jsp");
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
            int totaltheorymarks = getTotalTheoryMarks(userdao.getID(), userresultdao.getID());
            jsonObj.append("scoredtheorymarks", getTotalTheoryMarks(userdao.getID(), userresultdao.getID()));
            jsonObj.append("maxpractical", qpackdao.getTotalpracticalmarks());
            jsonObj.append("practicalcuttoff", qpackdao.getPracticalcutoffmarks());

            int scorepracticalmarks = calculatePracticalmarksQPackID(qpackdao.getQpid(), userdao.getID(), userresultdao.getID());
            jsonObj.append("scoredpracticalmarks", "" + scorepracticalmarks);
            jsonObj.append("overallcutoff", qpackdao.getOverallcutoffmarks());
            int totalscored = totaltheorymarks + scorepracticalmarks;
            jsonObj.append("scoredtotal", totalscored);
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

    private ArrayList getAssesmentLog(int userid, int userresultdetailid) {

        int i = 1;
        ArrayList assesrecord = new ArrayList();
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssesmentLogDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {
            AssesmentLogDisplay assesslog = new AssesmentLogDisplay();
            AssesmentLogDAO asseslog = (AssesmentLogDAO) itr.next();
            assesslog.setSno("" + i);
            assesslog.setDatetime(asseslog.getLogactiondate());
            assesslog.setAction(asseslog.getActiontaken());
            assesrecord.add(assesslog);
            i++;
        }
        return assesrecord;
    }

    private ArrayList getQuestionwiseLog(int userid, int userresultdetailid) {

        int i = 1;
        ArrayList quesrecord = new ArrayList();
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionWiseLogDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {
            QuestionWiseLog questionlog = new QuestionWiseLog();
            QuestionWiseLogDAO questionslog = (QuestionWiseLogDAO) itr.next();
            questionlog.setSno("" + i);
            questionlog.setQuestionno("" + questionslog.getQuestionno());
            questionlog.setStartdate(questionslog.getStartdate());
            questionlog.setEnddate(questionslog.getEnddate());
            questionlog.setTimetaken(questionslog.getTimetaken());
            quesrecord.add(questionlog);
            i++;
        }
        return quesrecord;
    }

    private Map<String, ArrayList> getPracticalwiseLog(int userid, int userresultdetailid) {

        Map<String, ArrayList> questions = new HashMap();
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PracticalWiseresultDAO practicallog = (PracticalWiseresultDAO) itr.next();
                System.out.println(" practlog ::::" + practicallog.getQuestionid());

                DisplayPracticalResult dprdao = new DisplayPracticalResult();
                SenarioQuestionDAO qdao = (SenarioQuestionDAO) this.superService.getObjectById(new SenarioQuestionDAO(), practicallog.getQuestionid());
                dprdao.setSnquestion(qdao.getQuestion());
                dprdao.setAnswerstatus(practicallog.getAnswerstatus());
                dprdao.setActualmarks("" + getTotalMarksofQuestion(practicallog.getQuestionid()));
                if (practicallog.getAnswerstatus().equalsIgnoreCase("yes")) {
                    dprdao.setScoredmarks(getTotalMarksofQuestion(practicallog.getQuestionid()));
                } else {
                    dprdao.setScoredmarks(0);
                }

                if (questions.get("" + qdao.getSenario_id()) != null) {

                    ArrayList questionsall = (ArrayList) questions.get("" + qdao.getSenario_id());
                    questionsall.add(dprdao);
                    questions.put("" + qdao.getSenario_id(), questionsall);
                } else {
                    ArrayList questionsall = new ArrayList();
                    questionsall.add(dprdao);
                    questions.put("" + qdao.getSenario_id(), questionsall);
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

        return questions;
    }

    private int getTotalMarksofQuestion(int questionid) {

        int totalmarks = 0;
        Map param = new HashMap();
        param.put("question_id", questionid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCWithMarksDAO pcwithmarks = (PCWithMarksDAO) itr.next();
                totalmarks = totalmarks + pcwithmarks.getMarks();
            }

        }

        return totalmarks;
    }

    private String getSenarioQuestionByID(String id) {

        String questions = "";
        PracticalMMQDAO pmmqdao = (PracticalMMQDAO) this.superService.getObjectById(new PracticalMMQDAO(), new Integer(id));
        questions = pmmqdao.getSenario();
        return questions;

    }

    private Map<Integer, NosWiseLogDisplay> getNoswiseLog(int userid, int userresultdetailid) {

        //ArrayList nosrecord = new ArrayList();
        Map<Integer, NosWiseLogDisplay> nosparam = new HashMap();
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {

            //NosWiseLogDisplay noswisedao = new NosWiseLogDisplay();
            TheoryWiseResultDAO theorydao = (TheoryWiseResultDAO) itr.next();

            int questid = theorydao.getQuestionid();
            QuestionDAO questiondao = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), questid);

            int nosid = questiondao.getNosid();
            if (nosparam.containsKey(nosid)) {

                NosWiseLogDisplay noswiselogdao = (NosWiseLogDisplay) nosparam.get(nosid);
                if (theorydao.getCorrectanswer().equals(questiondao.getCorrect_option())) {
                    int marks = noswiselogdao.getScoredtheorymarks();
                    marks = marks + questiondao.getMarks();
                    noswiselogdao.setScoredtheorymarks(marks);

                }
                nosparam.put(nosid, noswiselogdao);

            } else {

                NOSDAO nosdao = (NOSDAO) this.superService.getObjectById(new NOSDAO(), nosid);
                NosWiseLogDisplay noswisedao = new NosWiseLogDisplay();
                noswisedao.setNosid(nosdao.getNosid());
                noswisedao.setTheorymarks(nosdao.getTheorycutoffmarks());
                noswisedao.setTheorycutoff(nosdao.getTheorycutoffmarks());
                if (theorydao.getCorrectanswer().equals(questiondao.getCorrect_option())) {
                    noswisedao.setScoredtheorymarks(questiondao.getMarks());
                } else {
                    noswisedao.setScoredtheorymarks(0);
                }

                noswisedao.setPracticalmarks(nosdao.getPracticalcutoffmarks());
                noswisedao.setPracticalcutoff(nosdao.getPracticalcutoffmarks());
                int practicalscored = calculatePracticalmarksNOSID(nosdao.getNosID(), userid, userresultdetailid);
                noswisedao.setPracticalscoredmarks("" + practicalscored);
                int totalmarks = Integer.parseInt(nosdao.getTheorycutoffmarks()) + Integer.parseInt(nosdao.getPracticalcutoffmarks());
                noswisedao.setTotalmarks("" + totalmarks);

                noswisedao.setOverallcutoff(nosdao.getOverallcutoffmarks());
                noswisedao.setScoredtotalmarks("");
                noswisedao.setWeightedavg("0");
                noswisedao.setScoredweightavg("--");
                noswisedao.setResult("--");
                nosparam.put(nosid, noswisedao);
            }

            // nosrecord.add(noswisedao);
        }

        return nosparam;
    }

    private Map<Integer, PCWiseLogDisplay> getPcwiseLog(int userid, int userresultdetailid) {

        Map<Integer, PCWiseLogDisplay> pcparam = new HashMap();
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        Iterator itr = records.iterator();
        while (itr.hasNext()) {

            TheoryWiseResultDAO theorydao = (TheoryWiseResultDAO) itr.next();
            int questid = theorydao.getQuestionid();
            QuestionDAO questiondao = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), questid);
            int pcid = questiondao.getPcid();
            if (pcparam.containsKey(pcid)) {

                PCWiseLogDisplay pcwiselogdao = (PCWiseLogDisplay) pcparam.get(pcid);
                if (theorydao.getCorrectanswer().equals(questiondao.getCorrect_option())) {
                    int marks = pcwiselogdao.getScoredtheorymarks();
                    marks = marks + questiondao.getMarks();
                    pcwiselogdao.setScoredtheorymarks(marks);
                }
                pcparam.put(pcid, pcwiselogdao);

            } else {

                PCDAO pcdao = (PCDAO) this.superService.getObjectById(new PCDAO(), pcid);
                PCWiseLogDisplay pcwisedao = new PCWiseLogDisplay();
                pcwisedao.setPcid(pcdao.getPcid());
                pcwisedao.setTheorymarks(pcdao.getTheorycutoffmarks());
                if (theorydao.getCorrectanswer().equals(questiondao.getCorrect_option())) {
                    pcwisedao.setScoredtheorymarks(questiondao.getMarks());
                } else {
                    pcwisedao.setScoredtheorymarks(0);
                }

                pcwisedao.setPracticalmarks(pcdao.getPracticalcutoffmarks());
                int totalpracticalmarks = calculatePracticalMarksByPCID(pcdao.getPcID(), userid, userresultdetailid);
                pcwisedao.setScoredpracticalmarks(totalpracticalmarks);
                int totalmarks = Integer.parseInt(pcdao.getTheorycutoffmarks()) + Integer.parseInt(pcdao.getPracticalcutoffmarks());
                pcwisedao.setTotalmarks("" + totalmarks);
                pcwisedao.setScoredtotalmarks("--");

                pcparam.put(pcid, pcwisedao);

            }

        }

        return pcparam;
    }

    private int calculatePracticalmarksQPackID(int qpackid, int userid, int userresultdetailid) {

        int totalscoredmarks = 0;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PracticalWiseresultDAO pwrdao = (PracticalWiseresultDAO) itr.next();
                int questionid = pwrdao.getQuestionid();

                Map param1 = new HashMap();
                param1.put("qpackid", "" + qpackid);
                List<SuperBean> records1 = this.superService.listAllObjectsByCriteria(new NOSDAO(), param1);
                if (records1.size() > 0) {
                    Iterator itr1 = records1.iterator();
                    while (itr1.hasNext()) {
                        NOSDAO nosdao = (NOSDAO) itr1.next();

                        Map param2 = new HashMap();
                        param2.put("nosid", "" + nosdao.getNosID());
                        List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new PCDAO(), param2);
                        if (records2.size() > 0) {
                            Iterator itr2 = records2.iterator();
                            while (itr2.hasNext()) {
                                PCDAO pcdao = (PCDAO) itr2.next();
                                int pcid = pcdao.getPcID();

                                System.out.println("  questionid " + questionid);
                                System.out.println("  pcid " + pcid);
                                if (pwrdao.getAnswerstatus().equalsIgnoreCase("yes")) {
                                    Map param3 = new HashMap();
                                    param3.put("question_id", questionid);
                                    param3.put("pcid", pcid);
                                    List<SuperBean> records3 = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param3);
                                    if (records3.size() > 0) {
                                        PCWithMarksDAO pcwithmarks = (PCWithMarksDAO) records3.get(0);
                                        System.out.println("  marks " + pcwithmarks.getMarks());
                                        totalscoredmarks = totalscoredmarks + pcwithmarks.getMarks();
                                    }
                                }
                            }

                        }

                    }
                }

            }

        }

        System.out.println("  totalscoredmarks ::::: ::: " + totalscoredmarks);

        return totalscoredmarks;

    }

    private int calculatePracticalmarksNOSID(int nosid, int userid, int userresultdetailid) {

        int totalscoredmarks = 0;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PracticalWiseresultDAO pwrdao = (PracticalWiseresultDAO) itr.next();
                int questionid = pwrdao.getQuestionid();
                Map param2 = new HashMap();
                param2.put("nosid", "" + nosid);
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new PCDAO(), param2);
                if (records2.size() > 0) {
                    Iterator itr2 = records2.iterator();
                    while (itr2.hasNext()) {
                        PCDAO pcdao = (PCDAO) itr2.next();
                        int pcid = pcdao.getPcID();

                        System.out.println("  questionid " + questionid);
                        System.out.println("  pcid " + pcid);
                        if (pwrdao.getAnswerstatus().equalsIgnoreCase("yes")) {
                            Map param3 = new HashMap();
                            param3.put("question_id", questionid);
                            param3.put("pcid", pcid);
                            List<SuperBean> records3 = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param3);
                            if (records3.size() > 0) {
                                PCWithMarksDAO pcwithmarks = (PCWithMarksDAO) records3.get(0);
                                System.out.println("  marks " + pcwithmarks.getMarks());
                                totalscoredmarks = totalscoredmarks + pcwithmarks.getMarks();
                            }
                        }
                    }

                }
            }

        }

        System.out.println("  totalscoredmarks ::::: ::: " + totalscoredmarks);

        return totalscoredmarks;

    }

    private int calculatePracticalMarksByPCID(int pcid, int userid, int userresultdetailid) {

        int totalscoredmarks = 0;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PracticalWiseresultDAO pwrdao = (PracticalWiseresultDAO) itr.next();
                int questionid = pwrdao.getQuestionid();
                System.out.println("  questionid " + questionid);
                System.out.println("  pcid " + pcid);
                if (pwrdao.getAnswerstatus().equalsIgnoreCase("yes")) {
                    Map param3 = new HashMap();
                    param3.put("question_id", questionid);
                    param3.put("pcid", pcid);
                    List<SuperBean> records3 = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param3);
                    if (records3.size() > 0) {
                        PCWithMarksDAO pcwithmarks = (PCWithMarksDAO) records3.get(0);
                        System.out.println("  marks " + pcwithmarks.getMarks());
                        totalscoredmarks = totalscoredmarks + pcwithmarks.getMarks();
                    }
                }

            }
        }
        System.out.println("  totalscoredmarks ::::: ::: " + totalscoredmarks);

        return totalscoredmarks;

    }
}
