/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.generateqp.QuestionPaperDAO;
import com.abp.admin.project.ProjectDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.statedistrict.DistrictDAO;
import com.abp.statedistrict.StateDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.ObjectNotFoundException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/batches")
public class BatchesController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("batches", new BatchesDAO());
        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/batches/batches.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initadd", method = RequestMethod.GET)
    public String initadd(HttpServletRequest request, HttpServletResponse response, Model model) {

        BatchesDAO batches = new BatchesDAO();
        String recid = request.getParameter("recid");
        batches.setQpackId(new Integer(recid));
        model.addAttribute("batches", batches);
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("projects", getAllProjectValues());
        model.addAttribute("action", "add.io");
        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/batches/addbatches.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(@ModelAttribute("batches") BatchesDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        System.out.println("data ::" + beanObj.getQpackId());
        this.superService.saveObject(beanObj);

        return "redirect:/admin/batches/init.io";
    }

    @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
    public String initupdate(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String recid = request.getParameter("recid");
        BatchesDAO batches = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(recid));
        String startdate = batches.getAssessmentStartDate();
        String enddate = batches.getAssessmentEndDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss.s");
        Date jodastarttime = sdf.parse(startdate);        //dtf.parseDateTime(startdate);
        Date jodaendtime = sdf.parse(enddate);

        batches.setAssessmentStartDate(jodastarttime.toString().substring(0, 16));
        batches.setAssessmentEndDate(jodaendtime.toString().substring(0, 16));
        model.addAttribute("batches", batches);
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("projects", getAllProjectValues());
        model.addAttribute("district", getDistrictById(batches.getDistrict_id()));
        model.addAttribute("action", "update.io");
        model.addAttribute("mode", "update");

        request.getSession().setAttribute("body", "/admin/batches/addbatches.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/addassessor", method = RequestMethod.GET)
    public String addAssessor(HttpServletRequest request, HttpServletResponse response, Model model) {

        String batchid = request.getParameter("batchid");
        String sscid = request.getParameter("sscid");
        String qid = request.getParameter("qid");
        if (batchid == null) {
            batchid = (String) request.getSession().getAttribute("batchid");
        }
        if (sscid == null) {
            sscid = (String) request.getSession().getAttribute("sscid");
        }
        if (qid == null) {
            qid = (String) request.getSession().getAttribute("qid");
        }

        try {
            BatchesDAO batch = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
            model.addAttribute("sscid", sscid);
            model.addAttribute("qid", qid);
            model.addAttribute("batch_id", batch.getBatch_id());
            model.addAttribute("pincode", batch.getCenterPincode());
            model.addAttribute("district", getDistrictNameById(batch.getDistrict_id()));
            model.addAttribute("state", getStateById(batch.getState_id()));

            AssessorDAO assessor = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), batch.getAssessorId());
            assessor.setState(getStateById(assessor.getStateid()));
            assessor.setDistrict(getDistrictNameById(assessor.getDistrictid()));
            assessor.setActions(batchid);
            model.addAttribute("record", assessor);
        } catch (ObjectNotFoundException e) {
            model.addAttribute("record", null);
        }
        request.getSession().setAttribute("batchid", batchid);
        request.getSession().setAttribute("sscid", sscid);
        request.getSession().setAttribute("qid", qid);
        model.addAttribute("allstates", getAllStatesValues());
        model.addAttribute("assessor", new AssessorDAO());

        request.getSession().setAttribute("body", "/admin/batches/addassessor.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/addquestionpaper", method = RequestMethod.GET)
    public String addQuestionpaper(HttpServletRequest request, HttpServletResponse response, Model model) {

        String batchid = request.getParameter("batchid");
        String sscid = request.getParameter("sscid");
        String qid = request.getParameter("qid");
        String qpackid = request.getParameter("qpackid");
        if (batchid == null) {
            batchid = (String) request.getSession().getAttribute("batchid");
        }
        if (sscid == null) {
            sscid = (String) request.getSession().getAttribute("sscid");
        }
        if (qid == null) {
            qid = (String) request.getSession().getAttribute("qid");
        }
        if (qpackid == null) {
            qpackid = (String) request.getSession().getAttribute("qpackid");
        }

        try {
            BatchesDAO batch = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
            model.addAttribute("sscid", sscid);
            model.addAttribute("qid", qid);
            model.addAttribute("batch_id", batch.getBatch_id());

            QuestionPaperDAO questionPaperDAO = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), batch.getQuestionPaperId());
            questionPaperDAO.setActions(batchid);

            model.addAttribute("record", questionPaperDAO);

        } catch (ObjectNotFoundException e) {
            model.addAttribute("record", null);
        }
        request.getSession().setAttribute("batchid", batchid);
        request.getSession().setAttribute("sscid", sscid);
        request.getSession().setAttribute("qid", qid);
        request.getSession().setAttribute("qpackid", qpackid);
        String error = request.getParameter("error");
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("allquestionpapers", getAllQuestionPapers(new Integer(qpackid)));

        request.getSession().setAttribute("body", "/admin/batches/addquestionpaper.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(@ModelAttribute("batches") BatchesDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        this.superService.updateObject(beanObj);

        return "redirect:/admin/batches/init.io";
    }

    @RequestMapping(value = "/deleteAssessor", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteAssessor(@ModelAttribute("batches") BatchesDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String batchid = request.getParameter("recid");
        BatchesDAO batch = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
        batch.setAssessorId(0);
        this.superService.updateObject(batch);
        return "redirect:/admin/batches/addassessor.io";
    }

    @RequestMapping(value = "/deleteQuestionPaper", method = {RequestMethod.GET}, produces = "application/json")
    @ResponseBody
    String deleteQuestionPaper(@RequestParam("batchid") String batchid, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {

        boolean flag = true;
        JSONObject jsonObj = new JSONObject();
        BatchesDAO batch = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));

        String startdate = batch.getAssessmentStartDate();
        String enddate = batch.getAssessmentEndDate();

        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
        Date jodastarttime = outFormat.parse(startdate);
        Date jodaendtime = outFormat.parse(enddate);
        Date currentdt = new Date();

        if (currentdt.after(jodastarttime)) {
            flag = false;
        }
        if (currentdt.after(jodaendtime)) {
            flag = false;
        }
        if (flag) {
            batch.setQuestionPaperId(0);
            this.superService.updateObject(batch);
            jsonObj.append("status", "Question Paper Deleted");
        } else {
            jsonObj.append("status", "Assesment Started Cannot Delete Question Paper.");
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/searchbatch", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String searchbatch(@RequestParam("batchid") String batchid) {

        JSONArray jsonarrbatch = new JSONArray();
        Map param = new HashMap();
        param.put("batch_id", new Integer(batchid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                BatchesDAO batchdo = (BatchesDAO) itr.next();
                JSONObject jsonObj = new JSONObject();
                jsonObj.append("ID", batchdo.getID());
                jsonObj.append("batch_id", batchdo.getBatch_id());
                jsonObj.append("batch_size", batchdo.getBatch_size());
                jsonObj.append("state", getStateById(batchdo.getState_id()));
                jsonObj.append("centerAddress", batchdo.getCenterAddress());
                jsonObj.append("assessmentStartDate", batchdo.getAssessmentStartDate());
                jsonObj.append("assessmentEndDate", batchdo.getAssessmentEndDate());
                jsonObj.append("tpName", batchdo.getTpName());
                jsonObj.append("assessorId", getAssessorById(batchdo.getAssessorId()));
                jsonObj.append("questionPaperId", batchdo.getQuestionPaperId());
                System.out.println(jsonObj.toString());
                jsonarrbatch.put(jsonObj);

                System.out.println("  batch record count :::::::::::::::::::::::::::::::");
            }
        }

        return jsonarrbatch.toString();

    }

    @RequestMapping(value = "/getDistricts", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getDistricts(@RequestParam("s_id") String stateid) {

        System.out.println("State ID::" + stateid);
        String districts = getAllDistricts(stateid);

        return districts;
    }

    @RequestMapping(value = "/loadassessor", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String loadAssessor(HttpServletRequest request, @RequestParam("stateid") String stateid, @RequestParam("districtid") String districtid) {

        String batchid = (String) request.getSession().getAttribute("batchid");

        System.out.println("batchid ID::" + batchid);
        BatchesDAO batchObj = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
        String assessors = getAllAssessor(batchObj.getAssessorId(), stateid, districtid);

        return assessors;
    }

    @RequestMapping(value = "/asignassessor", method = RequestMethod.GET)
    public String asignassessor(HttpServletRequest request, HttpServletResponse response, Model model) {

        String assessorid = request.getParameter("assid");
        String batchid = (String) request.getSession().getAttribute("batchid");
        BatchesDAO batchObj = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
        batchObj.setAssessorId(new Integer(assessorid));
        this.superService.updateObject(batchObj);
        return "redirect:/admin/batches/addassessor.io";
    }

    @RequestMapping(value = "/asignQuestionPaper", method = RequestMethod.GET)
    public String asignQuestionPaper(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        boolean flag = true;
        String qpaperid = request.getParameter("qpaperid");
        String batchid = (String) request.getSession().getAttribute("batchid");
        BatchesDAO batchObj = (BatchesDAO) this.superService.getObjectById(new BatchesDAO(), new Integer(batchid));
        String startdate = batchObj.getAssessmentStartDate();
        String enddate = batchObj.getAssessmentEndDate();

//        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss.s");
//        DateTime jodastarttime = dtf.parseDateTime(startdate);
//        DateTime jodaendtime = dtf.parseDateTime(enddate);
//        DateTime currentdt = new DateTime();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
        Date jodastarttime = outFormat.parse(startdate);
        Date jodaendtime = outFormat.parse(enddate);
        Date currentdt = new Date();

        System.out.println("Start Time " + jodastarttime);
        System.out.println("End Time " + jodaendtime);
        System.out.println("Current Time " + currentdt);

        if (currentdt.after(jodastarttime)) {
            flag = false;

        }
        if (currentdt.after(jodaendtime)) {
            flag = false;

        }
        if (flag) {
            batchObj.setQuestionPaperId(new Integer(qpaperid));
            this.superService.updateObject(batchObj);
        } else {
            model.addAttribute("error", "Assesment started cannot assign question paper.");
        }
        return "redirect:/admin/batches/addquestionpaper.io";
    }

    @RequestMapping(value = "/checkBatchID", method = RequestMethod.GET)
    public @ResponseBody
    String checkBatchID(@RequestParam("batchid") String batchid) {
        String status = "avail";
        boolean flag = false;
        try {
            if (batchid != null && !batchid.isEmpty()) {
                System.out.println("getting data from jquery----------------" + batchid);
                Map param = new HashMap();
                param.put("batch_id", new Integer(batchid));
                flag = this.superService.duplicateCheck(new BatchesDAO(), param);
                if (flag) {
                    status = "notavail";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "notavail";
        }

        return status;
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

    public ArrayList getAllQuestionPapers(int qpid) {

        ArrayList objlist = new ArrayList();
        Map param = new HashMap();
        param.put("qpackid", qpid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionPaperDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionPaperDAO data = (QuestionPaperDAO) itr.next();
                objlist.add(data);
            }
        }
        return objlist;
    }

    public String getAllAssessor(int assid, String stateid, String districtid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("stateid", new Integer(stateid));
        param.put("districtid", new Integer(districtid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new AssessorDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                AssessorDAO data = (AssessorDAO) itr.next();
                if (data.getAssessorid() != assid) {
                    jsonObj.append("ID", data.getAssessorid());
                    jsonObj.append("LoginID", data.getLoginid());
                    jsonObj.append("Name", data.getFirstname());
                    jsonObj.append("EmailID", data.getEmailid());
                    jsonObj.append("State", getStateById(data.getStateid()));
                    jsonObj.append("District", getDistrictNameById(data.getDistrictid()));
                    jsonObj.append("Pincode", data.getPincode());
                    jsonObj.append("ContactNo", data.getContactno());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr.toString();
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

    @RequestMapping(value = "/getbatches", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getBatches(@RequestParam("qpid") String qpid) {

        System.out.println("Qpack ID ID::" + qpid);
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackId", new Integer(qpid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new BatchesDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                BatchesDAO batchdo = (BatchesDAO) itr.next();
                JSONObject jsonObj = new JSONObject();
                jsonObj.append("ID", batchdo.getID());
                jsonObj.append("batch_id", batchdo.getBatch_id());
                jsonObj.append("batch_size", batchdo.getBatch_size());
                jsonObj.append("state", getStateById(batchdo.getState_id()));
                jsonObj.append("centerAddress", batchdo.getCenterAddress());
                jsonObj.append("assessmentStartDate", batchdo.getAssessmentStartDate());
                jsonObj.append("assessmentEndDate", batchdo.getAssessmentEndDate());
                jsonObj.append("tpName", batchdo.getTpName());
                jsonObj.append("assessorId", getAssessorById(batchdo.getAssessorId()));
                jsonObj.append("questionPaperId", getQuestionById(batchdo.getQuestionPaperId()));
                System.out.println(jsonObj.toString());
                jsonarr.put(jsonObj);
            }
        }

        return jsonarr.toString();
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

    public String getStateById(int stateid) {

        String statename = "";
        StateDAO records = (StateDAO) this.superService.getObjectById(new StateDAO(), stateid);
        System.out.println("record :" + records.getStateName());
        statename = records.getStateName();
        return statename;
    }

    public String getDistrictNameById(int districtid) {

        String districtname = "";
        DistrictDAO records = (DistrictDAO) this.superService.getObjectById(new DistrictDAO(), districtid);

        districtname = records.getDistrictName();
        return districtname;
    }

    public String getAssessorById(int assorid) {

        String assoridname = "";
        try {
            AssessorDAO records = (AssessorDAO) this.superService.getObjectById(new AssessorDAO(), assorid);
            assoridname = records.getLoginid();
        } catch (Exception e) {
            assoridname = "Assessor";
        }

        return assoridname;
    }

    public String getQuestionById(int questionid) {

        String questionname = "";
        try {
            QuestionPaperDAO records = (QuestionPaperDAO) this.superService.getObjectById(new QuestionPaperDAO(), questionid);
            questionname = records.getQuestionpapername();
        } catch (Exception e) {
            questionname = "Question Paper";
        }

        return questionname;
    }

    public Map<Integer, String> getAllProjectValues() {

        Map<Integer, String> projects = new HashMap();
        List<SuperBean> records = this.superService.listAllObjects(new ProjectDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                ProjectDAO data = (ProjectDAO) itr.next();
                projects.put(data.getID(), data.getProjectname());
            }
        }

        return projects;
    }
}
