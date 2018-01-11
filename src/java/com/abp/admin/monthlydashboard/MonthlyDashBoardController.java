/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.monthlydashboard;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.individualdashboard.DisplayIndividualDAO;
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
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
@RequestMapping("/admin/monthlydashboard")
public class MonthlyDashBoardController {

    private static final Logger logger = Logger.getLogger(MonthlyDashBoardController.class);
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

        model.addAttribute("monthlydashboarddao", new DisplayMonthlyDashboard());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/monthlydashboard/monthlydashboard.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, DisplayMonthlyDashboard beanObj, Model model) {

        System.out.println(" Sector " + beanObj.getSscid());
        System.out.println(" Month " + beanObj.getMonth());
        int i = 1;
        ArrayList dataall = new ArrayList();
        Map qpacks = new HashMap();
        qpacks.put("sscid", beanObj.getSscid());
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();

            while (itr.hasNext()) {
                QualificationPackDAO qpackdao = (QualificationPackDAO) itr.next();

                Map batchdo = new HashMap();
                batchdo.put("qpackId", qpackdao.getQpid());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new BatchesDAO(), batchdo);
                if (records2.size() > 0) {
                    Iterator itr2 = records2.iterator();
                    while (itr2.hasNext()) {
                        BatchesDAO data = (BatchesDAO) itr2.next();
                        DisplayMonthlyDashboard dispmdao = new DisplayMonthlyDashboard();
                        dispmdao.setSrno("" + i);
                        dispmdao.setTotalnostudent("" + getTotalNoStudent(data.getID()));
                        dispmdao.setNotappeared("" + getTotalNoStudent(data.getID()));
                        dispmdao.setPass("0");
                        dispmdao.setFail("0");
                        dispmdao.setJobrole(qpackdao.getQpackname());
                        dataall.add(dispmdao);
                        i++;
                    }
                }

            }
        }

        model.addAttribute("records", dataall);
        model.addAttribute("monthlydashboarddao", beanObj);
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/monthlydashboard/monthlydashboard.jsp");
        return "admin/common";

    }

    @RequestMapping(value = "/writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletRequest request, HttpServletResponse response, Model model) {

        String sscid = request.getParameter("sscid");
        String srcdate = request.getParameter("srcdate");

        int i = 1;
        ArrayList dataall = new ArrayList();
        Map qpacks = new HashMap();
        qpacks.put("sscid", sscid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), qpacks);
        if (records.size() > 0) {
            Iterator itr = records.iterator();

            while (itr.hasNext()) {
                QualificationPackDAO qpackdao = (QualificationPackDAO) itr.next();

                Map batchdo = new HashMap();
                batchdo.put("qpackId", qpackdao.getQpid());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new BatchesDAO(), batchdo);
                if (records2.size() > 0) {
                    Iterator itr2 = records2.iterator();
                    while (itr2.hasNext()) {
                        BatchesDAO data = (BatchesDAO) itr2.next();
                        DisplayMonthlyDashboard dispmdao = new DisplayMonthlyDashboard();
                        dispmdao.setSrno("" + i);
                        dispmdao.setTotalnostudent("" + getTotalNoStudent(data.getID()));
                        dispmdao.setNotappeared("" + getTotalNoStudent(data.getID()));
                        dispmdao.setPass("0");
                        dispmdao.setFail("0");
                        dispmdao.setJobrole(qpackdao.getQpackname());
                        dataall.add(dispmdao);
                        i++;
                    }
                }

            }
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("monthly_dashboard");
        XSSFRow row = spreadsheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        row.setRowStyle(style);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("SrNo#");
        cell = row.createCell(1);
        cell.setCellValue("Job Role");
        cell = row.createCell(2);
        cell.setCellValue("Passed");
        cell = row.createCell(3);
        cell.setCellValue("Failed");
        cell = row.createCell(4);
        cell.setCellValue("Not Appeared");
        cell = row.createCell(5);
        cell.setCellValue("Total No Student");

        if (dataall.size() > 0) {
            Iterator itr = dataall.iterator();
            int ik = 1;
            while (itr.hasNext()) {
                DisplayMonthlyDashboard dispmonthlydb = (DisplayMonthlyDashboard) itr.next();
                row = spreadsheet.createRow(ik);
                cell = row.createCell(0);
                cell.setCellValue(ik);
                cell = row.createCell(1);
                cell.setCellValue(dispmonthlydb.getJobrole());
                cell = row.createCell(2);
                cell.setCellValue(dispmonthlydb.getPass());
                cell = row.createCell(3);
                cell.setCellValue(dispmonthlydb.getFail());
                cell = row.createCell(4);
                cell.setCellValue(dispmonthlydb.getNotappeared());
                cell = row.createCell(5);
                cell.setCellValue(dispmonthlydb.getTotalnostudent());

                ik++;
            }
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=monthly_dashboard.xls");

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

    private int getTotalNoStudent(int batchid) {

        int totalusers = 0;
        Map batchdo = new HashMap();
        batchdo.put("batchid", batchid);
        List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new UserDAO(), batchdo);
        if (records2.size() > 0) {
            totalusers = records2.size();
        }

        return totalusers;
    }
}
