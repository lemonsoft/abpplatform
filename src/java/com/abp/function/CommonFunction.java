/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.function;

import com.abp.admin.batches.UserDAO;
import com.abp.admin.practicalmmq.PCWithMarksDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.result.PracticalWiseresultDAO;
import com.abp.admin.result.TheoryWiseResultDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ss
 */
public class CommonFunction {

    public int getTotalUserOfBatch(int batchid, SuperService superService) {

        int totalusers = 0;
        Map param2 = new HashMap();
        param2.put("batchid", batchid);
        List<SuperBean> records2 = superService.listAllObjectsByCriteria(new UserDAO(), param2);
        if (records2.size() > 0) {
            totalusers = records2.size();
        }

        return totalusers;
    }

    public int getTotalUserPass(int batchid, int qpackid, SuperService superService) {

        int totalpass = 0;

        Map param2 = new HashMap();
        param2.put("batchid", batchid);
        List<SuperBean> records2 = superService.listAllObjectsByCriteria(new UserDAO(), param2);
        if (records2.size() > 0) {
            Iterator itr2 = records2.iterator();
            while (itr2.hasNext()) {
                UserDAO data2 = (UserDAO) itr2.next();
                boolean theoryflag = isTheoryPass(qpackid, data2.getID(), superService);
                boolean practicalflag = isPracticalPass(qpackid, data2.getID(), superService);
                if (theoryflag && practicalflag) {
                    totalpass++;
                }
            }
        }
        System.out.println("Total Pass :::::" + totalpass);

        return totalpass;
    }

    public boolean isTheoryPass(int qpackid, int userid, SuperService superService) {

        boolean flag = false;
        int totaltheorymarks = 0;
        Map param2 = new HashMap();
        param2.put("userid", userid);
        List<SuperBean> records2 = superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param2);
        if (records2.size() > 0) {
            Iterator itr2 = records2.iterator();
            while (itr2.hasNext()) {
                TheoryWiseResultDAO data2 = (TheoryWiseResultDAO) itr2.next();
                totaltheorymarks = totaltheorymarks + getQuestionMarks(data2.getQuestionid(), data2.getCorrectanswer(), superService);
            }
        }
        System.out.println("Total Theory Marks :::::" + totaltheorymarks);
        QualificationPackDAO qpackdao = (QualificationPackDAO) superService.getObjectById(new QualificationPackDAO(), qpackid);
        if (Integer.parseInt(qpackdao.getTheorycutoffmarks()) == totaltheorymarks) {
            flag = true;
        }

        return flag;
    }

    public boolean isPracticalPass(int qpackid, int userid, SuperService superService) {
        boolean flag = false;
        int totalpracticalmarks = 0;
        Map param2 = new HashMap();
        param2.put("userid", userid);
        List<SuperBean> records2 = superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param2);
        if (records2.size() > 0) {
            Iterator itr2 = records2.iterator();
            while (itr2.hasNext()) {
                PracticalWiseresultDAO data2 = (PracticalWiseresultDAO) itr2.next();
                totalpracticalmarks = totalpracticalmarks + getPracticalMarks(data2.getQuestionid(), data2.getAnswerstatus(), superService);
            }
        }
        QualificationPackDAO qpackdao = (QualificationPackDAO) superService.getObjectById(new QualificationPackDAO(), qpackid);
        if (Integer.parseInt(qpackdao.getPracticalcutoffmarks()) == totalpracticalmarks) {
            flag = true;
        }

        return flag;
    }

    public int getQuestionMarks(int questionid, String correctanswer, SuperService superService) {

        System.out.println("Question ID :::::" + questionid);
        int questmarks = 0;
        Map param2 = new HashMap();
        param2.put("id", questionid);
        List<SuperBean> records2 = superService.listAllObjectsByCriteria(new QuestionDAO(), param2);
        if (records2.size() > 0) {
            QuestionDAO questdao = (QuestionDAO) records2.get(0);
            if (Integer.parseInt(questdao.getCorrect_option()) == Integer.parseInt(correctanswer)) {
                questmarks = questdao.getMarks();
            }
        }

        System.out.println("Question Marks :::::" + questmarks);
        return questmarks;
    }

    public int getPracticalMarks(int questionid, String correctanswer, SuperService superService) {

        int questmarks = 0;
        Map param2 = new HashMap();
        param2.put("question_id", questionid);
        List<SuperBean> records2 = superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param2);
        if (records2.size() > 0) {
            PCWithMarksDAO questdao = (PCWithMarksDAO) records2.get(0);
            if (correctanswer.equalsIgnoreCase("yes")) {
                questmarks = questdao.getMarks();
            }
        }
        return questmarks;
    }
}
