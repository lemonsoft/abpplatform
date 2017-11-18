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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
public class UserController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/initimport", method = RequestMethod.GET)
    public String initImport(HttpServletRequest request, HttpServletResponse response, Model model) {

       
        model.addAttribute("user", new UserDAO());

        model.addAttribute("sscid", request.getParameter("sscid"));
        model.addAttribute("qid", request.getParameter("qid"));
        model.addAttribute("action", "importshow.io");
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/user/import.jsp");
        return "/common";
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
                        userdao.setTraineeadress(cell10.getStringCellValue());
                        System.out.println("data if 19");
                    } else {

                        userdao.setTraineeadress("");
                        System.out.println("data else");
                    }
                    Cell cell11 = row.getCell(11); //get first cell
                    if (cell11 != null && cell11.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setDistrict(cell11.getStringCellValue());
                        System.out.println("data if 19");
                    } else {

                        userdao.setDistrict("");
                        System.out.println("data else");
                    }
                    Cell cell12 = row.getCell(12); //get first cell
                    if (cell12 != null && cell12.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setStates(cell12.getStringCellValue());
                        System.out.println("data if 12");
                    } else {

                        userdao.setStates("");
                        System.out.println("data else");
                    }
                    Cell cell13 = row.getCell(13); //get first cell
                    if (cell13 != null && cell13.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTraineephone(cell13.getStringCellValue());
                        System.out.println("data if 13");
                    } else {

                        userdao.setTraineephone("");
                        System.out.println("data else");
                    }
                    Cell cell14 = row.getCell(14); //get first cell
                    if (cell14 != null && cell14.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setEduqualification(cell14.getStringCellValue());
                        System.out.println("data if 14");
                    } else {

                        userdao.setEduqualification("");
                        System.out.println("data else");
                    }
                    Cell cell15 = row.getCell(15); //get first cell
                    if (cell15 != null && cell15.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setTechqualification(cell15.getStringCellValue());
                        System.out.println("data if 15");
                    } else {

                        userdao.setEduqualification("");
                        System.out.println("data else");
                    }
                    Cell cell16 = row.getCell(16); //get first cell
                    if (cell16 != null && cell16.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setIstraineconductocupstd(cell16.getStringCellValue());
                        System.out.println("data if 15");
                    } else {

                        userdao.setIstraineconductocupstd("");
                        System.out.println("data else");
                    }
                    Cell cell17 = row.getCell(17); //get first cell
                    if (cell17 != null && cell17.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setJobrole(cell17.getStringCellValue());
                        System.out.println("data if 17");
                    } else {

                        userdao.setJobrole("");
                        System.out.println("data else");
                    }
                    Cell cell18 = row.getCell(18); //get first cell
                    if (cell18 != null && cell18.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setIstagencynsdcpartner(cell18.getStringCellValue());
                        System.out.println("data if 17");
                    } else {

                        userdao.setIstagencynsdcpartner("");
                        System.out.println("data else");
                    }
                    Cell cell19 = row.getCell(19); //get first cell
                    if (cell19 != null && cell19.getCellType() == Cell.CELL_TYPE_STRING) {
                        userdao.setSkillingupskilling(cell19.getStringCellValue());
                        System.out.println("data if 17");
                    } else {

                        userdao.setSkillingupskilling("");
                        System.out.println("data else");
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
        return "/common";
    }

    @RequestMapping(value = "/importusers", method = RequestMethod.GET)
    public String importusers(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("user", new UserDAO());
        ArrayList record = (ArrayList) request.getSession().getAttribute("saveimportdata");
        Iterator itr = record.iterator();
        while (itr.hasNext()) {
            UserDAO userdao = (UserDAO) itr.next();
            try {
                System.out.println(userdao.getFathername());

                BatchesDAO batches = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), userdao.getBatchid());

                if (batches!=null) {
                    if (checkBatchSize(userdao.getBatchid())) {
                        this.superService.saveObject(userdao);
                        userdao.setStatus("Insert Successfully");
                    } else {
                        userdao.setStatus("Batch Size Exceeds");
                    }
                }else{
                    userdao.setStatus("Invalid Batch ID");
                }

            }  catch (ObjectNotFoundException e) {
                userdao.setStatus("Invalid Batch ID");
                //e.printStackTrace();

            }

        }
        model.addAttribute("importdata", record);
        request.getSession().removeAttribute("importdata");
        model.addAttribute("action", "importshow.io");
        model.addAttribute("mode", "add");
        model.addAttribute("displaybtn", "no");
        request.getSession().setAttribute("body", "/admin/user/import.jsp");
        return "/common";
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

}
