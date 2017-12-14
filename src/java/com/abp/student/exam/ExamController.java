/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.student.exam;

import com.abp.admin.generateqp.QuestionPaperDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.result.AssesmentLogDAO;
import com.abp.admin.result.QuestionWiseLogDAO;
import com.abp.admin.result.TheoryWiseResultDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/student/exam")
public class ExamController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/startexam", method = RequestMethod.GET)
    public String startExam(HttpServletRequest request, HttpServletResponse response, Model model) {

        Integer qpaperid = (Integer) request.getSession().getAttribute("qpaperid");
        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        String randomquestids = (String) request.getSession().getAttribute("questionids");

        try {
            String[] questionid = randomquestids.split(",");
            ArrayList questionids = new ArrayList<>(Arrays.asList(questionid));
            boolean examfinish = checkExamFinished(questionids, userresultdetailid, userid);

            if (examfinish) {
                request.getSession().setAttribute("questions", questionids);

                QuestionPaperDAO qpaperdao = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), qpaperid);
                if (qpaperdao.getIsoptionrandom().equals("on")) {
                    request.getSession().setAttribute("isoptionrandom", "on");
                }
                model.addAttribute("maxtime", qpaperdao.getTotaltime());
                model.addAttribute("totalquestion", questionids.size());
                assesmentlogdao("Start Exam", userresultdetailid, userid);
                request.getSession().setAttribute("body", "/student/exam/exam.jsp");
            } else {
                model.addAttribute("exam", "finish");
                request.getSession().setAttribute("body", "/student/exam/examfinish.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/student/login/finishexam.io";
        }
        return "student/common";
    }

    @RequestMapping(value = "/loadQuestionAnswered", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String loadQuestionAnswered(HttpServletRequest request) {

        JSONObject ansquestion = new JSONObject();
        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                TheoryWiseResultDAO objdao = (TheoryWiseResultDAO) itr.next();
                if (objdao.getReviewlater().equalsIgnoreCase("yes")) {
                    ansquestion.append("Qno", objdao.getQuestionno());
                    ansquestion.append("color", "yellow");
                } else {
                    ansquestion.append("Qno", objdao.getQuestionno());
                    ansquestion.append("color", "green");
                }

            }
        }
        System.out.println(ansquestion);
        return ansquestion.toString();
    }

    @RequestMapping(value = "/getRemainingTime", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getRemainingTime(HttpServletRequest request) {

        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        //Integer userid = (Integer) request.getSession().getAttribute("userid");
        JSONObject loadtimeremain = new JSONObject();
        UserResultDetailDAO userresultdaolist = (UserResultDetailDAO) this.superService.getObjectByIdGet(new UserResultDetailDAO(), userresultdetailid);
        if (userresultdaolist != null) {
            String[] rtime = userresultdaolist.getTimetaken().split(":");
            loadtimeremain.append("min", rtime[0]);
            loadtimeremain.append("sec", rtime[1]);
        } else {
            loadtimeremain.append("min", "60");
            loadtimeremain.append("sec", "00");
        }

        return loadtimeremain.toString();
    }

    @RequestMapping(value = "/firstQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String firstQuestion(@RequestParam("questionno") String questionno, HttpServletRequest request) {

        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");

        JSONObject loadquestion = new JSONObject();
        ArrayList questionids = (ArrayList) request.getSession().getAttribute("questions");

        String qnoget = questionids.get(0).toString();
        loadquestion = loadQuestionByID(request, new Integer(qnoget));
        loadquestion.append("selected", selectOption(1, userresultdetailid, userid));
        System.out.println(loadquestion);
        assesmentlogdao("Display Question " + (1), userresultdetailid, userid);
        questionwisedaostart(1, userresultdetailid, userid);
        return loadquestion.toString();
    }

    @RequestMapping(value = "/updateTimeTaken", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    void updateTimeTaken(@RequestParam("timetaken") String timetaken, HttpServletRequest request) {

        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        UserResultDetailDAO userresultdetail = (UserResultDetailDAO) this.superService.getObjectById(new UserResultDetailDAO(), userresultdetailid);
        userresultdetail.setTimetaken(timetaken);

        this.superService.updateObject(userresultdetail);
    }

    @RequestMapping(value = "/loadQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String loadQuestion(@RequestParam("questionno") String questionno, @RequestParam("timetaken") String timetaken, @RequestParam("answer") String answer, HttpServletRequest request) {

        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        boolean flag = true;
        JSONObject loadquestion = new JSONObject();
        ArrayList questionids = (ArrayList) request.getSession().getAttribute("questions");
        int qno = new Integer(questionno);
        System.out.println("Question No " + qno);
        System.out.println("ArrayList Size " + questionids.size());
        if (qno > 0) {

            if (questionids.size() > qno) {

                String qnoget = questionids.get(qno - 1).toString();
                boolean flagopt = checkQuestionReviewed(new Integer(questionno), userresultdetailid, userid);
                if (flagopt) {
                    updateCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid);
                } else {
                    boolean flagans = checkQuestionAnswered(new Integer(questionno), userresultdetailid, userid);
                    if (flagans) {
                        saveCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid, "No");
                    }

                }

                questionwisedaoend(qno, timetaken);
                assesmentlogdao("Question " + questionno + " Answered", userresultdetailid, userid);
                String qnonext = questionids.get(qno).toString();
                loadquestion = loadQuestionByID(request, new Integer(qnonext));
            } else if (questionids.size() == qno) {

                String qnoget = questionids.get(qno - 1).toString();
                boolean flagopt = checkQuestionReviewed(new Integer(questionno), userresultdetailid, userid);
                if (flagopt) {
                    updateCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid);
                } else {
                    boolean flagans = checkQuestionAnswered(new Integer(questionno), userresultdetailid, userid);
                    if (flagans) {
                        saveCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid, "No");
                    }
                }
                questionwisedaoend(qno, timetaken);
                assesmentlogdao("Question " + questionno + " Answered", userresultdetailid, userid);
                boolean flagremain = checkQuestionRemainsUnAnswer(userresultdetailid, userid, questionids.size());
                if (flagremain) {
                    loadquestion.append("status", "remains");
                } else {
                    loadquestion.append("status", "finish");
                }

                flag = false;

            } else {
                loadquestion.append("status", "finish");
                flag = false;
            }

        } else {

            String qnoget = questionids.get(0).toString();
            saveCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid, "No");
            questionwisedaoend(qno, timetaken);
            assesmentlogdao("Question " + questionno + " Answered", userresultdetailid, userid);

            loadquestion = loadQuestionByID(request, new Integer(qnoget));
        }
        loadquestion.append("selected", selectOption(qno+1, userresultdetailid, userid));
        System.out.println(loadquestion);

        if (flag) {
            assesmentlogdao("Display Question " + (qno + 1), userresultdetailid, userid);
            questionwisedaostart(qno + 1, userresultdetailid, userid);
        }
        if (qno != questionids.size()) {
            boolean flagremain = checkQuestionRemainsUnAnswer(userresultdetailid, userid, questionids.size());
            if (!flagremain) {
                loadquestion.append("status", "finish");
            }
        }

        return loadquestion.toString();
    }

    @RequestMapping(value = "/reviewQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String reviewQuestion(@RequestParam("questionno") String questionno, @RequestParam("timetaken") String timetaken, @RequestParam("answer") String answer, HttpServletRequest request) {

        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        boolean flag = true;
        JSONObject loadquestion = new JSONObject();
        ArrayList questionids = (ArrayList) request.getSession().getAttribute("questions");
        int qno = new Integer(questionno);
        System.out.println("Question No " + qno);
        System.out.println("ArrayList Size " + questionids.size());
        if (qno > 0) {
            if (questionids.size() > qno) {
                String qnoget = questionids.get(qno - 1).toString();
                saveCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid, "Yes");
                questionwisedaoend(qno, timetaken);
                assesmentlogdao("Question " + questionno + " Review Later", userresultdetailid, userid);
                String qnonext = questionids.get(qno).toString();
                loadquestion = loadQuestionByID(request, new Integer(qnonext));
            } else if (questionids.size() == qno) {

                String qnoget = questionids.get(qno - 1).toString();
                saveCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid, "No");
                questionwisedaoend(qno, timetaken);
                assesmentlogdao("Question " + questionno + " Answered", userresultdetailid, userid);
                boolean flagremain = checkQuestionRemainsUnAnswer(userresultdetailid, userid, questionids.size());
                if (flagremain) {
                    loadquestion.append("status", "remains");
                } else {
                    loadquestion.append("status", "finish");
                }
                flag = false;

            } else {
                loadquestion.append("status", "finish");
                flag = false;
            }

        } else {
            //int getquestionno = (int) questionids.get(0);
            String qnoget = questionids.get(0).toString();
            saveCorrectAnswer(qno, new Integer(qnoget), answer, timetaken, userresultdetailid, userid, "Yes");
            questionwisedaoend(qno, timetaken);
            assesmentlogdao("Question " + questionno + " Review Later", userresultdetailid, userid);
            loadquestion = loadQuestionByID(request, new Integer(qnoget));
        }
        System.out.println(loadquestion);
        if (flag) {
            assesmentlogdao("Display Question " + (qno + 1), userresultdetailid, userid);
            questionwisedaostart(qno + 1, userresultdetailid, userid);
        }
        if (qno != questionids.size()) {
            boolean flagremain = checkQuestionRemainsUnAnswer(userresultdetailid, userid, questionids.size());
            if (!flagremain) {
                loadquestion.append("status", "finish");
            }
        }

        return loadquestion.toString();
    }

    @RequestMapping(value = "/loadQuestionByNo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String loadQuestionByNo(@RequestParam("questionno") String questionno, HttpServletRequest request) {

        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        boolean flag = true;
        JSONObject loadquestion = new JSONObject();
        ArrayList questionids = (ArrayList) request.getSession().getAttribute("questions");
        int qno = new Integer(questionno);
        System.out.println("Question No " + qno);
        System.out.println("ArrayList Size " + questionids.size());
        if (qno > 0) {
            if (questionids.size() >= qno) {
                String qnoget = questionids.get(qno - 1).toString();
                loadquestion = loadQuestionByID(request, new Integer(qnoget));
            } else {
                loadquestion.append("status", "finish");
                flag = false;
            }

        } else {
            //int getquestionno = (int) questionids.get(0);
            String qnoget = questionids.get(0).toString();
            loadquestion = loadQuestionByID(request, new Integer(qnoget));
        }
        System.out.println(loadquestion);

        if (flag) {
            assesmentlogdao("Display Question " + (qno), userresultdetailid, userid);
            questionwisedaostart(qno, userresultdetailid, userid);
        }
        boolean flagremain = checkQuestionRemainsUnAnswer(userresultdetailid, userid, questionids.size());
        if (!flagremain) {
            loadquestion.append("status", "finish");
        }
        return loadquestion.toString();
    }

    @RequestMapping(value = "/loadQuestionResult", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String loadQuestionResult(@RequestParam("questionno") String questionno, HttpServletRequest request) {

        Integer userresultdetailid = (Integer) request.getSession().getAttribute("userresultdetailid");
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        boolean flag = true;
        JSONObject loadquestion = new JSONObject();
        ArrayList questionids = (ArrayList) request.getSession().getAttribute("questions");
        int qno = new Integer(questionno);
        System.out.println("Question No " + qno);
        System.out.println("ArrayList Size " + questionids.size());
        if (qno > 0) {
            if (questionids.size() >= qno) {
                String qnoget = questionids.get(qno - 1).toString();
                loadquestion = loadQuestionByID(request, new Integer(qnoget));
            } else {
                loadquestion.append("status", "finish");
                flag = false;
            }

        } else {
            //int getquestionno = (int) questionids.get(0);
            String qnoget = questionids.get(0).toString();
            loadquestion = loadQuestionByID(request, new Integer(qnoget));
        }
        System.out.println(loadquestion);
        loadquestion.append("selected", selectOption(qno, userresultdetailid, userid));
        if (flag) {
            assesmentlogdao("Display Question " + (qno), userresultdetailid, userid);
            questionwisedaostart(qno, userresultdetailid, userid);
        }
        boolean flagremain = checkQuestionRemainsUnAnswer(userresultdetailid, userid, questionids.size());
        System.out.println("flagremain " + flagremain);
        if (!flagremain) {
            loadquestion.append("status", "finish");
        }

        return loadquestion.toString();
    }

    private boolean checkQuestionRemainsUnAnswer(int userresultdetailid, int userid, int totalquestion) {

        boolean flag = true;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        //param.put("recorddate", sdf.format(datenow));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        if (records.size() > 0) {
            if (totalquestion == records.size()) {
                flag = false;
                System.out.println(" Exam finish  flag 3" + flag);
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    TheoryWiseResultDAO theorywise = (TheoryWiseResultDAO) itr.next();
                    if (theorywise.getReviewlater().equalsIgnoreCase("yes")) {
                        flag = false;
                        break;
                    }
                }
                System.out.println(" Exam finish  flag 4" + flag);

            }
        }

        return flag;
    }

    private void assesmentlogdao(String action, int userresultdetailid, int userid) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        Date datenow = new Date();
        AssesmentLogDAO assesmentlogdao = new AssesmentLogDAO();
        assesmentlogdao.setLogactiondate(sdf.format(datenow));
        assesmentlogdao.setActiontaken(action);
        assesmentlogdao.setUserresultdetailid(userresultdetailid);
        assesmentlogdao.setUserid(userid);
        this.superService.saveObject(assesmentlogdao);

    }

    private void questionwisedaostart(int questionno, int userresultdetailid, int userid) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        Date datenow = new Date();
        QuestionWiseLogDAO questionwisedao = new QuestionWiseLogDAO();
        questionwisedao.setQuestionno(questionno);
        questionwisedao.setStartdate(sdf.format(datenow));
        questionwisedao.setUserresultdetailid(userresultdetailid);
        questionwisedao.setUserid(userid);
        questionwisedao.setAssesmentid(0);
        questionwisedao.setEnddate("-");
        questionwisedao.setTimetaken("-");
        this.superService.saveObject(questionwisedao);

    }

    private void questionwisedaoend(int questionno, String timetaken) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        Date datenow = new Date();
        Map param = new HashMap();
        param.put("questionno", questionno);
        param.put("enddate", "-");
        param.put("timetaken", "-");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionWiseLogDAO(), param);
        QuestionWiseLogDAO questionwisedao = (QuestionWiseLogDAO) records.get(0);
        questionwisedao.setEnddate(sdf.format(datenow));
        questionwisedao.setTimetaken(timetaken);
        this.superService.updateObject(questionwisedao);

    }

    private boolean saveCorrectAnswer(int questionno, int questionid, String selectoption, String timetaken, int userresultdetailid, int userid, String review) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datenow = new Date();
        boolean flag = true;
        TheoryWiseResultDAO theoryresultdao = new TheoryWiseResultDAO();
        theoryresultdao.setQuestionid(questionid);
        theoryresultdao.setQuestionno(questionno);
        theoryresultdao.setCorrectanswer("" + selectoption);
        theoryresultdao.setTimetaken(timetaken);
        theoryresultdao.setUserresultdetailid(userresultdetailid);
        theoryresultdao.setUserid(userid);
        theoryresultdao.setReviewlater(review);
        theoryresultdao.setRecorddate(sdf.format(datenow));

        try {
            this.superService.saveObject(theoryresultdao);
        } catch (Exception e) {
            flag = true;
        }

        return flag;
    }

    private boolean checkQuestionReviewed(int questionno, int userresultdetailid, int userid) {

        boolean flag = false;
        Map param = new HashMap();
        param.put("questionno", questionno);
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        param.put("reviewlater", "Yes");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        if (records.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private boolean checkQuestionAnswered(int questionno, int userresultdetailid, int userid) {

        boolean flag = true;
        Map param = new HashMap();
        param.put("questionno", questionno);
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        param.put("reviewlater", "No");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        if (records.size() > 0) {
            flag = false;
        }
        return flag;
    }

    private boolean updateCorrectAnswer(int questionno, int questionid, String selectoption, String timetaken, int userresultdetailid, int userid) {

        boolean flag = true;
        Map param = new HashMap();
        param.put("questionno", questionno);
        param.put("questionid", questionid);
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        param.put("reviewlater", "Yes");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        if (records.size() > 0) {
            TheoryWiseResultDAO theoryresultdao = (TheoryWiseResultDAO) records.get(0);
            theoryresultdao.setCorrectanswer(selectoption);
            int timetakenprev = new Integer(theoryresultdao.getTimetaken());
            int curnttimetaken = new Integer(timetaken);
            int totaltimetaken = timetakenprev + curnttimetaken;
            theoryresultdao.setTimetaken("" + totaltimetaken);
            theoryresultdao.setReviewlater("No");
            this.superService.updateObject(theoryresultdao);
        } else {
            flag = false;
        }

        return flag;
    }

    private String selectOption(int questionno, int userresultdetailid, int userid) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datenow = new Date();
        String selectoption = "";
        Map param = new HashMap();
        param.put("questionno", questionno);
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        //param.put("recorddate", sdf.format(datenow));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        if (records.size() > 0) {
            TheoryWiseResultDAO theorywisedao = (TheoryWiseResultDAO) records.get(0);
            selectoption = theorywisedao.getCorrectanswer();
        }
        System.out.println("Selected option :" + selectoption);
        return selectoption;
    }

    private JSONObject loadQuestionByID(HttpServletRequest request, int loadquestion) {

        JSONObject jsonObj = new JSONObject();
        System.out.println("Load Question paper Id " + loadquestion);
        QuestionDAO question = (QuestionDAO) this.superService.getObjectByIdGet(new QuestionDAO(), loadquestion);
        if (question != null) {
            jsonObj.append("question", question.getQuestion_title());
            jsonObj.append("questionimgurl", question.getQuestionimgurl());
            String isoptionrandom = (String) request.getSession().getAttribute("isoptionrandom");
            if (isoptionrandom.equals("on")) {
                jsonObj.append("optionno1", "3");
                jsonObj.append("option1", question.getOption3());
                jsonObj.append("option1imgurl", question.getImageurl3());
                jsonObj.append("optionno2", "1");
                jsonObj.append("option2", question.getOption1());
                jsonObj.append("option2imgurl", question.getImageurl1());
                jsonObj.append("optionno3", "4");
                jsonObj.append("option3", question.getOption4());
                jsonObj.append("option3imgurl", question.getImageurl4());
                jsonObj.append("optionno4", "2");
                jsonObj.append("option4", question.getOption2());
                jsonObj.append("option4imgurl", question.getImageurl2());
            } else {
                jsonObj.append("optionno1", "1");
                jsonObj.append("option1", question.getOption1());
                jsonObj.append("option1imgurl", question.getImageurl1());
                jsonObj.append("optionno2", "2");
                jsonObj.append("option2", question.getOption2());
                jsonObj.append("option2imgurl", question.getImageurl2());
                jsonObj.append("optionno3", "3");
                jsonObj.append("option3", question.getOption3());
                jsonObj.append("option3imgurl", question.getImageurl3());
                jsonObj.append("optionno4", "4");
                jsonObj.append("option4", question.getOption4());
                jsonObj.append("option4imgurl", question.getImageurl4());
            }

        }

        return jsonObj;

    }

    private boolean checkExamFinished(ArrayList questionids, int userresultdetailid, int userid) {

        boolean flag = true;
        Map param2 = new HashMap();
        param2.put("ID", userresultdetailid);
        param2.put("userid", userid);
        List<SuperBean> userresultdaolist = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param2);
        if (userresultdaolist.size() > 0) {
            UserResultDetailDAO userdao = (UserResultDetailDAO) userresultdaolist.get(0);
            String timeremain[] = userdao.getTimetaken().split(":");
            if (timeremain[0].equals("00")&&timeremain[1].equals("00")) {
                flag = false;
            }
        }
        System.out.println(" Exam finiahs  flag 1" + flag);
        if (flag) {
            System.out.println(" Exam finiahs  flag 2" + flag);
            Map param = new HashMap();
            param.put("userresultdetailid", userresultdetailid);
            param.put("userid", userid);
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
            System.out.println(questionids.size() + "  -  -" + records.size());
            if (questionids.size() == records.size()) {
                flag = false;
                System.out.println(" Exam finiahs  flag 3" + flag);
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    TheoryWiseResultDAO theorywise = (TheoryWiseResultDAO) itr.next();
                    if (theorywise.getReviewlater().equalsIgnoreCase("yes")) {
                        flag = true;
                        break;
                    }
                }
                System.out.println(" Exam finiahs  flag 4" + flag);

            }
        }

        System.out.println(" Exam finiahs  flag 5" + flag);

        return flag;
    }

    private boolean checkAllQuestionAnswered(ArrayList questionids, int userresultdetailid, int userid) {

        boolean flag = true;
        Map param = new HashMap();
        param.put("userresultdetailid", userresultdetailid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
        System.out.println(questionids.size() + "  -  -" + records.size());
        if (questionids.size() == records.size()) {
            flag = true;
            System.out.println(" Exam finish  flag 3" + flag);
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                TheoryWiseResultDAO theorywise = (TheoryWiseResultDAO) itr.next();
                if (theorywise.getReviewlater().equalsIgnoreCase("yes")) {
                    flag = false;
                    break;
                }
            }
            System.out.println(" Exam finish  flag 4" + flag);

        }

        return flag;
    }
}
