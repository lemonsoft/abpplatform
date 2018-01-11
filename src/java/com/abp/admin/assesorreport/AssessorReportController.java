/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.assesorreport;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.questionbankanalysis.DisplayQuestionBank;
import com.abp.admin.ssc.SSCDAO;
import com.abp.statedistrict.DistrictDAO;
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

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/assesorreport")
public class AssessorReportController {

    private static final Logger logger = Logger.getLogger(AssessorReportController.class);
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

        model.addAttribute("assessordao", new AssessorReportDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/assessorreport/assessorreport.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, AssessorReportDAO assesordao, Model model) {

        System.out.println("Sector Skill Council : : : : " + assesordao.getSscid());
        System.out.println("Month : : : : " + assesordao.getMonth());

        ArrayList recordall = new ArrayList();
        int ik = 1;
        Map param = new HashMap();
        param.put("ssc_id", Integer.parseInt(assesordao.getSscid()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                AssessorReportDAO areportdao = new AssessorReportDAO();
                AssessorDAO data = (AssessorDAO) itr.next();
                areportdao.setSrno("" + ik);
                areportdao.setAssessorname(data.getFirstname());
                System.out.println("Assesor Job fetch : : : " + data.getJobrole());
                String[] jobrole = data.getJobrole().split(",");
                String jobrolename = "";
                for (int i = 0; i < jobrole.length; i++) {
                    jobrolename = jobrolename + "," + getQualificationPackNameById(Integer.parseInt(jobrole[i]));
                }
                areportdao.setJobroles(jobrolename.substring(1));
                areportdao.setLocation(getDistrictNameById(data.getDistrictid()));
                areportdao.setDatesoccupiedinmonth("" + getTotalDateOccupied(data.getAssessorid()));
                recordall.add(areportdao);
                ik++;
            }
        }

        model.addAttribute("recordall", recordall);
        model.addAttribute("assessordao", assesordao);
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/assessorreport/assessorreport.jsp");
        return "admin/common";

    }
    @RequestMapping(value = "/writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletRequest request, HttpServletResponse response, AssessorReportDAO assesordao, Model model) {
        
        System.out.println("Sector Skill Council : : : : " + assesordao.getSscid());
        System.out.println("Month : : : : " + assesordao.getMonth());

        ArrayList recordall = new ArrayList();
        int ik = 1;
        Map param = new HashMap();
        param.put("ssc_id", Integer.parseInt(assesordao.getSscid()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                AssessorReportDAO areportdao = new AssessorReportDAO();
                AssessorDAO data = (AssessorDAO) itr.next();
                areportdao.setSrno("" + ik);
                areportdao.setAssessorname(data.getFirstname());
                System.out.println("Assesor Job fetch : : : " + data.getJobrole());
                String[] jobrole = data.getJobrole().split(",");
                String jobrolename = "";
                for (int i = 0; i < jobrole.length; i++) {
                    jobrolename = jobrolename + "," + getQualificationPackNameById(Integer.parseInt(jobrole[i]));
                }
                areportdao.setJobroles(jobrolename.substring(1));
                areportdao.setLocation(getDistrictNameById(data.getDistrictid()));
                areportdao.setDatesoccupiedinmonth("" + getTotalDateOccupied(data.getAssessorid()));
                recordall.add(areportdao);
                ik++;
            }
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("question_bank_analysis");

        XSSFRow row = spreadsheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        row.setRowStyle(style);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("Sr.#");
        cell = row.createCell(1);
        cell.setCellValue("Assessor Name");
        cell = row.createCell(2);
        cell.setCellValue("Job Role");
        cell = row.createCell(3);
        cell.setCellValue("Location");
        cell = row.createCell(4);
        cell.setCellValue("Dates Occupied in Month");
       // cell = row.createCell(5);
       // cell.setCellValue("Correct Attempt");
        //cell = row.createCell(6);
       // cell.setCellValue("Not Attempted");

        if (recordall.size() > 0) {
            Iterator itr = recordall.iterator();
            int i = 1;
            while (itr.hasNext()) {
                AssessorReportDAO areportdao = (AssessorReportDAO) itr.next();
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(i);
                cell = row.createCell(1);
                cell.setCellValue(areportdao.getAssessorname());
                cell = row.createCell(2);
                cell.setCellValue(areportdao.getJobroles());
                cell = row.createCell(3);
                cell.setCellValue(areportdao.getLocation());
                cell = row.createCell(4);
                cell.setCellValue(areportdao.getDatesoccupiedinmonth());
                //cell = row.createCell(5);
                //cell.setCellValue(areportdao.getDatesoccupiedinmonth());
                //cell = row.createCell(6);
                //cell.setCellValue(dispqbank.getNotattempt());
                i++;
            }
        }
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=assessor_report.xls");

            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            System.out.println("Code is Here...");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        System.out.println("Code is Here...");
        
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

    public String getQualificationPackNameById(int qpackid) {

        String qpackname = "";
        QualificationPackDAO qpackdao = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);
        if (qpackdao != null) {
            qpackname = qpackdao.getQpackname();
        }
        return qpackname;

    }

    public String getDistrictNameById(int districtid) {

        String disname = "";
        DistrictDAO districtdao = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), districtid);
        if (districtdao != null) {
            disname = districtdao.getDistrictName();
        }
        return disname;
    }

    public int getTotalDateOccupied(int assesorid) {

        int totaldatedao = 0;
        Map param = new HashMap();
        param.put("assessorId", assesorid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param);
        if (records.size() > 0) {
            totaldatedao = records.size();
        }
        return totaldatedao;
    }

}
