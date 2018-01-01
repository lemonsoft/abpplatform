/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.resultsummary;

import com.abp.admin.batches.UserDAO;
import com.abp.admin.practicalmmq.PCWithMarksDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.NOSDAO;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.result.PracticalWiseresultDAO;
import com.abp.admin.result.TheoryWiseResultDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/resultsummary")
public class ResultSummary {

    private static final Logger logger = Logger.getLogger(ResultSummary.class);

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("resultdao", new ResultsummaryDAO());

        System.out.println("Testing code Here ...");

        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/report/result.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchUser(HttpServletRequest request, HttpServletResponse response, Model model, ResultsummaryDAO resultdao) {

        String batchid = resultdao.getBatchid();
        ArrayList arr = new ArrayList();
        Map param = new HashMap();
        param.put("batchid", new Integer(batchid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        System.out.println("Get Record Size :" + records.size());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                
                UserDAO userdao = (UserDAO) itr.next();
                                
                DisplayResult disp = new DisplayResult();
                disp.setTheoryresult("" + getTotalTheoryMarks(userdao.getID(), new Integer(batchid)));
                disp.setPracticalresult("" + calculatePracticalmarksQPackID(new Integer(batchid), userdao.getID()));
                arr.add(disp);

            }
        }

        ArrayList headers = new ArrayList();
        headers.add("PCID");
        headers.add("PC");
        headers.add("Total");
        headers.add("Theory");
        headers.add("Practical");

        Map param2 = new HashMap();
        param2.put("batchid", new Integer(batchid));
        List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new UserDAO(), param2);
        {
            if (records2.size() > 0) {
                Iterator itr2 = records2.iterator();
                while (itr2.hasNext()) {
                    UserDAO userdao = (UserDAO) itr2.next();
                    headers.add(userdao.getTraineename()+" Theory");
                    headers.add(userdao.getTraineename()+" Practical");
                }
            }

        }
        model.addAttribute("resultdao", new ResultsummaryDAO());
        model.addAttribute("headers", headers);
        model.addAttribute("arr", arr);
        model.addAttribute("mode", "add");
        
        request.getSession().setAttribute("body", "/admin/report/result.jsp");
        return "admin/common";
    }

    public int getTotalTheoryMarks(int userid, int batchid) {

        int totaltheorymarks = 0;

        Map param = new HashMap();
        param.put("batchid", batchid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                UserResultDetailDAO usrdao = (UserResultDetailDAO) itr.next();

                Map param2 = new HashMap();
                param2.put("userresultdetailid", usrdao.getID());
                param2.put("userid", userid);
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param2);
                Iterator itr2 = records2.iterator();
                while (itr2.hasNext()) {

                    TheoryWiseResultDAO theorywisedao = (TheoryWiseResultDAO) itr2.next();
                    totaltheorymarks = totaltheorymarks + getMarksByQuestionID(theorywisedao.getQuestionid(), theorywisedao.getCorrectanswer());
                    System.out.println(" totaltheorymarks   " + totaltheorymarks);
                }

            }
        }

        System.out.println("total totaltheorymarks   " + totaltheorymarks);

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

    private int calculatePracticalmarksQPackID(int batchid, int userid) {

        int totalscoredmarks = 0;
        Map param = new HashMap();
        param.put("batchid", batchid);
        param.put("userid", userid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                UserResultDetailDAO usrdao = (UserResultDetailDAO) itr.next();
                Map param1 = new HashMap();
                param1.put("userresultdetailid", usrdao.getID());
                param1.put("userid", userid);
                List<SuperBean> records1 = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param1);
                if (records1.size() > 0) {
                    Iterator itr1 = records1.iterator();
                    while (itr1.hasNext()) {
                        PracticalWiseresultDAO pwrdao = (PracticalWiseresultDAO) itr1.next();
                        int questionid = pwrdao.getQuestionid();

                        Map param2 = new HashMap();
                        //param2.put("qpackid", "" + qpackid);
                        List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new NOSDAO(), param2);
                        if (records2.size() > 0) {
                            Iterator itr2 = records2.iterator();
                            while (itr2.hasNext()) {
                                NOSDAO nosdao = (NOSDAO) itr2.next();

                                Map param3 = new HashMap();
                                param3.put("nosid", "" + nosdao.getNosID());
                                List<SuperBean> records3 = this.superService.listAllObjectsByCriteria(new PCDAO(), param3);
                                if (records3.size() > 0) {
                                    Iterator itr3 = records3.iterator();
                                    while (itr3.hasNext()) {
                                        PCDAO pcdao = (PCDAO) itr3.next();
                                        int pcid = pcdao.getPcID();

                                        System.out.println("  questionid " + questionid);
                                        System.out.println("  pcid " + pcid);
                                        if (pwrdao.getAnswerstatus().equalsIgnoreCase("yes")) {
                                            Map param4 = new HashMap();
                                            param4.put("question_id", questionid);
                                            param4.put("pcid", pcid);
                                            List<SuperBean> records4 = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param4);
                                            if (records4.size() > 0) {
                                                PCWithMarksDAO pcwithmarks = (PCWithMarksDAO) records4.get(0);
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
            }
        }

        System.out.println("  totalscoredmarks ::::: ::: " + totalscoredmarks);

        return totalscoredmarks;

    }
}
