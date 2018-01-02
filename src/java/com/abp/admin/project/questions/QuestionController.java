/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.project.questions;

import com.abp.admin.language.LanguageDAO;
import com.abp.admin.qualificationpack.NOSDAO;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/admin/questions")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
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

        model.addAttribute("questions", new QuestionDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/questions/questions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
    public String addQuestion(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpackid = request.getParameter("qpackid");
        String nosid = request.getParameter("nosid");
        String pcid = request.getParameter("pcid");

        try {
            QuestionDAO questObj = new QuestionDAO();
            questObj.setQpackid(new Integer(qpackid));
            questObj.setNosid(new Integer(nosid));
            questObj.setPcid(new Integer(pcid));
            model.addAttribute("questions", questObj);

            model.addAttribute("mode", "add");
            model.addAttribute("action", "add.io");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/questions/addQuestions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(@ModelAttribute("questions") QuestionDAO beanObj, @RequestParam MultipartFile[] files, HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
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
                    e.printStackTrace();
                }
            }
            String questionimgurl = "no-image.png";
            if (!files[0].getOriginalFilename().isEmpty()) {
                questionimgurl = files[0].getOriginalFilename();
            }
            String imageurl1 = "no-image.png";
            if (!files[1].getOriginalFilename().isEmpty()) {
                imageurl1 = files[1].getOriginalFilename();
            }
            String imageurl2 = "no-image.png";
            if (!files[2].getOriginalFilename().isEmpty()) {
                imageurl2 = files[2].getOriginalFilename();
            }
            String imageurl3 = "no-image.png";
            if (!files[3].getOriginalFilename().isEmpty()) {
                imageurl3 = files[3].getOriginalFilename();
            }
            String imageurl4 = "no-image.png";
            if (!files[4].getOriginalFilename().isEmpty()) {
                imageurl4 = files[4].getOriginalFilename();
            }
            String imageurl5 = "no-image.png";
            if (!files[5].getOriginalFilename().isEmpty()) {
                imageurl5 = files[5].getOriginalFilename();
            }

            beanObj.setQuestionimgurl(questionimgurl);
            beanObj.setImageurl1(imageurl1);
            beanObj.setImageurl2(imageurl2);
            beanObj.setImageurl3(imageurl3);
            beanObj.setImageurl4(imageurl4);
            beanObj.setImageurl5(imageurl5);
            beanObj.setCans1("");
            beanObj.setCans2("");
            beanObj.setCans3("");
            beanObj.setCans4("");
            beanObj.setIsapproved("Y");

            this.superService.saveObject(beanObj);
            model.addAttribute("questions", new QuestionDAO());

            model.addAttribute("mode", "add");
            model.addAttribute("action", "add.io");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/questions/addQuestions.jsp");
        return "redirect:/admin/questions/init.io";
    }

    @RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
    public String initUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = request.getParameter("recid");
        try {
            QuestionDAO beanObj = (QuestionDAO) this.superService.getObjectById(new QuestionDAO(), new Integer(recid));
            String imgurl0 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getQuestionimgurl();
            String imgurl1 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl1();
            String imgurl2 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl2();
            String imgurl3 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl3();
            String imgurl4 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl4();
            String imgurl5 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/uploaded/questions/" + beanObj.getImageurl5();
            //beanObj.setImageurl1(imgurl1);
            model.addAttribute("imgurl0", imgurl0);
            model.addAttribute("imgurl1", imgurl1);
            model.addAttribute("imgurl2", imgurl2);
            model.addAttribute("imgurl3", imgurl3);
            model.addAttribute("imgurl4", imgurl4);
            model.addAttribute("imgurl5", imgurl5);
            model.addAttribute("questions", beanObj);
            model.addAttribute("action", "update.io");
            model.addAttribute("mode", "update");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/questions/addQuestions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(@ModelAttribute("questions") QuestionDAO beanObj, @RequestParam MultipartFile[] files, HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
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
                    e.printStackTrace();
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
            String imageurl5 = beanObj.getImageurl5();
            if (!files[5].getOriginalFilename().isEmpty()) {
                imageurl5 = files[5].getOriginalFilename();
            }

            beanObj.setQuestionimgurl(questionimgurl);
            beanObj.setImageurl1(imageurl1);
            beanObj.setImageurl2(imageurl2);
            beanObj.setImageurl3(imageurl3);
            beanObj.setImageurl4(imageurl4);
            beanObj.setImageurl5(imageurl5);
            beanObj.setCans1("");
            beanObj.setCans2("");
            beanObj.setCans3("");
            beanObj.setCans4("");
            beanObj.setIsapproved("Y");
            this.superService.updateObject(beanObj);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return "redirect:/admin/questions/init.io";
    }

    @RequestMapping(value = "/importQuestions", method = RequestMethod.GET)
    public String importQuestions(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("questions", new QuestionDAO());

        model.addAttribute("action", "importQuestionExcel.io");

        request.getSession().setAttribute("body", "/admin/questions/importQuestions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/importMultiLanguage", method = RequestMethod.GET)
    public String importMultiLanguage(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("questionsmultilang", new MultiLangQuestionDAO());

        model.addAttribute("action", "importMultiLangExcel.io");

        request.getSession().setAttribute("body", "/admin/questions/importmultilanguage.jsp");
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

                    QuestionDAO questiondao = new QuestionDAO();
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
                    if (cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption5(cell5.getStringCellValue());

                    } else {

                    }
                    Cell cell6 = row.getCell(6); //get first cell
                    if (cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cans1 = cell6.getStringCellValue();
                        questiondao.setCans1("" + cans1);
                        if (cans1.equalsIgnoreCase("true")) {
                            correctoption = 1;
                        }

                    } else {
                        status = status + "Invalid CANS1";
                        error = true;
                    }
                    Cell cell7 = row.getCell(7); //get first cell
                    if (cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cans2 = cell7.getStringCellValue();
                        questiondao.setCans2("" + cans2);
                        if (cans2.equalsIgnoreCase("true")) {
                            correctoption = 2;
                        }
                    } else {

                        status = status + "Invalid CANS2";
                        error = true;
                    }
                    Cell cell8 = row.getCell(8); //get first cell
                    if (cell8 != null && cell8.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cans3 = cell8.getStringCellValue();
                        questiondao.setCans3("" + cans3);
                        if (cans3.equalsIgnoreCase("true")) {
                            correctoption = 3;
                        }

                    } else {

                        status = status + "Invalid CANS3";
                        error = true;

                    }
                    Cell cell9 = row.getCell(9); //get first cell
                    if (cell9 != null && cell9.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cans4 = cell9.getStringCellValue();
                        questiondao.setCans4("" + cans4);
                        if (cans4.equalsIgnoreCase("true")) {
                            correctoption = 4;
                        }

                    } else {

                        status = status + "Invalid CANS4";
                        error = true;

                    }
                    Cell cell10 = row.getCell(10); //get first cell
                    if (cell10 != null && cell10.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cans5 = cell10.getStringCellValue();
                        questiondao.setCans5("" + cans5);
                        if (cans5.equalsIgnoreCase("true")) {
                            correctoption = 5;
                        }
                    } else {

                        status = status + "Invalid CANS5";
                        error = true;

                    }
                    Cell cell11 = row.getCell(11); //get first cell
                    if (cell11 != null && cell11.getCellType() == Cell.CELL_TYPE_STRING) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell11);
                        questiondao.setSolution(val);

                    } else {

                        status = status + "Invalid Solution";
                        error = true;

                    }
                    Cell cell12 = row.getCell(12); //get first cell
                    if (cell12 != null && cell12.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell12);
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
                    Cell cell13 = row.getCell(13); //get first cell
                    if (cell13 != null && cell13.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell13);
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
                    Cell cell14 = row.getCell(14); //get first cell
                    if (cell14 != null && cell14.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell14);
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
                    Cell cell15 = row.getCell(15); //get first cell
                    if (cell15 != null && cell15.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell15);
                        questiondao.setMarks(new Integer(val));

                    } else {

                        status = status + "Invalid Marks";
                        error = true;
                    }

                    Cell cell16 = row.getCell(16); //get first cell
                    if (cell16 != null && cell16.getCellType() == Cell.CELL_TYPE_STRING) {

                        questiondao.setPc_id(cell16.getStringCellValue());

                        PCDAO obj = getPCObjectByName(cell16.getStringCellValue());
                        if (obj != null) {
                            System.out.println("Getting PCDAO ........" + obj);
                            int maxmarks = new Integer(obj.getOverallcutoffmarks());
                            int marks = questiondao.getMarks();
                            if (maxmarks > marks) {
                                questiondao.setMarks(marks);
                            } else {
                                questiondao.setMarks(marks);
                                status = status + "Invalid Marks";
                                error = true;

                            }
                            questiondao.setPcid(obj.getPcID());
                            questiondao.setNosid(new Integer(obj.getNosid()));
                            NOSDAO nosdao = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(obj.getNosid()));
                            questiondao.setQpackid(new Integer(nosdao.getQpackid()));
                        } else {
                            status = status + "Invalid PCID";
                            error = true;
                        }

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
                    questiondao.setImageurl5("no-image.png");
                    questiondao.setIsapproved("Y");
                    data.add(questiondao);
                }
            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("importdata", data);
        model.addAttribute("action", "importQuestionExcel.io");
        model.addAttribute("importdata", data);
        model.addAttribute("isError", error);

        request.getSession().setAttribute("body", "/admin/questions/importQuestions.jsp");
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

                    MultiLangQuestionDAO questiondao = new MultiLangQuestionDAO();
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
                    Cell cell7 = row.getCell(7); //get first cell
                    if (cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_STRING) {
                        questiondao.setOption5(cell7.getStringCellValue());

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

        request.getSession().setAttribute("body", "/admin/questions/importmultilanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/insertQuestions", method = RequestMethod.GET)
    public String insertQuestions(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            ArrayList data = (ArrayList) request.getSession().getAttribute("importdata");
            if (data.size() > 0) {
                Iterator itr = data.iterator();
                while (itr.hasNext()) {
                    QuestionDAO questiondao = (QuestionDAO) itr.next();
                    this.superService.saveObject(questiondao);
                }
            }
            model.addAttribute("questions", new QuestionDAO());

            model.addAttribute("ssc", getSectorSkillCouncil());
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/questions/questions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/insertMultiQuestions", method = RequestMethod.GET)
    public String insertMultiQuestions(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            ArrayList data = (ArrayList) request.getSession().getAttribute("importdata");
            if (data.size() > 0) {
                Iterator itr = data.iterator();
                while (itr.hasNext()) {
                    MultiLangQuestionDAO questiondao = (MultiLangQuestionDAO) itr.next();
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
        request.getSession().setAttribute("body", "/admin/questions/importmultilanguage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/openqplang", method = RequestMethod.GET)
    public String openqplang(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            ArrayList data = new ArrayList();
            String questionid = request.getParameter("qid");
            Map param = new HashMap();
            param.put("question_id", new Integer(questionid));
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new MultiLangQuestionDAO(), param);
            System.out.println("Get Record Size :" + records.size());
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    MultiLangQuestionDAO dataObj = (MultiLangQuestionDAO) itr.next();
                    if (dataObj.getQuestion_id() == new Integer(questionid)) {
                        dataObj.setActions("<a href=#  onclick=\"editQuestion('" + dataObj.getId() + "');\">Edit</a>");
                        data.add(dataObj);
                    }
                }
            }
            model.addAttribute("multilangquestion", new MultiLangQuestionDAO());
            model.addAttribute("records", data);

            model.addAttribute("mode", "add");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/questions/showmultilanguage.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = request.getParameter("recid");
        try {
            this.superService.deleteObjectById(new QuestionDAO(), new Integer(recid));

            model.addAttribute("questions", new QuestionDAO());

            model.addAttribute("ssc", getSectorSkillCouncil());
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/questions/questions.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/getMultiQuestion", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getMultiQuestion(@RequestParam("mqid") String mqid, HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject jsonObj = new JSONObject();
        try {
            MultiLangQuestionDAO beanObj = (MultiLangQuestionDAO) this.superService.getObjectById(new MultiLangQuestionDAO(), new Integer(mqid));

            jsonObj.append("ID", beanObj.getId());
            System.out.println("Hindi converstion ...." + beanObj.getQuestion_title());

            jsonObj.append("question_title", beanObj.getQuestion_title());

            jsonObj.append("option1", beanObj.getOption1());

            jsonObj.append("option2", beanObj.getOption2());

            jsonObj.append("option3", beanObj.getOption3());

            jsonObj.append("option4", beanObj.getOption4());

            jsonObj.append("option5", beanObj.getOption5());

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return jsonObj.toString();
    }

    @RequestMapping(value = "/updateQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String updateQuestion(@RequestParam("mqid") String mqid, @RequestParam("question") String question, @RequestParam("option1") String option1, @RequestParam("option2") String option2, @RequestParam("option3") String option3, @RequestParam("option4") String option4, @RequestParam("option5") String option5) throws Exception {

        MultiLangQuestionDAO beanObj = (MultiLangQuestionDAO) this.superService.getObjectById(new MultiLangQuestionDAO(), new Integer(mqid));
        beanObj.setQuestion_title(question);
        beanObj.setOption1(option1);
        beanObj.setOption2(option2);
        beanObj.setOption3(option3);
        beanObj.setOption4(option4);
        beanObj.setOption5(option5);
        try {
            this.superService.updateObject(beanObj);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.append("status", "update successfully");
        return jsonObj.toString();
    }

    @RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

        String qpid = request.getParameter("qpid");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("questions");

        XSSFRow row = spreadsheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        row.setRowStyle(style);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("Question ID");
        cell = row.createCell(1);
        cell.setCellValue("Question Title");
        cell = row.createCell(2);
        cell.setCellValue("Option 1");
        cell = row.createCell(3);
        cell.setCellValue("Option 2");
        cell = row.createCell(4);
        cell.setCellValue("Option 3");
        cell = row.createCell(5);
        cell.setCellValue("Option 4");
        cell = row.createCell(6);
        cell.setCellValue("Option 5");

        Map param = new HashMap();
        param.put("qpackid", new Integer(qpid));
        List<SuperBean> questions = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
        Iterator itr = questions.iterator();
        int i = 1;
        while (itr.hasNext()) {
            QuestionDAO questiondao = (QuestionDAO) itr.next();
            row = spreadsheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(questiondao.getId());
            cell = row.createCell(1);
            cell.setCellValue(questiondao.getQuestion_title());
            cell = row.createCell(2);
            cell.setCellValue(questiondao.getOption1());
            cell = row.createCell(3);
            cell.setCellValue(questiondao.getOption2());
            cell = row.createCell(4);
            cell.setCellValue(questiondao.getOption3());
            cell = row.createCell(5);
            cell.setCellValue(questiondao.getOption4());
            cell = row.createCell(6);
            cell.setCellValue(questiondao.getOption5());
            System.out.println("Code is Here..." + questiondao.getOption5());
            i++;
        }
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=questions.xls");

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

    @RequestMapping(value = "/getnos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getnos(@RequestParam("qpid") String qpid) {

        System.out.println("QP ID::" + qpid);
        String nos = getNOSByID(qpid);

        return nos;
    }

    @RequestMapping(value = "/getpc", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getpc(@RequestParam("nosid") String nosid) {

        System.out.println("NOS ID::" + nosid);
        String pcdata = getPCByID(nosid);

        return pcdata;
    }

    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestions(@RequestParam("qp_id") String qpid) {

        System.out.println("qpid ID::" + qpid);
        String questions = getAllQuestions(new Integer(qpid));

        return questions;
    }

    @RequestMapping(value = "/getQuestionsbynos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestionsbynos(@RequestParam("qp_id") String qpid, @RequestParam("nosid") String nosid) {

        String questions = getAllQuestionsByNos(new Integer(qpid), new Integer(nosid));

        return questions;
    }

    @RequestMapping(value = "/getQuestionsbypc", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQuestionsbypc(@RequestParam("qp_id") String qpid, @RequestParam("nosid") String nosid, @RequestParam("pcid") String pcid) {

        String questions = getAllQuestionsByPC(new Integer(qpid), new Integer(nosid), new Integer(pcid));
        System.out.println("Get question by pc from Question Bank");
        return questions;
    }

    public String getAllQuestions(int qpid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackid", qpid);
        param.put("isapproved", "Y");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
        System.out.println("Get Record Size :" + records.size());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionDAO data = (QuestionDAO) itr.next();
                if (data.getQpackid() == qpid) {
                    System.out.println(data.getId());
                    jsonObj.append("ID", data.getId());
                    jsonObj.append("question_title", data.getQuestion_title());
                    jsonObj.append("questionimgurl", data.getQuestionimgurl());
                    System.out.println("qimg::::" + data.getQuestionimgurl());
                    jsonObj.append("option1", data.getOption1());
                    jsonObj.append("imageurl1", data.getImageurl1());
                    jsonObj.append("option2", data.getOption2());
                    jsonObj.append("imageurl2", data.getImageurl2());
                    jsonObj.append("option3", data.getOption3());
                    jsonObj.append("imageurl3", data.getImageurl3());
                    jsonObj.append("option4", data.getOption4());
                    jsonObj.append("imageurl4", data.getImageurl4());
                    jsonObj.append("option5", data.getOption5());
                    jsonObj.append("imageurl5", data.getImageurl5());
                    jsonObj.append("marks", data.getMarks());
                    jsonObj.append("correctOption", data.getCorrect_option());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
    }

    public String getAllQuestionsByNos(int qpid, int nosid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackid", qpid);
        param.put("nosid", nosid);
        param.put("isapproved", "Y");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
        System.out.println("Get Record Size :" + records.size());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionDAO data = (QuestionDAO) itr.next();
                if (data.getQpackid() == qpid && data.getNosid() == nosid) {
                    System.out.println(data.getId());
                    jsonObj.append("ID", data.getId());
                    jsonObj.append("question_title", data.getQuestion_title());
                    jsonObj.append("questionimgurl", data.getQuestionimgurl());
                    jsonObj.append("option1", data.getOption1());
                    jsonObj.append("imageurl1", data.getImageurl1());
                    jsonObj.append("option2", data.getOption2());
                    jsonObj.append("imageurl2", data.getImageurl2());
                    jsonObj.append("option3", data.getOption3());
                    jsonObj.append("imageurl3", data.getImageurl3());
                    jsonObj.append("option4", data.getOption4());
                    jsonObj.append("imageurl4", data.getImageurl4());
                    jsonObj.append("option5", data.getOption5());
                    jsonObj.append("imageurl5", data.getImageurl5());
                    jsonObj.append("marks", data.getMarks());
                    jsonObj.append("correctOption", data.getCorrect_option());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
    }

    public String getAllQuestionsByPC(int qpid, int nosid, int pcid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackid", qpid);
        param.put("nosid", nosid);
        param.put("pcid", pcid);
        param.put("isapproved", "Y");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
        System.out.println("Get Record Size :" + records.size());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionDAO data = (QuestionDAO) itr.next();
                if (data.getQpackid() == qpid && data.getNosid() == nosid && data.getPcid() == pcid) {
                    System.out.println(data.getId());
                    jsonObj.append("ID", data.getId());
                    jsonObj.append("question_title", data.getQuestion_title());
                    jsonObj.append("questionimgurl", data.getQuestionimgurl());
                    jsonObj.append("option1", data.getOption1());
                    jsonObj.append("imageurl1", data.getImageurl1());
                    jsonObj.append("option2", data.getOption2());
                    jsonObj.append("imageurl2", data.getImageurl2());
                    jsonObj.append("option3", data.getOption3());
                    jsonObj.append("imageurl3", data.getImageurl3());
                    jsonObj.append("option4", data.getOption4());
                    jsonObj.append("imageurl4", data.getImageurl4());
                    jsonObj.append("option5", data.getOption5());
                    jsonObj.append("imageurl5", data.getImageurl5());
                    jsonObj.append("marks", data.getMarks());
                    jsonObj.append("correctOption", data.getCorrect_option());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
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

    public String getNOSByID(String qpid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackid", qpid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new NOSDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                NOSDAO data = (NOSDAO) itr.next();
                if (data.getQpackid().equals(qpid)) {
                    jsonObj.append("ID", data.getNosID());
                    jsonObj.append("NAME", data.getNosname());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
    }

    public String getPCByID(String nosid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("nosid", nosid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCDAO data = (PCDAO) itr.next();
                if (data.getNosid().equals(nosid)) {
                    jsonObj.append("ID", data.getPcID());
                    jsonObj.append("NAME", data.getPcname());
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

    public boolean validQuestionID(int questionid) {

        boolean flag = false;
        Map param = new HashMap();
        param.put("id", questionid);
        List<SuperBean> questiondao = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
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
