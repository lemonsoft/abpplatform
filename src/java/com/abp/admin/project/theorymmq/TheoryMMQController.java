/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.project.theorymmq;

import com.abp.admin.qualificationpack.NOSDAO;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/theorymmq")
public class TheoryMMQController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("theorymmq", new TheoryMMQDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/theorymmq/theorymmq.jsp");
        return "/common";
    }

    @RequestMapping(value = "/importMMQQuestions", method = RequestMethod.GET)
    public String importQuestions(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpid=request.getParameter("qpid");
        model.addAttribute("theorymmq", new TheoryMMQDAO());

        model.addAttribute("action", "importQuestionExcel.io");
         request.getSession().setAttribute("qpid", qpid);

        request.getSession().setAttribute("body", "/admin/theorymmq/importmmqquestions.jsp");
        return "/common";
    }

    @RequestMapping(value = "/importQuestionExcel", method = RequestMethod.POST)
    public String importQuestionExcel(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) {

        ArrayList data = new ArrayList();
        boolean error = false;
        try {

            String ext1 = FilenameUtils.getExtension(file.getOriginalFilename());

            if (ext1.equalsIgnoreCase("xls")) {
                Workbook workbook = new HSSFWorkbook(file.getInputStream());
                Sheet datatypeSheet = workbook.getSheetAt(0);
                for (int j = 1; j < datatypeSheet.getLastRowNum() + 1; j++) {
                    String status = "";
                    int correctoption = 0;

                    TheoryMMQDAO questiondao = new TheoryMMQDAO();
                    Row row = datatypeSheet.getRow(j);
                    Cell cell0 = row.getCell(0); //get first cell

                    if (cell0 != null && cell0.getCellType() == Cell.CELL_TYPE_STRING) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell0);
                        questiondao.setQuestion_title(val);

                    } else {

                    }
                    Cell cell1 = row.getCell(1); //get first cell
                    if (cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_STRING) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell1);
                        questiondao.setOption1(val);

                    } else {

                    }
                    Cell cell2 = row.getCell(2); //get first cell
                    if (cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption2(cell2.getStringCellValue());

                    } else {

                    }
                    Cell cell3 = row.getCell(3); //get first cell
                    if (cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption3(cell3.getStringCellValue());

                    } else {

                    }
                    Cell cell4 = row.getCell(4); //get first cell
                    if (cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption4(cell4.getStringCellValue());

                    } else {

                    }

                    Cell cell5 = row.getCell(5); //get first cell
                    if (cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                        boolean cans1 = cell5.getBooleanCellValue();
                        questiondao.setCans1("" + cans1);
                        if (cans1) {
                            correctoption = 1;
                        }

                    } else {
                        System.out.println("Cell Type" + cell5.getCellType());
                        status = status + "Invalid CANS1";
                        error = true;
                    }
                    Cell cell6 = row.getCell(6); //get first cell
                    if (cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                        boolean cans2 = cell6.getBooleanCellValue();
                        questiondao.setCans2("" + cans2);
                        if (cans2) {
                            correctoption = 2;
                        }
                    } else {

                        status = status + "Invalid CANS2";
                        error = true;
                    }
                    Cell cell7 = row.getCell(7); //get first cell
                    if (cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                        boolean cans3 = cell7.getBooleanCellValue();
                        questiondao.setCans3("" + cans3);
                        if (cans3) {
                            correctoption = 3;
                        }

                    } else {

                        status = status + "Invalid CANS3";
                        error = true;

                    }
                    Cell cell8 = row.getCell(8); //get first cell
                    if (cell8 != null && cell8.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                        boolean cans4 = cell8.getBooleanCellValue();
                        questiondao.setCans4("" + cans4);
                        if (cans4) {
                            correctoption = 4;
                        }
                    } else {

                        status = status + "Invalid CANS4";
                        error = true;

                    }

                    Cell cell9 = row.getCell(9); //get first cell
                    if (cell9 != null && cell9.getCellType() == Cell.CELL_TYPE_STRING) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell9);
                        questiondao.setSolution(val);

                    } else {

                        status = status + "Invalid Solution";
                        error = true;

                    }
                    Cell cell10 = row.getCell(10); //get first cell
                    if (cell10 != null && cell10.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell10);
                        boolean isnooption = validateNoofOption(new Integer(val));
                        if (isnooption) {
                            questiondao.setNoofoption(new Integer(val));
                        } else {
                            status = status + "Invalid NoofOption";
                            error = true;
                        }

                    } else {
                        System.out.println("Here else part...");
                        status = status + "Invalid NoofOption";
                        error = true;

                    }
                    Cell cell11 = row.getCell(11); //get first cell
                    if (cell11 != null && cell11.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell11);
                        boolean isqtype = validateQuestionType(new Integer(val));
                        if (isqtype) {
                            questiondao.setQuestion_type(val);
                        } else {
                            status = status + "Invalid Question Type";
                            error = true;
                        }

                    } else {

                        status = status + "Invalid Question Type";
                        error = true;

                    }
                    Cell cell12 = row.getCell(12); //get first cell
                    if (cell12 != null && cell12.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell12);
                        boolean isqlevel = validateQuestionLevel(new Integer(val));
                        if (isqlevel) {
                            questiondao.setQuestion_level(val);
                        } else {
                            status = status + "Invalid Question Level";
                            error = true;
                        }

                    } else {

                        status = status + "Invalid Question Level";
                        error = true;
                    }

                    Cell cell13 = row.getCell(13); //get first cell
                    if (cell13 != null && cell13.getCellType() == Cell.CELL_TYPE_STRING) {

                        String pcidmarks = cell13.getStringCellValue();
                        String pcids[] = pcidmarks.split(",");
                        boolean pcflag=false;
                        String pcidandmarks="";
                        for (int i = 0; i < pcids.length; i++) {
                            String pcidmarkss = pcids[i];
                            String pcidandmark[] = pcidmarkss.split("#");
                            String pcid = pcidandmark[0];
                            String marks = pcidandmark[1];

                            System.out.println("Getting PCID  :" + pcid + " marks:#" + marks);
                            pcidandmarks=pcidandmarks+pcid+"("+marks+"),";
                            PCDAO obj = getPCObjectByName(pcid);
                            
                            if (obj != null) {
                                
                                pcflag=true;
                            } else {
                                status = status + "Invalid PCID";
                                error = true;
                                pcflag=false;
                            }
                        }
                        questiondao.setPcidwithmarks(pcidandmarks);
                        if(pcflag){
                            questiondao.setPcidwithmarks(pcidandmarks);
                        }

//                        PCDAO obj = getPCObjectByName(cell13.getStringCellValue());
//                        if (obj != null) {
//
//                            NOSDAO nosdao = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(obj.getNosid()));
//                            questiondao.setQpackid(new Integer(nosdao.getQpackid()));
//                        } else {
//                            status = status + "Invalid PCID";
//                            error = true;
//                        }
                    } else {

                        status = status + "Invalid PCID";
                        error = true;
                    }
                    questiondao.setCorrect_option("" + correctoption);
                    if (status.isEmpty()) {
                        questiondao.setStatus("Correct Format");

                    } else {
                        questiondao.setStatus(status);
                    }
                    questiondao.setQuestionimgurl("no-image.png");
                    questiondao.setImageurl1("no-image.png");
                    questiondao.setImageurl2("no-image.png");
                    questiondao.setImageurl3("no-image.png");
                    questiondao.setImageurl4("no-image.png");
                    data.add(questiondao);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("theorydata", data);
        model.addAttribute("action", "importQuestionExcel.io");
        model.addAttribute("theorydata", data);
        model.addAttribute("isError", error);

        request.getSession().setAttribute("body", "/admin/theorymmq/importmmqquestions.jsp");
        return "/common";
    }
    
    @RequestMapping(value = "/inserttheorymmq", method = RequestMethod.GET)
    public String insertTheoryMMQ(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpid=(String)request.getSession().getAttribute("qpid");
        ArrayList data = (ArrayList) request.getSession().getAttribute("theorydata");
        if (data.size() > 0) {
            Iterator itr = data.iterator();
            while (itr.hasNext()) {
                TheoryMMQDAO questiondao = (TheoryMMQDAO) itr.next();
                questiondao.setQpackid(new Integer(qpid));
                this.superService.saveObject(questiondao);
                questiondao.setStatus("Insert Successfully");
            }
        }
        model.addAttribute("theorymmq", new TheoryMMQDAO());
        model.addAttribute("theorydata", data);
       request.getSession().setAttribute("body", "/admin/theorymmq/importmmqquestions.jsp");
        return "/common";
    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
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

    public boolean validateNoofOption(int noofoption) {

        int[] a = {3, 4, 5};
        boolean contains = contains(a, noofoption);
        System.out.println("validateNoofOption :::" + contains);
        return contains;
    }

    public boolean validateQuestionType(int type) {

        int[] a = {1, 2};
        boolean contains = contains(a, type);
        System.out.println("validateQuestionType :::" + contains);
        return contains;
    }

    public boolean validateQuestionLevel(int level) {

        int[] a = {1, 2};
        boolean contains = contains(a, level);
        System.out.println("validateQuestionLevel :::" + contains);
        return contains;
    }

    public static boolean contains(final int[] array, final int v) {

        boolean result = false;

        for (int i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }

        return result;
    }
    public PCDAO getPCObjectByName(String pcid) {

        PCDAO pcobj = null;
        Map param = new HashMap();
        param.put("pcid", pcid);
        List<SuperBean> pcdao = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (pcdao.size() > 0) {
            Iterator itr = pcdao.iterator();
            while (itr.hasNext()) {
                PCDAO obj = (PCDAO) itr.next();
                if (obj.getPcid().equals(pcid)) {
                    pcobj = obj;
                }
            }
        }
        return pcobj;
    }

}
