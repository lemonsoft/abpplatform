/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.project.theorymmq;

import com.abp.admin.language.LanguageDAO;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private static final Logger logger = Logger.getLogger(TheoryMMQController.class);
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

        model.addAttribute("theorymmq", new TheoryMMQDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/theorymmq/theorymmq.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/importMMQQuestions", method = RequestMethod.GET)
    public String importQuestions(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            String qpid = request.getParameter("qpid");
            model.addAttribute("theorymmq", new TheoryMMQDAO());

            model.addAttribute("action", "importQuestionExcel.io");
            request.getSession().setAttribute("qpid", qpid);

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        request.getSession().setAttribute("body", "/admin/theorymmq/importmmqquestions.jsp");
        return "admin/common";
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
                        boolean pcflag = false;
                        String pcidandmarks = "";
                        for (int i = 0; i < pcids.length; i++) {
                            String pcidmarkss = pcids[i];
                            String pcidandmark[] = pcidmarkss.split("#");
                            String pcid = pcidandmark[0];
                            String marks = pcidandmark[1];

                            System.out.println("Getting PCID  :" + pcid + " marks:#" + marks);
                            pcidandmarks = pcidandmarks + pcid + "(" + marks + "),";
                            PCDAO obj = getPCObjectByName(pcid);

                            if (obj != null) {

                                pcflag = true;
                            } else {
                                status = status + "Invalid PCID";
                                error = true;
                                pcflag = false;
                            }
                        }
                        questiondao.setPcidwithmarks(pcidandmarks);
                        if (pcflag) {
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
                    questiondao.setIsactive("N");

                    data.add(questiondao);
                }
            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("theorydata", data);
        model.addAttribute("action", "importQuestionExcel.io");
        model.addAttribute("theorydata", data);
        model.addAttribute("isError", error);

        request.getSession().setAttribute("body", "/admin/theorymmq/importmmqquestions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/importMultiLangExcel", method = RequestMethod.POST)
    public String importMultiLangExcel(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) {

        ArrayList data = new ArrayList();
        boolean error = false;
        try {

            String ext1 = FilenameUtils.getExtension(file.getOriginalFilename());

            if (ext1.equalsIgnoreCase("xls")) {
                Workbook workbook = new HSSFWorkbook(file.getInputStream());
                Sheet datatypeSheet = workbook.getSheetAt(0);
                for (int j = 1; j < datatypeSheet.getLastRowNum() + 1; j++) {
                    String status = "";

                    MultiLangMMQQuestionDAO questiondao = new MultiLangMMQQuestionDAO();
                    Row row = datatypeSheet.getRow(j);

                    Cell cell0 = row.getCell(0); //get first cell
                    if (cell0 != null && cell0.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell0);
                        boolean isValid = validQuestionID(new Integer(val));
                        if (isValid) {
                            questiondao.setQuestion_id(new Integer(val));
                        } else {
                            status = status + "Invalid Question ID";
                            error = true;
                        }

                    } else {
                        System.out.println("Here else part...");
                        status = status + "Invalid Question ID";
                        error = true;

                    }
                    Cell cell1 = row.getCell(1); //get first cell
                    if (cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell1);
                        boolean isValid = validateLanguageID(new Integer(val));
                        if (isValid) {
                            questiondao.setLanguage_id(new Integer(val));
                        } else {
                            status = status + "Invalid Language ID";
                            error = true;
                        }

                    } else {

                        status = status + "Invalid Language ID";
                        error = true;

                    }

                    Cell cell2 = row.getCell(2); //get first cell
                    if (cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell2);
                        questiondao.setQuestion_title(val);

                    } else {

                    }
                    Cell cell3 = row.getCell(3); //get first cell
                    if (cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_STRING) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell3);
                        questiondao.setOption1(val);

                    } else {

                    }
                    Cell cell4 = row.getCell(4); //get first cell
                    if (cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption2(cell4.getStringCellValue());

                    } else {

                    }
                    Cell cell5 = row.getCell(5); //get first cell
                    if (cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption3(cell5.getStringCellValue());

                    } else {

                    }
                    Cell cell6 = row.getCell(6); //get first cell
                    if (cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption4(cell6.getStringCellValue());

                    } else {

                    }

                    if (status.isEmpty()) {
                        questiondao.setStatus("Correct Format");

                    } else {
                        questiondao.setStatus(status);
                    }

                    data.add(questiondao);
                }
            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("importdata", data);
        model.addAttribute("action", "importMultiLangExcel.io");
        model.addAttribute("importdata", data);
        model.addAttribute("isError", error);

        request.getSession().setAttribute("body", "/admin/theorymmq/importmultilanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/insertMultiQuestions", method = RequestMethod.GET)
    public String insertMultiQuestions(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            ArrayList data = (ArrayList) request.getSession().getAttribute("importdata");
            if (data.size() > 0) {
                Iterator itr = data.iterator();
                while (itr.hasNext()) {
                    MultiLangMMQQuestionDAO questiondao = (MultiLangMMQQuestionDAO) itr.next();
                    this.superService.saveObject(questiondao);
                    questiondao.setStatus("Insert Successfully");
                }
            }
            request.getSession().removeAttribute("importdata");
            model.addAttribute("action", "importMultiLangExcel.io");
            model.addAttribute("importdata", data);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/theorymmq/importmultilanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/openqplang", method = RequestMethod.GET)
    public String openqplang(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            ArrayList data = new ArrayList();
            String questionid = request.getParameter("qid");
            Map param = new HashMap();
            param.put("question_id", new Integer(questionid));
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new MultiLangMMQQuestionDAO(), param);
            System.out.println("Get Record Size :" + records.size());
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    MultiLangMMQQuestionDAO dataObj = (MultiLangMMQQuestionDAO) itr.next();
                    if (dataObj.getQuestion_id() == new Integer(questionid)) {
                        dataObj.setActions("<a href=#  onclick=\"editQuestion('" + dataObj.getId() + "');\">Edit</a>");
                        data.add(dataObj);
                    }
                }
            }
            model.addAttribute("multilangquestion", new MultiLangMMQQuestionDAO());
            model.addAttribute("records", data);

            model.addAttribute("mode", "add");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/theorymmq/showmultilanguage.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/getMultiQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getMultiQuestion(@RequestParam("mqid") String mqid, HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject jsonObj = new JSONObject();
        try {
            MultiLangMMQQuestionDAO beanObj = (MultiLangMMQQuestionDAO) this.superService.getObjectById(new MultiLangMMQQuestionDAO(), new Integer(mqid));

            jsonObj.append("ID", beanObj.getId());
            System.out.println("Hindi converstion ...." + beanObj.getQuestion_title());

            jsonObj.append("question_title", beanObj.getQuestion_title());

            jsonObj.append("option1", beanObj.getOption1());

            jsonObj.append("option2", beanObj.getOption2());

            jsonObj.append("option3", beanObj.getOption3());

            jsonObj.append("option4", beanObj.getOption4());

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return jsonObj.toString();
    }

    @RequestMapping(value = "/updateQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String updateQuestion(@RequestParam("mqid") String mqid, @RequestParam("question") String question, @RequestParam("option1") String option1, @RequestParam("option2") String option2, @RequestParam("option3") String option3, @RequestParam("option4") String option4) throws Exception {

        MultiLangMMQQuestionDAO beanObj = (MultiLangMMQQuestionDAO) this.superService.getObjectById(new MultiLangMMQQuestionDAO(), new Integer(mqid));
        beanObj.setQuestion_title(question);
        beanObj.setOption1(option1);
        beanObj.setOption2(option2);
        beanObj.setOption3(option3);
        beanObj.setOption4(option4);

        try {
            this.superService.updateObject(beanObj);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.append("status", "update successfully");
        return jsonObj.toString();
    }

    @RequestMapping(value = "/inserttheorymmq", method = RequestMethod.GET)
    public String insertTheoryMMQ(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            String qpid = (String) request.getSession().getAttribute("qpid");
            ArrayList data = (ArrayList) request.getSession().getAttribute("theorydata");
            if (data.size() > 0) {
                Iterator itr = data.iterator();
                while (itr.hasNext()) {
                    TheoryMMQDAO questiondao = (TheoryMMQDAO) itr.next();
                    questiondao.setQpackid(new Integer(qpid));
                    this.superService.saveObject(questiondao);
                    System.out.println("ID after save ::::: " + questiondao.getId());
                    int questionid = questiondao.getId();
                    String pcidwithmark = questiondao.getPcidwithmarks();
                    String pcidandmarks[] = pcidwithmark.split(",");
                    for (int i = 0; i < pcidandmarks.length; i++) {
                        String pcidmarks = pcidandmarks[i];
                        int indexopen = pcidmarks.indexOf("(");
                        int indexlast = pcidmarks.indexOf(")");
                        String pcid = pcidmarks.substring(0, indexopen);
                        String marks = pcidmarks.substring(indexopen + 1, indexlast);

                        System.out.println("pcid :" + pcid + "marks:::" + marks);
                        TheoryPCIDWithMarks pcidwithmarks = new TheoryPCIDWithMarks();

                        pcidwithmarks.setQuestion_id(questionid);
                        pcidwithmarks.setPcid(getPCIDByName(pcid));
                        pcidwithmarks.setMarks(new Integer(marks));
                        this.superService.saveObject(pcidwithmarks);
                    }

                    questiondao.setStatus("Insert Successfully");
                }
            }
            model.addAttribute("action", "importQuestionExcel.io");
            model.addAttribute("theorymmq", new TheoryMMQDAO());
            model.addAttribute("theorydata", data);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/theorymmq/importmmqquestions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
    public String initUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = request.getParameter("recid");
        try {
            TheoryMMQDAO beanObj = (TheoryMMQDAO) this.superService.getObjectById(new TheoryMMQDAO(), new Integer(recid));
            String imgurl0 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getQuestionimgurl();
            String imgurl1 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl1();
            String imgurl2 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl2();
            String imgurl3 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl3();
            String imgurl4 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl4();

            //beanObj.setImageurl1(imgurl1);
            model.addAttribute("imgurl0", imgurl0);
            model.addAttribute("imgurl1", imgurl1);
            model.addAttribute("imgurl2", imgurl2);
            model.addAttribute("imgurl3", imgurl3);
            model.addAttribute("imgurl4", imgurl4);

            model.addAttribute("theorymmq", beanObj);
            model.addAttribute("action", "update.io");
            model.addAttribute("mode", "update");

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/theorymmq/addtheorymmq.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/importMultiLanguage", method = RequestMethod.GET)
    public String importMultiLanguage(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("questionsmultilang", new MultiLangMMQQuestionDAO());

        model.addAttribute("action", "importMultiLangExcel.io");

        request.getSession().setAttribute("body", "/admin/theorymmq/importmultilanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(@ModelAttribute("theorymmq") TheoryMMQDAO beanObj, @RequestParam MultipartFile[] files, HttpServletRequest request, HttpServletResponse response, Model model) {

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            System.out.println("file ::::: " + file.getOriginalFilename());
            String path = servletContext.getRealPath("/uploaded/questions/") + File.separator + file.getOriginalFilename();
            File destinationFile = new File(path);
            try {
                if (!destinationFile.exists()) {
                    destinationFile.createNewFile();
                }
                file.transferTo(destinationFile);
            } catch (Exception e) {
                logger.error("This is Error message", e);
            }
        }
        String questionimgurl = beanObj.getQuestionimgurl();
        if (!files[0].getOriginalFilename().isEmpty()) {
            questionimgurl = files[0].getOriginalFilename();
        }
        String imageurl1 = beanObj.getImageurl1();
        if (!files[1].getOriginalFilename().isEmpty()) {
            imageurl1 = files[1].getOriginalFilename();
        }
        String imageurl2 = beanObj.getImageurl2();
        if (!files[2].getOriginalFilename().isEmpty()) {
            imageurl2 = files[2].getOriginalFilename();
        }
        String imageurl3 = beanObj.getImageurl3();
        if (!files[3].getOriginalFilename().isEmpty()) {
            imageurl3 = files[3].getOriginalFilename();
        }
        String imageurl4 = beanObj.getImageurl4();
        if (!files[4].getOriginalFilename().isEmpty()) {
            imageurl4 = files[4].getOriginalFilename();
        }

        beanObj.setQuestionimgurl(questionimgurl);
        beanObj.setImageurl1(imageurl1);
        beanObj.setImageurl2(imageurl2);
        beanObj.setImageurl3(imageurl3);
        beanObj.setImageurl4(imageurl4);

        this.superService.updateObject(beanObj);

        return "redirect:/admin/theorymmq/init.io";
    }

    @RequestMapping(value = "/initChange", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String initChange(@RequestParam("recid") String recid, @RequestParam("active") String active, HttpServletRequest request, HttpServletResponse response, Model model) {

        TheoryMMQDAO theorydao = (TheoryMMQDAO) this.superService.getObjectById(new TheoryMMQDAO(), new Integer(recid));

        System.out.println("::::::::::::::::::::::" + active);
        if (active.equalsIgnoreCase("Y")) {
            System.out.println("::::::::::::::::::::::" + active);
            theorydao.setIsactive("N");
        } else {
            theorydao.setIsactive("Y");
        }
        JSONObject jsonObj = new JSONObject();
        try {
            System.out.println("::::::::::::::::::::::" + theorydao.getId());
            this.superService.updateObject(theorydao);
            jsonObj.append("status", "update" + active);
        } catch (Exception e) {
            logger.error("This is Error message", e);
            jsonObj.append("status", "notupdate" + active);
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String delete(@RequestParam("recid") String recid, HttpServletRequest request, HttpServletResponse response, Model model) {

        TheoryMMQDAO theorydao = (TheoryMMQDAO) this.superService.getObjectById(new TheoryMMQDAO(), new Integer(recid));

        JSONObject jsonObj = new JSONObject();
        try {

            this.superService.deleteObject(theorydao);
            jsonObj.append("status", "delete");
        } catch (Exception e) {
            jsonObj.append("status", "notdelete");
            logger.error("This is Error message", e);
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestions(@RequestParam("qp_id") String qpid) {

        System.out.println("qpid ID::" + qpid);
        String questions = getAllQuestions(new Integer(qpid));

        return questions;
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
            jsonObj.append("status", "notdelete");
            logger.error("This is Error message", e);
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

    public int getPCIDByName(String pcid) {

        int pcidObj = 0;
        Map param = new HashMap();
        param.put("pcid", pcid);
        List<SuperBean> pcdao = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (pcdao.size() > 0) {
            Iterator itr = pcdao.iterator();
            while (itr.hasNext()) {
                PCDAO obj = (PCDAO) itr.next();
                pcidObj = obj.getPcID();
            }
        }
        return pcidObj;
    }

    public String getAllQuestions(int qpid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackid", qpid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryMMQDAO(), param);
        System.out.println("Get Record Size :" + records.size());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                TheoryMMQDAO data = (TheoryMMQDAO) itr.next();
                if (data.getQpackid() == qpid) {
                    System.out.println(data.getId());
                    jsonObj.append("ID", data.getId());
                    jsonObj.append("question_title", data.getQuestion_title());
                    jsonObj.append("option1", data.getOption1());
                    jsonObj.append("option2", data.getOption2());
                    jsonObj.append("option3", data.getOption3());
                    jsonObj.append("option4", data.getOption4());
                    jsonObj.append("marks", data.getMarks());
                    jsonObj.append("pcwithmarks", getPCIDWithMarks(data.getId()));
                    jsonObj.append("correctOption", data.getCorrect_option());
                    if (data.getIsactive() != null && data.getIsactive().equalsIgnoreCase("Y")) {
                        //initChange.io?recid=" + data.getId() + "&active=" + data.getIsactive() + "\  & \"initChange.io?recid=" + data.getId() + "&active=" + data.getIsactive() + "\"
                        String str = "<a href=\"recid:" + data.getId() + ",active:" + data.getIsactive() + "\" onclick=\"setchange(event);\"><img src=\"http://localhost:8084/ABP-Ver1/assets/images/Tick-Box.png\" width=20 height=20/></a>";
                        jsonObj.append("isactive", str);
                    } else {
                        String str = "<a href=\"recid:" + data.getId() + ",active:" + data.getIsactive() + "\" onclick=\"setchange(event);\"><img src=\"http://localhost:8084/ABP-Ver1/assets/images/wrong.png\" width=20 height=20/></a>";
                        jsonObj.append("isactive", str);
                    }

                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
    }

    public String getPCIDWithMarks(int questionid) {

        String pcidwithmarks = "";
        Map param = new HashMap();
        param.put("question_id", questionid);
        List<SuperBean> theorymmqdao = this.superService.listAllObjectsByCriteria(new TheoryPCIDWithMarks(), param);
        if (theorymmqdao.size() > 0) {
            Iterator itr = theorymmqdao.iterator();
            while (itr.hasNext()) {
                TheoryPCIDWithMarks obj = (TheoryPCIDWithMarks) itr.next();
                PCDAO pcdao = (PCDAO) this.superService.getObjectById(new PCDAO(), obj.getPcid());
                pcidwithmarks = pcidwithmarks + pcdao.getPcid() + "(" + obj.getMarks() + "),";
            }
        }
        return pcidwithmarks;
    }

    public boolean validQuestionID(int questionid) {

        boolean flag = false;
        Map param = new HashMap();
        param.put("id", questionid);
        List<SuperBean> questiondao = this.superService.listAllObjectsByCriteria(new TheoryMMQDAO(), param);
        if (questiondao.size() > 0) {
            flag = true;
        }
        return flag;
    }

    public boolean validateLanguageID(int languageid) {

        boolean flag = false;
        Map param = new HashMap();
        param.put("id", languageid);
        List<SuperBean> questiondao = this.superService.listAllObjectsByCriteria(new LanguageDAO(), param);
        if (questiondao.size() > 0) {
            flag = true;
        }
        return flag;
    }

}
