/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
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
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/users")
public class BatchUserController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/initimport", method = RequestMethod.GET)
    public String initImport(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("user", new UserDAO());

        request.getSession().setAttribute("sscname", request.getParameter("sscid"));
        request.getSession().setAttribute("qpname", request.getParameter("qid"));

        model.addAttribute("action", "importshow.io");
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/user/import.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/importshow", method = RequestMethod.POST)
    public String importShow(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean flag = true;
        ArrayList data = new ArrayList();
        try {
            System.out.println("File :::::" + file.getOriginalFilename());
            String ext1 = FilenameUtils.getExtension(file.getOriginalFilename());
            System.out.println("File extension:::::" + ext1);
            if (ext1.equalsIgnoreCase("xls")) {
                Workbook workbook = new HSSFWorkbook(file.getInputStream());
                Sheet datatypeSheet = workbook.getSheetAt(0);

                for (int j = 1; j < datatypeSheet.getLastRowNum() + 1; j++) {
                    UserDAO userdao = new UserDAO();
                    Row row = datatypeSheet.getRow(j);
                    Cell cell0 = row.getCell(0); //get first cell
                    // System.out.println("data "+cell0.getNumericCellValue());
                    if (cell0 != null && cell0.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell0);
                        userdao.setBatchid(new Integer(val));
                        System.out.println("data if 1");
                    } else {
                        System.out.println("data else");
                        continue;
                    }
                    Cell cell1 = row.getCell(1); //get first cell
                    if (cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        DataFormatter formatter = new DataFormatter();
                        String val = formatter.formatCellValue(cell1);
                        userdao.setEnrollmentno(new Integer(val));
                        System.out.println("data if 2");
                    } else {
                        System.out.println("data else");
                        continue;
                    }
                    Cell cell2 = row.getCell(2); //get first cell
                    if (cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTrainingagency(cell2.getStringCellValue());
                        System.out.println("data if 3");

                    } else {
                        System.out.println("data else");
                        continue;
                    }
                    Cell cell3 = row.getCell(3); //get first cell
                    if (cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTraineename(cell3.getStringCellValue());
                        System.out.println("data if 4");
                    } else {
                        System.out.println("data else");
                        continue;
                    }
                    Cell cell4 = row.getCell(4); //get first cell
                    if (cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setGender(cell4.getStringCellValue());
                        System.out.println("data if 5");
                    } else {

                        continue;
                    }
                    Cell cell5 = row.getCell(5); //get first cell
                    if (cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setCategory(cell5.getStringCellValue());
                        System.out.println("data " + cell5.getStringCellValue());
                        System.out.println("data if 6");
                    } else {
                        System.out.println("data else");
                        continue;
                    }
                    Cell cell6 = row.getCell(6); //get first cell
                    if (cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setDateofbirth(cell6.getStringCellValue());
                        System.out.println("data if 7");
                    } else {
                        System.out.println("data else");
                        continue;
                    }
                    Cell cell7 = row.getCell(7); //get first cell
                    if (cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setFathername(cell7.getStringCellValue());
                        System.out.println("data if 8");
                    } else {
                        System.out.println("data else");
                        userdao.setFathername("");
                    }
                    Cell cell8 = row.getCell(8); //get first cell
                    if (cell8 != null && cell8.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setMothername(cell8.getStringCellValue());
                        System.out.println("data if 9");
                    } else {

                        userdao.setMothername("");
                        System.out.println("data else");
                    }
                    Cell cell9 = row.getCell(9); //get first cell
                    if (cell9 != null && cell9.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTraineeadress(cell9.getStringCellValue());
                        System.out.println("data if 9");
                    } else {

                        userdao.setTraineeadress("");
                        System.out.println("data else");
                    }
                    Cell cell10 = row.getCell(10); //get first cell
                    if (cell10 != null && cell10.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setDistrict(cell10.getStringCellValue());
                        System.out.println("data if 19");
                    } else {

                        userdao.setDistrict("");
                        System.out.println("data else");
                    }
                    Cell cell11 = row.getCell(11); //get first cell
                    if (cell11 != null && cell11.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setStates(cell11.getStringCellValue());
                        System.out.println("data if 19");
                    } else {

                        userdao.setStates("");
                        System.out.println("data else");
                    }
                    Cell cell12 = row.getCell(12); //get first cell
                    if (cell12 != null && cell12.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTraineephone(cell12.getStringCellValue());
                        System.out.println("data if 12");
                    } else {

                        userdao.setTraineephone("");
                        System.out.println("data else");
                    }
                    Cell cell13 = row.getCell(13); //get first cell
                    if (cell13 != null && cell13.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setEduqualification(cell13.getStringCellValue());
                        System.out.println("data if 13");
                    } else {

                        userdao.setEduqualification("");
                        System.out.println("data else");
                    }
                    Cell cell14 = row.getCell(14); //get first cell
                    if (cell14 != null && cell14.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTechqualification(cell14.getStringCellValue());
                        System.out.println("data if 14");
                    } else {

                        userdao.setTechqualification("");
                        System.out.println("data else");
                    }
                    Cell cell15 = row.getCell(15); //get first cell
                    if (cell15 != null && cell15.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setIstraineconductocupstd(cell15.getStringCellValue());
                        System.out.println("data if 15");
                    } else {

                        userdao.setIstraineconductocupstd("");
                        System.out.println("data else");
                    }
                    Cell cell16 = row.getCell(16); //get first cell
                    if (cell16 != null && cell16.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setJobrole(cell16.getStringCellValue());
                        System.out.println("data if 15");
                    } else {

                        userdao.setJobrole("");
                        System.out.println("data else");
                    }
                    Cell cell17 = row.getCell(17); //get first cell
                    if (cell17 != null && cell17.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setIstagencynsdcpartner(cell17.getStringCellValue());
                        System.out.println("data if 17");
                    } else {

                        userdao.setIstagencynsdcpartner("");
                        System.out.println("data else");
                    }
                    Cell cell18 = row.getCell(18); //get first cell
                    if (cell18 != null && cell18.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setSkillingupskilling(cell18.getStringCellValue());
                        System.out.println("data if 18");
                    } else {

                        userdao.setSkillingupskilling("");
                        System.out.println("data else");
                    }
                    Cell cell19 = row.getCell(19); //get first cell
                    if (cell19 != null && cell19.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setCoursestartdate(cell19.getStringCellValue());
                        System.out.println("data if19");
                    } else {

                        userdao.setCoursestartdate("");
                        System.out.println("data else");
                    }
                    Cell cell20 = row.getCell(20); //get first cell
                    if (cell20 != null && cell20.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setDateofcompletion(cell20.getStringCellValue());
                        System.out.println("data if19");
                    } else {

                        userdao.setDateofcompletion("");
                        System.out.println("data else");
                    }
                    Cell cell21 = row.getCell(21); //get first cell
                    if (cell21 != null && cell21.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTrainersname(cell21.getStringCellValue());
                    } else {
                        userdao.setTrainersname("");
                    }
                    Cell cell22 = row.getCell(22); //get first cell
                    if (cell22 != null && cell22.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTproviderspocname(cell22.getStringCellValue());
                    } else {
                        userdao.setTproviderspocname("");
                    }
                    Cell cell23 = row.getCell(23); //get first cell
                    if (cell23 != null && cell23.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setContactno(cell23.getStringCellValue());
                    } else {
                        userdao.setContactno("");
                    }
                    Cell cell24 = row.getCell(24); //get first cell
                    if (cell24 != null && cell24.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setEmailid(cell24.getStringCellValue());
                    } else {
                        userdao.setEmailid("");
                    }
                    Cell cell25 = row.getCell(25); //get first cell
                    if (cell25 != null && cell25.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTotalfeefortraining(cell25.getStringCellValue());
                    } else {
                        userdao.setTotalfeefortraining("");
                    }
                    Cell cell26 = row.getCell(26); //get first cell
                    if (cell26 != null && cell26.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setDateofassessment(cell26.getStringCellValue());
                    } else {
                        userdao.setDateofassessment("");
                    }
                    Cell cell27 = row.getCell(27); //get first cell
                    if (cell27 != null && cell27.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setAssessmentagency(cell27.getStringCellValue());
                    } else {
                        userdao.setAssessmentagency("");
                    }
                    Cell cell28 = row.getCell(28); //get first cell
                    if (cell28 != null && cell28.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTotalfeeforassessment(cell28.getStringCellValue());
                    } else {
                        userdao.setTotalfeeforassessment("");
                    }
                    Cell cell29 = row.getCell(29); //get first cell
                    if (cell29 != null && cell29.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setAssessmentfeefromwhom(cell29.getStringCellValue());
                    } else {
                        userdao.setAssessmentfeefromwhom("");
                    }
                    Cell cell30 = row.getCell(30); //get first cell
                    if (cell30 != null && cell30.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTotalfeeforassessment(cell30.getStringCellValue());
                    } else {
                        userdao.setTotalfeeforassessment("");
                    }
                    Cell cell31 = row.getCell(31); //get first cell
                    if (cell31 != null && cell31.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTotalfeeforcertification(cell31.getStringCellValue());
                    } else {
                        userdao.setTotalfeeforcertification("");
                    }
                    Cell cell32 = row.getCell(32); //get first cell
                    if (cell32 != null && cell32.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setCertificationchargefromwhom(cell32.getStringCellValue());
                    } else {
                        userdao.setCertificationchargefromwhom("");
                    }
                    Cell cell33 = row.getCell(33); //get first cell
                    if (cell33 != null && cell33.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setPriorworkexpyear(cell33.getStringCellValue());
                    } else {
                        userdao.setPriorworkexpyear("");
                    }
                    Cell cell34 = row.getCell(34); //get first cell
                    if (cell34 != null && cell34.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setEmployed(cell34.getStringCellValue());
                    } else {
                        userdao.setEmployed("");
                    }
                    Cell cell35 = row.getCell(35); //get first cell
                    if (cell35 != null && cell35.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setNameofemployer(cell35.getStringCellValue());
                    } else {
                        userdao.setNameofemployer("");
                    }
                    Cell cell36 = row.getCell(36); //get first cell
                    if (cell36 != null && cell36.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setKeycontactfrmemployer(cell36.getStringCellValue());
                    } else {
                        userdao.setKeycontactfrmemployer("");
                    }
                    Cell cell37 = row.getCell(37); //get first cell
                    if (cell37 != null && cell37.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setPhonenoofkeycontact(cell37.getStringCellValue());
                    } else {
                        userdao.setPhonenoofkeycontact("");
                    }
                    Cell cell38 = row.getCell(38); //get first cell
                    if (cell38 != null && cell38.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setPerageincreasewagepostcert(cell38.getStringCellValue());
                    } else {
                        userdao.setPerageincreasewagepostcert("");
                    }
                    Cell cell39 = row.getCell(39); //get first cell
                    if (cell39 != null && cell39.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setBenifitgainfrmcert(cell39.getStringCellValue());
                    } else {
                        userdao.setBenifitgainfrmcert("");
                    }

                    data.add(userdao);
                }

            }
//            if (ext1.equalsIgnoreCase("xlsx")) {
//                Workbook workbook = new XSSFWorkbook(file.getInputStream());
//                Sheet datatypeSheet = workbook.getSheetAt(0);
//                Iterator<Row> iterator = datatypeSheet.iterator();
//
//                while (iterator.hasNext()) {
//
//                    Row currentRow = iterator.next();
//                    Iterator<Cell> cellIterator = currentRow.iterator();
//
//                    while (cellIterator.hasNext()) {
//
//                        Cell currentCell = cellIterator.next();
//                        //getCellTypeEnum shown as deprecated for version 3.15
//                        //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
//                        if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
//                            System.out.print(currentCell.getStringCellValue() + "--");
//                        } else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//                            System.out.print(currentCell.getNumericCellValue() + "--");
//                        }
//
//                    }
//                    System.out.println();
//
//                }
//            }
            request.getSession().setAttribute("saveimportdata", data);
            model.addAttribute("importdata", data);
            model.addAttribute("displaybtn", "yes");

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("body", "/admin/user/import.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/importusers", method = RequestMethod.GET)
    public String importusers(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("user", new UserDAO());
        int idno=getMaxRecordID()+1;
        ArrayList record = (ArrayList) request.getSession().getAttribute("saveimportdata");
        Iterator itr = record.iterator();
        while (itr.hasNext()) {
            UserDAO userdao = (UserDAO) itr.next();
            try {
                System.out.println(userdao.getFathername());

                BatchesDAO batches = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), userdao.getBatchid());

                if (batches != null) {
                    if (checkBatchSize(userdao.getBatchid())) {
                        if (!checkEnrollmentNo(userdao.getEnrollmentno())) {
                            String username = userdao.getTraineename().toLowerCase();
                            username = username.replaceAll("\\s+", "");
                            userdao.setUsername(username+idno);
                            userdao.setWebcam("y");
                            this.superService.saveObject(userdao);
                            userdao.setStatus("Insert Successfully");
                        } else {
                            userdao.setStatus("Enrollment already exists.");
                        }

                    } else {
                        userdao.setStatus("Batch Size Exceeds");
                    }
                } else {
                    userdao.setStatus("Invalid Batch ID");
                }

            } catch (ObjectNotFoundException e) {
                userdao.setStatus("Invalid Batch ID");
                //e.printStackTrace();

            }
            idno++;
        }
        model.addAttribute("importdata", record);
        request.getSession().removeAttribute("importdata");
        model.addAttribute("action", "importshow.io");
        model.addAttribute("mode", "add");
        model.addAttribute("displaybtn", "no");
        request.getSession().setAttribute("body", "/admin/user/import.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/showuser", method = {RequestMethod.GET, RequestMethod.POST})
    public String showUser(HttpServletRequest request, HttpServletResponse response, Model model) {

        request.getSession().setAttribute("sscname", request.getParameter("sscid"));
        request.getSession().setAttribute("qpname", request.getParameter("qid"));
        String batchid = request.getParameter("batchid");
        Map param = new HashMap();
        param.put("batchid", new Integer(batchid));

        List<SuperBean> users = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        model.addAttribute("importdata", users);
        model.addAttribute("batchid", batchid);
        request.getSession().setAttribute("body", "/admin/user/showusers.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/exportexcel", method = {RequestMethod.GET, RequestMethod.POST})
    public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {

        String batchid = request.getParameter("batchid");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("users");

        XSSFRow row = spreadsheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        row.setRowStyle(style);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("BatchID");
        cell = row.createCell(1);
        cell.setCellValue("Enrollment No");
        cell = row.createCell(2);
        cell.setCellValue("Name of Training Agency");
        cell = row.createCell(3);
        cell.setCellValue("Name of the Trainee");
        cell = row.createCell(4);
        cell.setCellValue("Gender(M/F/T)");
        cell = row.createCell(5);
        cell.setCellValue("Category(Gen/SC/ST/OBC)");
        cell = row.createCell(6);
        cell.setCellValue("DOB");
        cell = row.createCell(7);
        cell.setCellValue("Father's name");
        cell = row.createCell(8);
        cell.setCellValue("Mother's name");
        cell = row.createCell(9);
        cell.setCellValue("Address of the trainee");
        cell = row.createCell(10);
        cell.setCellValue("District");
        cell = row.createCell(11);
        cell.setCellValue("State");
        cell = row.createCell(12);
        cell.setCellValue("Phone no of the trainee");
        cell = row.createCell(13);
        cell.setCellValue("Educational Qualification");
        cell = row.createCell(14);
        cell.setCellValue("Tech Qualification(If Any)");
        cell = row.createCell(15);
        cell.setCellValue("Is the training conducted linked to Occupational Standards");
        cell = row.createCell(16);
        cell.setCellValue("Job Role");
        cell = row.createCell(17);
        cell.setCellValue("Is the training agency an NSDC partner? (Y/N)");
        cell = row.createCell(18);
        cell.setCellValue("Skilling/Upskilling");
        cell = row.createCell(19);
        cell.setCellValue("Course Start Date");
        cell = row.createCell(20);
        cell.setCellValue("Date of course completion");
        cell = row.createCell(21);
        cell.setCellValue("Trainer's name");
        cell = row.createCell(22);
        cell.setCellValue("Training Provider SPOC name");
        cell = row.createCell(23);
        cell.setCellValue("Contact No");
        cell = row.createCell(24);
        cell.setCellValue("Email ID");
        cell = row.createCell(25);
        cell.setCellValue("Total fee charged for training");
        cell = row.createCell(26);
        cell.setCellValue("Date of assessment");
        cell = row.createCell(27);
        cell.setCellValue("Name of assessment agency");
        cell = row.createCell(28);
        cell.setCellValue("Total Fee charged for assessment");
        cell = row.createCell(29);
        cell.setCellValue("Assessment fee charged from whom?");
        cell = row.createCell(30);
        cell.setCellValue("Total Fee charged for certification");
        cell = row.createCell(31);
        cell.setCellValue("Certification fee charged from whom?");
        cell = row.createCell(32);
        cell.setCellValue("No of years of prior work experience");
        cell = row.createCell(33);
        cell.setCellValue("Employed (Y/N)");
        cell = row.createCell(34);
        cell.setCellValue("Name of the employer");
        cell = row.createCell(35);
        cell.setCellValue("Name of key contact from the employer");
        cell = row.createCell(36);
        cell.setCellValue("Phone no of the key contact from the employer");
        cell = row.createCell(37);
        cell.setCellValue("% age increase in wage post certification");
        cell = row.createCell(38);
        cell.setCellValue("Benefits gained from certification");

        Map param = new HashMap();
        param.put("batchid", new Integer(batchid));
        List<SuperBean> users = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        Iterator itr = users.iterator();
        int i = 1;
        while (itr.hasNext()) {
            UserDAO userdao = (UserDAO) itr.next();
            row = spreadsheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(userdao.getBatchid());
            cell = row.createCell(1);
            cell.setCellValue(userdao.getEnrollmentno());
            cell = row.createCell(2);
            cell.setCellValue(userdao.getTrainingagency());
            cell = row.createCell(3);
            cell.setCellValue(userdao.getTraineename());
            cell = row.createCell(4);
            cell.setCellValue(userdao.getGender());
            cell = row.createCell(5);
            cell.setCellValue(userdao.getCategory());
            cell = row.createCell(6);
            cell.setCellValue(userdao.getDateofbirth());
            cell = row.createCell(7);
            cell.setCellValue(userdao.getFathername());
            cell = row.createCell(8);
            cell.setCellValue(userdao.getMothername());
            cell = row.createCell(9);
            cell.setCellValue(userdao.getTraineeadress());
            cell = row.createCell(10);
            cell.setCellValue(userdao.getDistrict());
            cell = row.createCell(11);
            cell.setCellValue(userdao.getStates());
            cell = row.createCell(12);
            cell.setCellValue(userdao.getTraineephone());
            cell = row.createCell(13);
            cell.setCellValue(userdao.getEduqualification());
            cell = row.createCell(14);
            cell.setCellValue(userdao.getTechqualification());
            cell = row.createCell(15);
            cell.setCellValue(userdao.getIstraineconductocupstd());
            cell = row.createCell(16);
            cell.setCellValue(userdao.getJobrole());
            cell = row.createCell(17);
            cell.setCellValue(userdao.getIstagencynsdcpartner());
            cell = row.createCell(18);
            cell.setCellValue(userdao.getSkillingupskilling());
            cell = row.createCell(19);
            cell.setCellValue(userdao.getCoursestartdate());
            cell = row.createCell(20);
            cell.setCellValue(userdao.getDateofcompletion());
            cell = row.createCell(21);
            cell.setCellValue(userdao.getTrainersname());
            cell = row.createCell(22);
            cell.setCellValue(userdao.getTproviderspocname());
            cell = row.createCell(23);
            cell.setCellValue(userdao.getContactno());
            cell = row.createCell(24);
            cell.setCellValue(userdao.getEmailid());
            cell = row.createCell(25);
            cell.setCellValue(userdao.getTotalfeefortraining());
            cell = row.createCell(26);
            cell.setCellValue(userdao.getDateofassessment());
            cell = row.createCell(27);
            cell.setCellValue(userdao.getAssessmentagency());
            cell = row.createCell(28);
            cell.setCellValue(userdao.getTotalfeeforassessment());
            cell = row.createCell(29);
            cell.setCellValue(userdao.getAssessmentfeefromwhom());
            cell = row.createCell(30);
            cell.setCellValue(userdao.getTotalfeeforassessment());
            cell = row.createCell(31);
            cell.setCellValue(userdao.getCertificationchargefromwhom());
            cell = row.createCell(32);
            cell.setCellValue(userdao.getPriorworkexpyear());
            cell = row.createCell(33);
            cell.setCellValue(userdao.getEmployed());
            cell = row.createCell(34);
            cell.setCellValue(userdao.getNameofemployer());
            cell = row.createCell(35);
            cell.setCellValue(userdao.getKeycontactfrmemployer());
            cell = row.createCell(36);
            cell.setCellValue(userdao.getPhonenoofkeycontact());
            cell = row.createCell(37);
            cell.setCellValue(userdao.getPerageincreasewagepostcert());
            cell = row.createCell(38);
            cell.setCellValue(userdao.getBenifitgainfrmcert());
            i++;
        }
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=users.xls");

            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("importdata", users);
        request.getSession().setAttribute("body", "/admin/user/showusers.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initChange", method = {RequestMethod.GET, RequestMethod.POST})
    public String initChange(HttpServletRequest request, HttpServletResponse response, Model model) {

        String batchid = request.getParameter("batchid");
        String recid = request.getParameter("recid");
        UserDAO userdao = (UserDAO) this.superService.getObjectById(new UserDAO(), new Integer(recid));
        String webcam = request.getParameter("webcam");
        if (webcam.equalsIgnoreCase("Y")) {
            userdao.setWebcam("N");
        } else {
            userdao.setWebcam("Y");
        }
        this.superService.updateObject(userdao);

        Map param = new HashMap();
        param.put("batchid", new Integer(batchid));
        List<SuperBean> users = this.superService.listAllObjectsByCriteria(new UserDAO(), param);
        model.addAttribute("importdata", users);
        request.getSession().setAttribute("body", "/admin/user/showusers.jsp");
        return "admin/common";
    }

    public boolean checkBatchSize(int recid) {

        boolean flag = false;
        Map param = new HashMap();
        param.put("batchid", recid);
        List<SuperBean> users = this.superService.listAllObjectsByCriteria(new UserDAO(), param);

        BatchesDAO batches = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), recid);

        if (batches.getBatch_size() > users.size()) {
            flag = true;
        }
        return flag;
    }

    public boolean checkEnrollmentNo(int recid) {

        boolean flag = false;
        Map param = new HashMap();
        param.put("enrollmentno", recid);
        List<SuperBean> users = this.superService.listAllObjectsByCriteria(new UserDAO(), param);

        if (users.size() > 0) {
            flag = true;
        }
        return flag;
    }

    public int getMaxRecordID() {

        List<SuperBean> users = this.superService.listAllObjects(new UserDAO());
        int total = users.size();
        return total;
    }

}
