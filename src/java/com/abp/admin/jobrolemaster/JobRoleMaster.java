/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.jobrolemaster;

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
@RequestMapping("/admin/jobrole")
public class JobRoleMaster {

    private static final Logger logger = Logger.getLogger(JobRoleMaster.class);
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

        model.addAttribute("jobroledao", new JobRoleMasterDO());
        Map<Integer, String> param = new HashMap();
        List<SuperBean> objdao = this.superService.listAllObjects(new SSCDAO());
        if (objdao.size() > 0) {
            Iterator itr = objdao.iterator();
            while (itr.hasNext()) {
                SSCDAO sscdao = (SSCDAO) itr.next();
                param.put(sscdao.getSscId(), sscdao.getSscName());
            }

        }

        model.addAttribute("records", param);
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/jobrole/jobrolemaster.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletRequest request, HttpServletResponse response, Model model) {

        String sscid = request.getParameter("sscid");

        SSCDAO sectorskill = (SSCDAO) this.superService.getObjectById(new SSCDAO(), new Integer(sscid));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("job_rolemaster_report");

        XSSFRow row = spreadsheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        row.setRowStyle(style);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("Sr.#");
        cell = row.createCell(1);
        cell.setCellValue("Sector");
        cell = row.createCell(2);
        cell.setCellValue("Job Role");
        cell = row.createCell(3);
        cell.setCellValue("Job Role Number");
        cell = row.createCell(4);
        cell.setCellValue("Job Role Level");

        Map<String, String> param = new HashMap();
        param.put("sscid", sscid);
        List<SuperBean> objdao = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), param);
        if (objdao.size() > 0) {
            Iterator itr = objdao.iterator();
            int i = 1;
            while (itr.hasNext()) {
                QualificationPackDAO qpack = (QualificationPackDAO) itr.next();
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(i);
                cell = row.createCell(1);
                cell.setCellValue(sectorskill.getSscName());
                cell = row.createCell(2);
                cell.setCellValue(qpack.getQpackname());
                cell = row.createCell(3);
                cell.setCellValue(qpack.getQpackid());
                cell = row.createCell(4);
                cell.setCellValue(qpack.getQpacklevel());
                i++;
            }
        }
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=job_rolemaster_report.xls");

            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            System.out.println("Code is Here...");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        System.out.println("Code is Here...");

    }

    @RequestMapping(value = "/sectorskill", method = RequestMethod.GET)
    public @ResponseBody
    String sectorskill(@RequestParam("sscid") String sscid, HttpServletRequest request, HttpServletResponse response, Model model) {

        Map<Integer, String> param = new HashMap();
        List<SuperBean> objdao = this.superService.listAllObjects(new SSCDAO());
        if (objdao.size() > 0) {
            Iterator itr = objdao.iterator();
            while (itr.hasNext()) {
                SSCDAO sscdao = (SSCDAO) itr.next();
                param.put(sscdao.getSscId(), sscdao.getSscName());
            }

        }

        return "";
    }

    @RequestMapping(value = "/jobRoleMaster", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String jobRoleMaster(@RequestParam("sscid") String sscid, HttpServletRequest request, HttpServletResponse response, Model model) {

        JSONArray jsonarrbatch = new JSONArray();

        SSCDAO sectorskill = (SSCDAO) this.superService.getObjectById(new SSCDAO(), new Integer(sscid));

        Map<String, String> param = new HashMap();
        param.put("sscid", sscid);
        List<SuperBean> objdao = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), param);
        if (objdao.size() > 0) {
            Iterator itr = objdao.iterator();
            while (itr.hasNext()) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.append("sscname", sectorskill.getSscName());
                QualificationPackDAO qpack = (QualificationPackDAO) itr.next();
                jsonObj.append("jobrole", qpack.getQpackname());
                jsonObj.append("jobroleno", qpack.getQpackid());
                jsonObj.append("jobrolelevel", qpack.getQpacklevel());
                jsonarrbatch.put(jsonObj);
            }

        }
        return jsonarrbatch.toString();
    }

}
