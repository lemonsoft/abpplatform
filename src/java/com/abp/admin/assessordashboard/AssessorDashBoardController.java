/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.assessordashboard;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
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
import static org.springframework.util.Assert.state;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/assesordashboard")
public class AssessorDashBoardController {

    private static final Logger logger = Logger.getLogger(AssessorDashBoardController.class);
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

        model.addAttribute("assesordao", new AssessorDashBoardDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");

        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/consolidateassessor/consolidateassessor.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, Model model, AssessorDashBoardDAO assessordao) {

        System.out.println(" State " + assessordao.getState());
        System.out.println(" Sector Skill " + assessordao.getSscid());

        ArrayList datarecords = new ArrayList();
        int i = 1;
        Map param = new HashMap();
        param.put("sscid", assessordao.getSscid());
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QualificationPackDAO qpackdata = (QualificationPackDAO) itr.next();
                AssessorDashBoardDAO assesdashdao = new AssessorDashBoardDAO();
                assesdashdao.setSrno("" + i);
                assesdashdao.setJobrole(qpackdata.getQpackname());
                assesdashdao.setTotalassessor("0");
                assesdashdao.setState(getStateIdByName(Integer.parseInt(assessordao.getState())));
                datarecords.add(assesdashdao);
                i++;
            }
        }

        model.addAttribute("assesordao", new AssessorDashBoardDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("mode", "add");
        model.addAttribute("records", datarecords);
        request.getSession().setAttribute("body", "/admin/consolidateassessor/consolidateassessor.jsp");
        return "admin/common";
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

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String statename = getStateBySSCID(Integer.parseInt(sscid));

        return statename;
    }

    private String getStateBySSCID(int sscid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        HashMap<String, String> states = new HashMap();
        System.out.println("   Sector skill counsil ID :::: " + sscid);
        try {

            Map param = new HashMap();
            param.put("sscid", "" + sscid);
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), param);
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    QualificationPackDAO qpackdata = (QualificationPackDAO) itr.next();

                    Map param2 = new HashMap();
                    param2.put("qpackId", qpackdata.getQpid());
                    List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param2);
                    if (records2.size() > 0) {
                        Iterator itr2 = records2.iterator();
                        while (itr2.hasNext()) {
                            BatchesDAO data = (BatchesDAO) itr2.next();
                            states.put("" + data.getState_id(), getStateIdByName(data.getState_id()));
                        }
                    }

                }
            }
            for (Map.Entry<String, String> entry : states.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                jsonObj.append("ID", entry.getKey());
                jsonObj.append("NAME", entry.getValue());
                jsonarr.put(jsonObj);
                jsonObj = new JSONObject();
            }

        } catch (Exception e) {

            e.printStackTrace();
            logger.error("This is Error message", e);
        }

        return jsonarr.toString();
    }

    @RequestMapping(value = "/writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletRequest request, HttpServletResponse response, Model model) {

        String sscid = request.getParameter("sscid");
        String state = request.getParameter("state");

        ArrayList record = new ArrayList();
        int i = 1;
        Map param = new HashMap();
        param.put("sscid", sscid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QualificationPackDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QualificationPackDAO qpackdata = (QualificationPackDAO) itr.next();
                AssessorDashBoardDAO assesdashdao = new AssessorDashBoardDAO();
                assesdashdao.setSrno("" + i);
                assesdashdao.setJobrole(qpackdata.getQpackname());
                assesdashdao.setTotalassessor("0");
                assesdashdao.setState(getStateIdByName(Integer.parseInt(state)));
                record.add(assesdashdao);
                i++;
            }
        }

        System.out.println(sscid + " : : Get Parameter Value " + state);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("consolidate_assesor_dashboard");
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
        cell.setCellValue("Total Assessor");
        cell = row.createCell(3);
        cell.setCellValue("State");

        System.out.println("Record : "+record.size());
        if (record.size() > 0) {
            Iterator itr = record.iterator();
            int ik = 1;
            while (itr.hasNext()) {
                AssessorDashBoardDAO dispqbank = (AssessorDashBoardDAO) itr.next();
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(i);
                cell = row.createCell(1);
                cell.setCellValue(dispqbank.getJobrole());
                cell = row.createCell(2);
                cell.setCellValue(dispqbank.getTotalassessor());
                cell = row.createCell(3);
                cell.setCellValue(dispqbank.getState());

                ik++;
            }
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=onsolidate_assesor_dashboard.xls");

            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            System.out.println("Code is Here...");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        System.out.println("Code is Here...");

    }

    private String getStateIdByName(int stateid) {

        String statename = "";
        StateDAO statedao = (StateDAO) this.superService.getObjectById(new StateDAO(), stateid);
        if (statedao != null) {
            statename = statedao.getStateName();
        }
        return statename;
    }

}
