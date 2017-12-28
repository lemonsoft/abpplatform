/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.assessor;

import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.statedistrict.DistrictDAO;
import com.abp.statedistrict.StateDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
@RequestMapping("/admin/assessor")
public class AssessorController {

    private static final Logger logger = Logger.getLogger(AssessorController.class);
    private SuperService superService;
    @Autowired
    private ServletContext servletContext;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {

            model.addAttribute("assessor", new AssessorDAO());
            model.addAttribute("action", "add.io");
            model.addAttribute("allstates", getAllStatesValues());
            model.addAttribute("jobroles", getSkillsList());
            model.addAttribute("ssc", getSectorSkillCouncil());
            model.addAttribute("alldistrict", getAllDistrictValues());
            model.addAttribute("mode", "add");

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        request.getSession().setAttribute("body", "/admin/assessor/addassessor.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String add(@ModelAttribute("assessor") AssessorDAO beanObj, @RequestParam MultipartFile[] files, HttpServletRequest request, HttpServletResponse response, Model model) {

        try {

            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];

                System.out.println("file ::::: " + file.getOriginalFilename());
                String path = servletContext.getRealPath("/uploaded/assessor/") + File.separator + file.getOriginalFilename();
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
            String photname = files[0].getOriginalFilename();
            String aadharname = files[1].getOriginalFilename();
            String resumename = files[2].getOriginalFilename();
            List<String> options = beanObj.getOptions();
            Iterator itr = options.iterator();
            String jobroles = "";
            while (itr.hasNext()) {
                String data = (String) itr.next();
                jobroles = jobroles + "," + data;
            }
            jobroles = jobroles.substring(1);
            beanObj.setPhotoname(photname);
            beanObj.setAadharimage(aadharname);
            beanObj.setResumename(resumename);
            beanObj.setJobrole(jobroles);
            this.superService.saveObject(beanObj);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return "redirect:/admin/assessor/init.io";
    }

    @RequestMapping(value = "/initSearch", method = {RequestMethod.GET, RequestMethod.POST})
    public String initSearch(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {

            model.addAttribute("assessor", new AssessorDAO());
            model.addAttribute("ssc", getSectorSkillCouncil());
            List<SuperBean> records = this.superService.listAllObjects(new AssessorDAO());
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                AssessorDAO assessor = (AssessorDAO) itr.next();
                assessor.setState(getStateNameByID("" + assessor.getStateid()));
                assessor.setDistrict(getDistrictNameByID("" + assessor.getDistrictid()));
                String[] jobroleid = assessor.getJobrole().split(",");
                String jobrole = "";
                for (int i = 0; i < jobroleid.length; i++) {
                    jobrole = jobrole + "," + getJobRoleNameByID(jobroleid[i]);
                }
                jobrole = jobrole.substring(1);
                assessor.setJobrole(jobrole);
            }
            model.addAttribute("records", records);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/assessor/searchassessor.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(AssessorDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            model.addAttribute("assessor", beanObj);
            model.addAttribute("ssc", getSectorSkillCouncil());
            Map param = new HashMap();
            param.put("ssc_id", new Integer(beanObj.getSsc()));
            System.out.println("SSC ID :::::::::::::::" + beanObj.getSsc());
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
            System.out.println("records found ::" + records.size());
            if (records.size() > 0) {
                Iterator itr = records.iterator();
                while (itr.hasNext()) {
                    AssessorDAO assessor = (AssessorDAO) itr.next();
                    assessor.setState(getStateNameByID("" + assessor.getStateid()));
                    assessor.setDistrict(getDistrictNameByID("" + assessor.getDistrictid()));
                }
            }
            model.addAttribute("records", records);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        request.getSession().setAttribute("body", "/admin/assessor/searchassessor.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
    public String initUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            String recid = (String) request.getParameter("recid");
            AssessorDAO record = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), new Integer(recid));
            String[] jobroles = record.getJobrole().split(",");
            System.out.println("job role " + jobroles[0]);
            List<String> options = new ArrayList();
            for (int i = 0; i < jobroles.length; i++) {
                options.add(jobroles[i]);
            }
            System.out.println("File name send to page " + record.getPhotoname());

            request.getSession().setAttribute("photoname", record.getPhotoname());
            request.getSession().setAttribute("aadharimage", record.getAadharimage());
            request.getSession().setAttribute("resumename", record.getResumename());
            record.setOptions(options);
            model.addAttribute("assessor", record);
            model.addAttribute("allstates", getAllStatesValues());
            model.addAttribute("jobroles", getSkillsList());
            model.addAttribute("ssc", getSectorSkillCouncil());
            model.addAttribute("district", getDistrictById(record.getDistrictid()));
            model.addAttribute("action", "update.io");
            model.addAttribute("mode", "update");
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        request.getSession().setAttribute("body", "/admin/assessor/addassessor.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("assessor") AssessorDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String forward = "redirect:/admin/assessor/initSearch.io";
        try {
            List<String> options = beanObj.getOptions();
            Iterator itr = options.iterator();
            String jobroles = "";
            while (itr.hasNext()) {
                String data = (String) itr.next();
                jobroles = jobroles + "," + data;
            }
            jobroles = jobroles.substring(1);
            beanObj.setJobrole(jobroles);
            beanObj.setPhotoname(request.getSession().getAttribute("photoname").toString());
            beanObj.setAadharimage(request.getSession().getAttribute("aadharimage").toString());
            beanObj.setResumename(request.getSession().getAttribute("resumename").toString());

            this.superService.updateObject(beanObj);

        } catch (Exception e) {
            logger.error("This is Error message", e);
            model.addAttribute("assessor", beanObj);
            model.addAttribute("allstates", getAllStatesValues());
            model.addAttribute("jobroles", getSkillsList());
            model.addAttribute("ssc", getSectorSkillCouncil());
            model.addAttribute("district", getDistrictById(beanObj.getDistrictid()));
            model.addAttribute("action", "update.io");
            model.addAttribute("mode", "update");
            request.getSession().setAttribute("body", "/admin/assessor/addassessor.jsp");
            forward = "admin/common";

        }

        request.getSession().removeAttribute("photoname");
        request.getSession().removeAttribute("aadharimage");
        request.getSession().removeAttribute("resumename");
        return forward;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = (String) request.getParameter("recid");
        AssessorDAO record = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), new Integer(recid));

        String[] files = {record.getPhotoname(), record.getAadharimage(), record.getResumename()};
        for (int i = 0; i < files.length; i++) {
            String path = servletContext.getRealPath("/uploaded/assessor/") + File.separator + files[i];
            System.out.println("file path" + path);
            File destinationFile = new File(path);
            try {
                if (destinationFile.exists()) {
                    System.out.println("file exist");
                    boolean flag = destinationFile.delete();
                    System.out.println("deleted file" + flag);
                }

            } catch (Exception e) {
                logger.error("This is Error message", e);
            }
        }

        this.superService.deleteObjectById(new AssessorDAO(), new Integer(recid));

        return "redirect:/admin/assessor/initSearch.io";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = (String) request.getParameter("recid");
        AssessorDAO record = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), new Integer(recid));

        String resume = record.getResumename();

        String path = servletContext.getRealPath("/uploaded/assessor/") + resume;
        System.out.println("file path" + path);
        File sourceFile = new File(path);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + sourceFile.getName() + "\""));

        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int) sourceFile.length());
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));

            //Copy bytes from source to destination(outputstream in this example), closes both streams.
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            logger.error("This is Error message", e);
        }
        return "redirect:/admin/assessor/initSearch.io";
    }

    @RequestMapping(value = "/getJobRole", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getJobRole(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String jobroles = getJobRolesByID(sscid);

        return jobroles;
    }

    @RequestMapping(value = "/getDistricts", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getDistricts(@RequestParam("s_id") String stateid) {

        System.out.println("State ID::" + stateid);
        String districts = getAllDistricts(stateid);

        return districts;
    }

    public Map<Integer, String> getAllStatesValues() {

        Map<Integer, String> states = new HashMap();
        List<SuperBean> records = this.superService.listAllObjects(new StateDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                StateDAO data = (StateDAO) itr.next();
                states.put(data.getStateID(), data.getStateName());
            }
        }

        return states;
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

    public Map<String, String> getAllDistrictValues() {

        Map<String, String> district = new HashMap();
        List<SuperBean> records = this.superService.listAllObjects(new DistrictDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                DistrictDAO data = (DistrictDAO) itr.next();
                district.put("" + data.getDistrictID(), data.getDistrictName());
            }
        }

        return district;
    }

    public String getAllDistricts(String stateid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        List<SuperBean> records = this.superService.listAllObjects(new DistrictDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                DistrictDAO data = (DistrictDAO) itr.next();
                if (data.getState().getStateID() == Integer.parseInt(stateid)) {
                    jsonObj.append("ID", data.getDistrictID());
                    jsonObj.append("NAME", data.getDistrictName());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
    }

    public Map<String, String> getDistrictById(int districtid) {

        Map<String, String> district = new HashMap();
        DistrictDAO records = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), districtid);
        System.out.println("record :" + records.getDistrictID());
        district.put("" + records.getDistrictID(), records.getDistrictName());
        return district;
    }

    public Map<String, String> getSkillsList() {
        Map<String, String> skillList = new HashMap<String, String>();
        skillList.put("Hibernate", "Hibernate");
        skillList.put("Spring", "Spring");
        skillList.put("Apache Wicket", "Apache Wicket");
        skillList.put("Struts", "Struts");
        return skillList;
    }

    public String getStateNameByID(String stateid) {
        StateDAO state = (StateDAO) this.superService.getObjectById(new StateDAO(), new Integer(stateid));
        return state.getStateName();
    }

    public String getDistrictNameByID(String districtid) {

        DistrictDAO district = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), new Integer(districtid));
        return district.getDistrictName();
    }

    public String getJobRoleNameByID(String districtid) {

        QualificationPackDAO role = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(districtid));
        return role.getQpackname();
    }

    public String getJobRolesByID(String sscid) {

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
}
