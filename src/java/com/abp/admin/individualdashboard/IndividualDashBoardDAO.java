/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.individualdashboard;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
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
@RequestMapping("/admin/individualdashboard")
public class IndividualDashBoardDAO {

    private static final Logger logger = Logger.getLogger(IndividualDashBoardDAO.class);
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

        model.addAttribute("individualdao", new DisplayIndividualDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/individualassesor/individualassessor.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, DisplayIndividualDAO beanObj, Model model) {

        System.out.println(" SSCID " + beanObj.getSscid());
        ArrayList data = new ArrayList();
        int i = 1;
        Map param = new HashMap();
        param.put("ssc_id", Integer.parseInt(beanObj.getSscid()));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            AssessorDAO assesdao = (AssessorDAO) itr.next();
            DisplayIndividualDAO dispdao = new DisplayIndividualDAO();
            dispdao.setSrno("" + i);
            dispdao.setAssesorname(assesdao.getFirstname());
            String jobroles = assesdao.getJobrole();
            dispdao.setJobroles(getQPackNameById(jobroles));
            dispdao.setAffiliationdate("");
            dispdao.setState(getStateById(assesdao.getStateid()));
            dispdao.setCity(getDistrictId(assesdao.getDistrictid()));
            dispdao.setAadharno(assesdao.getAadharno());
            dispdao.setQualification(assesdao.getQualification());
            dispdao.setTotalexp(assesdao.getTotalexp() + " Years");
            dispdao.setNoofcandidateasses(""+getTotalAssessedBatch(assesdao.getAssessorid()));
            dispdao.setPass("NAN%");
            data.add(dispdao);
            i++;
        }
        model.addAttribute("records", data);
        model.addAttribute("individualdao", beanObj);
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/individualassesor/individualassessor.jsp");
        return "admin/common";

    }

    @RequestMapping(value = "/writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletRequest request, HttpServletResponse response, Model model) {

        String sscid = request.getParameter("sscid");
        ArrayList record = new ArrayList();
        int i = 1;
        Map param = new HashMap();
        param.put("ssc_id", Integer.parseInt(sscid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            AssessorDAO assesdao = (AssessorDAO) itr.next();
            DisplayIndividualDAO dispdao = new DisplayIndividualDAO();
            dispdao.setSrno(""+i);
            dispdao.setAssesorname(assesdao.getFirstname());
            String jobroles = assesdao.getJobrole();
            dispdao.setJobroles(getQPackNameById(jobroles));
            dispdao.setAffiliationdate("");
            dispdao.setState(getStateById(assesdao.getStateid()));
            dispdao.setCity(getDistrictId(assesdao.getDistrictid()));
            dispdao.setAadharno(assesdao.getAadharno());
            dispdao.setQualification(assesdao.getQualification());
            dispdao.setTotalexp(assesdao.getTotalexp() + " Years");
            dispdao.setNoofcandidateasses(""+getTotalAssessedBatch(assesdao.getAssessorid()));
            dispdao.setPass("NAN%");
            record.add(dispdao);
            i++;
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("individual_assesor_dashboard");
        XSSFRow row = spreadsheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        row.setRowStyle(style);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("SrNo#");
        cell = row.createCell(1);
        cell.setCellValue("Assesor Name");
        cell = row.createCell(2);
        cell.setCellValue("Job Roles");
        cell = row.createCell(3);
        cell.setCellValue("Affiliation date");
        cell = row.createCell(4);
        cell.setCellValue("State");
        cell = row.createCell(5);
        cell.setCellValue("City");
        cell = row.createCell(6);
        cell.setCellValue("Aadhar No");
        cell = row.createCell(7);
        cell.setCellValue("Qualification");
        cell = row.createCell(8);
        cell.setCellValue("Total Experience");
        cell = row.createCell(9);
        cell.setCellValue("Number of Candidates Assesed");
        cell = row.createCell(10);
        cell.setCellValue("Pass %");

        if (record.size() > 0) {
            Iterator itr = record.iterator();
            int ik = 1;
            while (itr.hasNext()) {
                DisplayIndividualDAO dispqbank = (DisplayIndividualDAO) itr.next();
                row = spreadsheet.createRow(ik);
                cell = row.createCell(0);
                cell.setCellValue(ik);
                cell = row.createCell(1);
                cell.setCellValue(dispqbank.getAssesorname());
                cell = row.createCell(2);
                cell.setCellValue(dispqbank.getJobroles());
                cell = row.createCell(3);
                cell.setCellValue(dispqbank.getAffiliationdate());
                cell = row.createCell(4);
                cell.setCellValue(dispqbank.getState());
                cell = row.createCell(5);
                cell.setCellValue(dispqbank.getCity());
                cell = row.createCell(6);
                cell.setCellValue(dispqbank.getAadharno());
                cell = row.createCell(7);
                cell.setCellValue(dispqbank.getQualification());
                cell = row.createCell(8);
                cell.setCellValue(dispqbank.getTotalexp());
                cell = row.createCell(9);
                cell.setCellValue(dispqbank.getNoofcandidateasses());
                cell = row.createCell(10);
                cell.setCellValue(dispqbank.getPass());

                ik++;
            }
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=individual_assesor_dashboard.xls");

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

    private String getQPackNameById(String qpackids) {

        String qpacknames = "";
        String[] qpids = qpackids.split(",");
        for (int i = 0; i < qpids.length; i++) {
            QualificationPackDAO beanQpackObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), Integer.parseInt(qpids[i]));
            if (beanQpackObj != null) {
                qpacknames = qpacknames + beanQpackObj.getQpackname();
            }
        }

        return qpacknames;
    }

    private AssessorDAO getAssessorByJobID(int sscid) {

        AssessorDAO assesdao = null;
        Map param = new HashMap();
        param.put("ssc_id", sscid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
        if (records.size() > 0) {
            assesdao = (AssessorDAO) records.get(0);
        }

        return assesdao;

    }

    private String getStateById(int stateid) {

        String statename = "";
        StateDAO beanObj = (StateDAO) this.superService.getObjectById(new StateDAO(), stateid);
        if (beanObj != null) {
            statename = beanObj.getStateName();
        }
        return statename;
    }

    private String getDistrictId(int districtid) {

        String districtname = "";
        DistrictDAO beanObj = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), districtid);
        if (beanObj != null) {
            districtname = beanObj.getDistrictName();
        }
        return districtname;
    }

    private int getTotalAssessedBatch(int assessorid) {

        int totalassessjob = 0;
        Map param = new HashMap();
        param.put("assessorId", assessorid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                BatchesDAO data = (BatchesDAO) itr.next();
                Map param2 = new HashMap();
                param2.put("batchid", data.getID());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new UserDAO(), param2);
                if (records2.size() > 0) {
                    totalassessjob=records2.size();
                }

            }
        }

        return totalassessjob;
    }

}
