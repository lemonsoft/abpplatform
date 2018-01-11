/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.consolidatejobroleresult;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.practicalmmq.PCWithMarksDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.result.PracticalWiseresultDAO;
import com.abp.admin.result.TheoryWiseResultDAO;
import com.abp.admin.result.UserResultDetailDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.function.CommonFunction;
import com.abp.statedistrict.DistrictDAO;
import com.abp.statedistrict.StateDAO;
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
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
@RequestMapping("/admin/jobroleresult")
public class ConsolidateJobRoleResult {
    
    private static final Logger logger = Logger.getLogger(ConsolidateJobRoleResult.class);
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
        
        model.addAttribute("jobroledao", new DisplayJobRoleResult());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/consolidatejobroleresult/jobroleresult.jsp");
        return "admin/common";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, DisplayJobRoleResult beanObj, Model model) {
        
        System.out.println(" Sector " + beanObj.getSscid());
        System.out.println(" Job role " + beanObj.getQpackid());
        System.out.println(" Month " + beanObj.getMonth());
        
        ArrayList recordss = new ArrayList();
        QualificationPackDAO beanQpackObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(beanObj.getQpackid()));
        CommonFunction commonfunct = new CommonFunction();
        int i = 1;
        Map qpacks = new HashMap();
        qpacks.put("qpackId", new Integer(beanObj.getQpackid()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                BatchesDAO data = (BatchesDAO) itr.next();
                DisplayJobRoleResult dispjobroledao = new DisplayJobRoleResult();
                dispjobroledao.setSrno("" + i);
                dispjobroledao.setJobrole(beanQpackObj.getQpackname());
                dispjobroledao.setTpname(data.getTpName());
                dispjobroledao.setBatchid("" + data.getBatch_id());
                dispjobroledao.setCenterid("" + data.getCenter_id());
                dispjobroledao.setState(getStateNameById(data.getState_id()));
                dispjobroledao.setLocation(getDistrictNameById(data.getDistrict_id()));
                dispjobroledao.setAssesmentdate(data.getAssessmentEndDate());
                dispjobroledao.setAssesorname(getAssessorNameById(data.getAssessorId()));
                dispjobroledao.setTotalnostudent("" + getTotalStudent(data.getID()));
                dispjobroledao.setNotappeared("" + totalappear(data.getBatch_id()));
                int totalpass = commonfunct.getTotalUserPass(data.getID(), beanQpackObj.getQpid(), superService);
                System.out.println("Total user pass" + totalpass);
                dispjobroledao.setPass("" + totalpass);
                dispjobroledao.setFail("" + 0);
                recordss.add(dispjobroledao);
                i++;
            }
        }
        model.addAttribute("records", recordss);
        model.addAttribute("jobroledao", new DisplayJobRoleResult());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/consolidatejobroleresult/jobroleresult.jsp");
        return "admin/common";
    }
    
    @RequestMapping(value = "/writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
        
        String sscid = request.getParameter("sscid");
        String qpackid = request.getParameter("qpackid");
        String month = request.getParameter("month");
        
        ArrayList record = new ArrayList();
        QualificationPackDAO beanQpackObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));
        CommonFunction commonfunct = new CommonFunction();
        Map qpacks = new HashMap();
        qpacks.put("qpackId", new Integer(qpackid));
        List<SuperBean> recordsw = this.superService.listAllObjectsByCriteria(new BatchesDAO(), qpacks);
        if (recordsw.size() > 0) {
            Iterator itr = recordsw.iterator();
            while (itr.hasNext()) {
                BatchesDAO data = (BatchesDAO) itr.next();
                DisplayJobRoleResult dispjobroledao = new DisplayJobRoleResult();
                dispjobroledao.setJobrole(beanQpackObj.getQpackname());
                dispjobroledao.setTpname(data.getTpName());
                dispjobroledao.setBatchid("" + data.getBatch_id());
                dispjobroledao.setCenterid("" + data.getCenter_id());
                dispjobroledao.setState(getStateNameById(data.getState_id()));
                dispjobroledao.setLocation(getDistrictNameById(data.getDistrict_id()));
                dispjobroledao.setAssesmentdate(data.getAssessmentEndDate());
                dispjobroledao.setAssesorname(getAssessorNameById(data.getAssessorId()));
                dispjobroledao.setTotalnostudent("" + getTotalStudent(data.getID()));
                dispjobroledao.setNotappeared("" + totalappear(data.getBatch_id()));
                int totalpass = commonfunct.getTotalUserPass(data.getID(), beanQpackObj.getQpid(), superService);
                System.out.println("Total user pass" + totalpass);
                dispjobroledao.setPass("" + totalpass);
                dispjobroledao.setFail("" + 0);
                record.add(dispjobroledao);
            }
        }
        
        System.out.println(sscid + " : : Get Parameter Value " + qpackid);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("training_partner_wise");
        XSSFRow row = spreadsheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        row.setRowStyle(style);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("Sr.#");
        cell = row.createCell(1);
        cell.setCellValue("Job Role");
        cell = row.createCell(2);
        cell.setCellValue("TP Name");
        cell = row.createCell(3);
        cell.setCellValue("Batch ID");
        cell = row.createCell(4);
        cell.setCellValue("Centre ID");
        cell = row.createCell(5);
        cell.setCellValue("State");
        cell = row.createCell(6);
        cell.setCellValue("Location");
        cell = row.createCell(7);
        cell.setCellValue("Assessor Name");
        cell = row.createCell(8);
        cell.setCellValue("Pass");
        cell = row.createCell(9);
        cell.setCellValue("Fail");
        cell = row.createCell(10);
        cell.setCellValue("Not Appeared");
        cell = row.createCell(11);
        cell.setCellValue("Total No. of Students");
        cell = row.createCell(12);
        cell.setCellValue("Assessment Date");
        cell = row.createCell(13);
        cell.setCellValue("Result Submission - Planned Date");
        cell = row.createCell(14);
        cell.setCellValue("Result Submission - Actual Date");
        cell = row.createCell(15);
        cell.setCellValue("Status");
        cell = row.createCell(16);
        cell.setCellValue("Deviation in Number of Days");
        
        System.out.println("Record : " + record.size());
        if (record.size() > 0) {
            Iterator itr = record.iterator();
            int ik = 1;
            while (itr.hasNext()) {
                DisplayJobRoleResult dispqbank = (DisplayJobRoleResult) itr.next();
                row = spreadsheet.createRow(ik);
                cell = row.createCell(0);
                cell.setCellValue(ik);
                cell = row.createCell(1);
                cell.setCellValue(dispqbank.getJobrole());
                cell = row.createCell(2);
                cell.setCellValue(dispqbank.getTpname());
                cell = row.createCell(3);
                cell.setCellValue(dispqbank.getBatchid());
                cell = row.createCell(4);
                cell.setCellValue(dispqbank.getCenterid());
                cell = row.createCell(5);
                cell.setCellValue(dispqbank.getState());
                cell = row.createCell(6);
                cell.setCellValue(dispqbank.getLocation());
                cell = row.createCell(7);
                cell.setCellValue(dispqbank.getAssesorname());
                cell = row.createCell(8);
                cell.setCellValue(dispqbank.getPass());
                cell = row.createCell(9);
                cell.setCellValue(dispqbank.getFail());
                cell = row.createCell(10);
                cell.setCellValue(dispqbank.getNotappeared());
                cell = row.createCell(11);
                cell.setCellValue(dispqbank.getTotalnostudent());
                cell = row.createCell(12);
                cell.setCellValue(dispqbank.getAssesmentdate());
                cell = row.createCell(13);
                cell.setCellValue(dispqbank.getResultsubmissonplanneddate());
                cell = row.createCell(14);
                cell.setCellValue(dispqbank.getResultsubmissonactualdate());
                cell = row.createCell(15);
                cell.setCellValue(dispqbank.getStatus());
                cell = row.createCell(16);
                cell.setCellValue(dispqbank.getDeviationnoofdays());
                ik++;
            }
        }
        
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=training_partner_wise.xls");
            
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            System.out.println("Code is Here...");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        System.out.println("Code is Here...");
        
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
    
    private String getStateNameById(int stateid) {
        
        String statename = "";
        StateDAO beanQpackObj = (StateDAO) this.superService.getObjectById(new StateDAO(), stateid);
        if (beanQpackObj != null) {
            statename = beanQpackObj.getStateName();
        }
        return statename;
    }
    
    private String getDistrictNameById(int districtid) {
        
        String districtname = "";
        DistrictDAO beanQpackObj = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), districtid);
        if (beanQpackObj != null) {
            districtname = beanQpackObj.getDistrictName();
        }
        return districtname;
    }
    
    private String getAssessorNameById(int assessorid) {
        
        String assessorname = "";
        AssessorDAO beanQpackObj = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), assessorid);
        if (beanQpackObj != null) {
            assessorname = beanQpackObj.getFirstname();
        }
        return assessorname;
    }
    
    private int getTotalStudent(int batchid) {
        
        System.out.println("  batchid  : : : " + batchid);
        int totalstudent = 0;
        Map param = new HashMap();
        param.put("batchid", batchid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        if (records.size() > 0) {
            totalstudent = records.size();
        }
        
        return totalstudent;
    }
    
    private int totalappear(int batchid) {
        
        int totalstudent = 0;
        Map param = new HashMap();
        param.put("batchid", batchid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                UserDAO userdao = (UserDAO) itr.next();
                
                Map param2 = new HashMap();
                param2.put("userid", userdao.getID());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param2);
                if (records2.size() > 0) {
                    
                    totalstudent++;
                }
            }
        }
        
        return totalstudent;
        
    }
    
    public int calculateTotalPass(int batchid, int qpackid) {
        
        int totalpass = 0;
        Map param = new HashMap();
        param.put("batchid", batchid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                UserDAO userdao = (UserDAO) itr.next();
                
            }
        }
        
        return totalpass;
    }
    
    private boolean checkAllTheoryQuestions(int userid, int userdetailid, int qpackid) {
        
        boolean flag = false;
        Map param = new HashMap();
        param.put("userid", userid);
        param.put("ID", userdetailid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
        if (records.size() > 0) {
            UserResultDetailDAO data = (UserResultDetailDAO) records.get(0);
            int totaltheorymarks = getTotalTheoryMarks(userid, userdetailid, data.getQuestionids());
            QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
            int getTheoryCutoffmarks = Integer.parseInt(beanObj.getTheorycutoffmarks());
            if (totaltheorymarks > getTheoryCutoffmarks) {
                flag = true;
            }
        }
        
        return flag;
    }
    
    private int getTotalTheoryMarks(int userid, int userdetailid, String questionids) {
        
        int totaltheorymarks = 0;
        String questions[] = questionids.split(",");
        for (int i = 0; i < questions.length; i++) {
            Map param = new HashMap();
            param.put("userid", userid);
            param.put("userresultdetailid", userdetailid);
            param.put("questionid", Integer.parseInt(questions[i]));
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryWiseResultDAO(), param);
            if (records.size() > 0) {
                TheoryWiseResultDAO data = (TheoryWiseResultDAO) records.get(0);
                String selectanswer = data.getCorrectanswer();
                totaltheorymarks = totaltheorymarks + getMarksofQuestion(questions[i], selectanswer);
            }
            
        }
        return totaltheorymarks;
    }
    
    private int getMarksofQuestion(String questionid, String selectOption) {
        
        int marks = 0;
        QuestionDAO questdao = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), Integer.parseInt(questionid));
        if (questdao != null) {
            String corectopt = questdao.getCorrect_option();
            if (Integer.parseInt(corectopt) == Integer.parseInt(selectOption)) {
                marks = questdao.getMarks();
            }
            
        }
        return marks;
    }
    
    private boolean checkAllPracticalQuestion(int userid, int userdetailid, int qpackid) {
        
        boolean flag = false;
        int totalpracticalmarks = 0;
        Map param = new HashMap();
        param.put("userid", userid);
        param.put("userresultdetailid", userdetailid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PracticalWiseresultDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PracticalWiseresultDAO data = (PracticalWiseresultDAO) itr.next();
                if (data.getAnswerstatus().equalsIgnoreCase("yes")) {
                    totalpracticalmarks = totalpracticalmarks + getAllPracticalmarks(data.getQuestionid());
                }
                
            }
        }
        QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
        int getPracticalCutoffmarks = Integer.parseInt(beanObj.getPracticalcutoffmarks());
        if (totalpracticalmarks > getPracticalCutoffmarks) {
            flag = true;
        }
        
        return flag;
    }
    
    private int getAllPracticalmarks(int questionid) {
        
        int marks = 0;
        Map param = new HashMap();
        param.put("question_id", questionid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param);
        if (records.size() > 0) {
            PCWithMarksDAO pcmarks = (PCWithMarksDAO) records.get(0);
            marks = pcmarks.getMarks();
        }
        
        return marks;
    }
    
    private boolean isPracticalPass(int userid, int batchid, int qpackid) {
        
        boolean flagthry = false;
        Map param = new HashMap();
        param.put("userid", userid);
        param.put("batchid", batchid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                UserResultDetailDAO data = (UserResultDetailDAO) itr.next();
                flagthry = checkAllPracticalQuestion(userid, data.getID(), qpackid);
            }
        }
        return flagthry;
    }
    
    private boolean isTheoryPass(int userid, int batchid, int qpackid) {
        
        boolean flagthry = false;
        Map param = new HashMap();
        param.put("userid", userid);
        param.put("batchid", batchid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                UserResultDetailDAO data = (UserResultDetailDAO) itr.next();
                flagthry = checkAllTheoryQuestions(userid, data.getID(), qpackid);
            }
        }
        
        return flagthry;
    }
}
