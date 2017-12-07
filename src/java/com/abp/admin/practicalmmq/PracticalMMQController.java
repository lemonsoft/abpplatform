/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.practicalmmq;

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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
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
@RequestMapping("/admin/practicalmmq")
public class PracticalMMQController {

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

        model.addAttribute("practicalmmq", new PracticalMMQDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/practicalmmq/practicalmmq.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initadd", method = RequestMethod.GET)
    public String initadd(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpackid = (String) request.getParameter("qpid");
        String sscname = (String) request.getParameter("sscname");
        String qpname = (String) request.getParameter("qpname");

        PracticalMMQDAO pmmqObj = new PracticalMMQDAO();
        pmmqObj.setQpackid(new Integer(qpackid));
        model.addAttribute("practicalmmq", pmmqObj);
        model.addAttribute("action", "add.io");
        model.addAttribute("mode", "add");
        request.getSession().setAttribute("sscname", sscname);
        request.getSession().setAttribute("qpname", qpname);

        //request.getSession().setAttribute("body", "/admin/practicalmmq/addsenario.jsp");
        request.getSession().setAttribute("body", "/admin/testpage/tetspage.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("practicalmmq") PracticalMMQDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        this.superService.saveObject(beanObj);

        model.addAttribute("practicalmmq", beanObj);
        model.addAttribute("action", "update.io");
        model.addAttribute("mode", "update");

        request.getSession().setAttribute("body", "/admin/practicalmmq/addsenario.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
    public String initUpdate(@ModelAttribute("practicalmmq") PracticalMMQDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = (String) request.getParameter("recid");
        String sscname = (String) request.getParameter("sscname");
        String qpname = (String) request.getParameter("qpname");
        String qpid = (String) request.getParameter("qid");

        PracticalMMQDAO pmmqObj = (PracticalMMQDAO) this.superService.getObjectById(new PracticalMMQDAO(), new Integer(recid));

        Map param = new HashMap();
        param.put("senario_id", pmmqObj.getId());
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new SenarioQuestionDAO(), param);

        model.addAttribute("records", records);
        model.addAttribute("practicalmmq", pmmqObj);
        model.addAttribute("action", "update.io");
        model.addAttribute("mode", "update");
        request.getSession().setAttribute("sscname", sscname);
        request.getSession().setAttribute("qpname", qpname);

        System.out.println("Stored in session : " + qpid);

        request.getSession().setAttribute("qpid", qpid);

        request.getSession().setAttribute("body", "/admin/practicalmmq/addsenario.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/editMapping", method = RequestMethod.GET)
    public String editMapping(@ModelAttribute("practicalmmq") PracticalMMQDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = request.getParameter("recid");
        String qpid = (String) request.getSession().getAttribute("qpid");

        SenarioQuestionDAO scenarioObj = (SenarioQuestionDAO) this.superService.getObjectById(new SenarioQuestionDAO(), new Integer(recid));

        System.out.println("Getting ID : " + qpid);
        ArrayList noslist = getNOSByID(qpid, scenarioObj.getId());
        System.out.println("Size ::::: of List :::" + noslist.size());
        //JSONArray pcjsonarray = getAllPC();

        model.addAttribute("noslist", noslist);
        model.addAttribute("scenariommq", scenarioObj);
        model.addAttribute("mode", "update");
        request.getSession().setAttribute("body", "/admin/practicalmmq/editmapping.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/viewQuestions", method = RequestMethod.GET)
    public String viewQuestion(@ModelAttribute("practicalmmq") PracticalMMQDAO beanObj, HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = request.getParameter("recid");
        ArrayList alldata = new ArrayList();
        Map param = new HashMap();
        param.put("senario_id", new Integer(recid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new SenarioQuestionDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {

                SenarioQuestionDAO data = (SenarioQuestionDAO) itr.next();
                Map param2 = new HashMap();
                param2.put("question_id", data.getId());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param2);
                if (records2.size() > 0) {
                    String pcidwithmarks = "";
                    int totalmarks = 0;
                    Iterator itr2 = records2.iterator();
                    while (itr2.hasNext()) {
                        PCWithMarksDAO data2 = (PCWithMarksDAO) itr2.next();
                        pcidwithmarks = pcidwithmarks + getPCNameById(data2.getPcid()) + ":(" + data2.getMarks() + "),";
                        totalmarks = totalmarks + data2.getMarks();
                    }
                    data.setPcsselectedmarks(pcidwithmarks);
                    data.setTotalmarks(totalmarks);
                    alldata.add(data);

                }

            }
        }
        model.addAttribute("alldata", alldata);
        request.getSession().setAttribute("body", "/admin/practicalmmq/viewQuestion.jsp");
        return "admin/commonmodal";
    }

    public String getPCNameById(int pcid) {

        String pcname = "";
        try {
            PCDAO pcdaoObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcid);
            pcname = pcdaoObj.getPcid();
        } catch (Exception e) {
            pcname = "Not Found";
        }
        return pcname;
    }

    @RequestMapping(value = "/insertQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String insertQuestion(@RequestParam("sid") String sid, @RequestParam("values") String values) {

        System.out.println(" senario id " + sid);
        System.out.println(" values " + values);
        JSONObject jsonObjreturn = new JSONObject();
        JSONArray arr = new JSONArray(values);
        try {
            for (int i = 0; i < arr.length(); i++) {
                System.out.println(" arr.getString(i) " + arr.getString(i));
                SenarioQuestionDAO pmmqObj = new SenarioQuestionDAO();
                pmmqObj.setSenario_id(new Integer(sid));
                pmmqObj.setQuestion(arr.getString(i));
                pmmqObj.setPcsselectedmarks("");
                this.superService.saveObject(pmmqObj);
            }
            jsonObjreturn.append("status", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObjreturn.append("status", "fail");
        }
        return jsonObjreturn.toString();
    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
    }

    @RequestMapping(value = "/checkRemainingmarks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String checkRemainingmarks(@RequestParam("pcid") String pcid, @RequestParam("questionid") String qid, @RequestParam("entermarks") String entermarks) {

        JSONObject jsonObj = new JSONObject();
        int checkRemaining = checkTotalmarks(new Integer(qid), new Integer(pcid));
        System.out.println("Remaining marks  " + checkRemaining + " entermarks" + entermarks);
        if (checkRemaining < new Integer(entermarks)) {
            jsonObj.append("status", "greator");
        } else {
            jsonObj.append("status", "smaller");
        }
        return jsonObj.toString();
    }

    @RequestMapping(value = "/updatePCIDMARKS", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String updatePCIDMARKS(@RequestParam("data") String data, @RequestParam("questionid") String questionid) {

        JSONObject jsonObj = new JSONObject();
        String status = "update";
        JSONParser parser = new JSONParser();
        try {
            org.json.simple.JSONArray jsonarr = (org.json.simple.JSONArray) parser.parse(data);

            for (int index = 0; index < jsonarr.size(); index++) {
                org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonarr.get(index);
                String pcid = (String) jsonObject.get("pcid");
                String marks = (String) jsonObject.get("marks");

                Map param = new HashMap();
                param.put("question_id", new Integer(questionid));
                param.put("pcid", new Integer(pcid));
                List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param);
                if (records.size() > 0) {
                    PCWithMarksDAO pcwithmarks = (PCWithMarksDAO) records.get(0);
                    pcwithmarks.setMarks(new Integer(marks));
                    this.superService.updateObject(pcwithmarks);
                } else {
                    PCWithMarksDAO pcwithmarks = new PCWithMarksDAO();
                    pcwithmarks.setMarks(new Integer(marks));
                    pcwithmarks.setPcid(new Integer(pcid));
                    pcwithmarks.setQuestion_id(new Integer(questionid));
                    this.superService.saveObject(pcwithmarks);
                }

            }
            //System.out.println(jsonarr);

        } catch (Exception e) {
            status = "notupdate";
            e.printStackTrace();

        }
        jsonObj.append("status", status);
        return jsonObj.toString();
    }

    public int checkTotalmarks(int qid, int pcid) {

        int sum = 0;
        int totalremaining = 0;
        Map param = new HashMap();
        //param.put("question_id", qid);
        param.put("pcid", pcid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param);
        if (records.size() > 0) {

            Iterator itr = records.iterator();
            while (itr.hasNext()) {

                PCWithMarksDAO data = (PCWithMarksDAO) itr.next();
                if(data.getQuestion_id()!=qid){
                    sum = sum + data.getMarks();
                }
                

            }
        }

        PCDAO pcdaoObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcid);
        totalremaining = new Integer(pcdaoObj.getPracticalcutoffmarks()) - sum;

        return totalremaining;
    }

    public ArrayList getNOSByID(String qpid, int questionid) {

        //ArrayList Obj = new ArrayList();
        ArrayList jsonarr = new ArrayList();
        Map param = new HashMap();
        param.put("qpackid", qpid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new NOSDAO(), param);
        if (records.size() > 0) {

            Iterator itr = records.iterator();
            while (itr.hasNext()) {

                NOSDAO data = (NOSDAO) itr.next();
                if (data.getQpackid().equals(qpid)) {

                    ArrayList pcdata = getAllPC(data.getNosID(), questionid);
                    data.setPcdata(pcdata);
                    jsonarr.add(data);
                }

            }
        }

        return jsonarr;
    }

    public ArrayList getAllPC(int nosid, int questionid) {

        ArrayList jsonarr = new ArrayList();

        Map param = new HashMap();
        param.put("nosid", "" + nosid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                DisplayMapping dispmap = new DisplayMapping();

                PCDAO data = (PCDAO) itr.next();
                dispmap.setId(data.getPcID());
                dispmap.setPcid(data.getPcid());
                dispmap.setPcname(data.getPcname());
                dispmap.setActualmarks(data.getPracticalcutoffmarks());
                int remainmarks = new Integer(data.getPracticalcutoffmarks()) - getRemainingMarksByPcid(data.getPcID());
                dispmap.setRemainingmarks("" + remainmarks);
                dispmap.setMarks("" + getMarksByPcid(data.getPcID(), questionid));
                if (getMarksByPcid(data.getPcID(), questionid) > 0) {
                    dispmap.setCheckbox("Y");
                } else {
                    dispmap.setCheckbox("N");
                }
                jsonarr.add(dispmap);
            }
        }

        return jsonarr;
    }

    @RequestMapping(value = "/getSenario", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getSenario(@RequestParam("qid") String qid, HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println(" " + qid);
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackid", new Integer(qid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PracticalMMQDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {

                PracticalMMQDAO data = (PracticalMMQDAO) itr.next();
                if (data.getQpackid() == new Integer(qid)) {
                    System.out.println(" " + data.getQpackid());
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.append("id", data.getId());
                    jsonObj.append("senario", data.getSenario());
                    jsonObj.append("marks", getTotalMarksofSenario(data.getId()));

                    jsonarr.put(jsonObj);
                }

            }
        }
        System.out.println(" " + jsonarr.toString());
        return jsonarr.toString();
    }

    public int getTotalMarksofSenario(int senraioid) {

        int totalmarks = 0;
        Map param = new HashMap();
        param.put("senario_id", senraioid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new SenarioQuestionDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                SenarioQuestionDAO data = (SenarioQuestionDAO) itr.next();
                Map param2 = new HashMap();
                param2.put("question_id", data.getId());
                List<SuperBean> records2 = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param2);
                if (records2.size() > 0) {
                    Iterator itr2 = records2.iterator();
                    while (itr2.hasNext()) {
                        PCWithMarksDAO data1 = (PCWithMarksDAO) itr2.next();
                        totalmarks = totalmarks + data1.getMarks();
                    }

                }

            }
        }
        return totalmarks;
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

    @RequestMapping(value = "/updateSenario", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String updateSenario(@RequestParam("senario") String senario, @RequestParam("senarioid") String senarioid) {

        JSONObject jsonObj = new JSONObject();
        try {
            PracticalMMQDAO pmmqObj = (PracticalMMQDAO) this.superService.getObjectById(new PracticalMMQDAO(), new Integer(senarioid));
            pmmqObj.setSenario(senario);
            this.superService.updateObject(pmmqObj);
            jsonObj.append("status", "save");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObj.append("status", "fail");
        }

        return jsonObj.toString();
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

    public int getMarksByPcid(int pcid, int questionid) {

        int marks = 0;
        Map param = new HashMap();
        param.put("pcid", pcid);
        param.put("question_id", questionid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param);
        if (records.size() > 0) {
            PCWithMarksDAO pcidmarks = (PCWithMarksDAO) records.get(0);
            marks = pcidmarks.getMarks();
        }

        return marks;
    }

    public int getRemainingMarksByPcid(int pcid) {

        int marks = 0;
        Map param = new HashMap();
        param.put("pcid", pcid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCWithMarksDAO pcidmarks = (PCWithMarksDAO) itr.next();
                marks = marks + pcidmarks.getMarks();
            }

        }

        return marks;
    }

}
