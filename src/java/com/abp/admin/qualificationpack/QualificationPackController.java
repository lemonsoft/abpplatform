/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.qualificationpack;

import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.batches.UserDAO;
import com.abp.admin.practicalmmq.PCWithMarksDAO;
import com.abp.admin.project.questions.MultiLangQuestionDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.project.theorymmq.TheoryPCIDWithMarks;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/admin/qualificationpack")
public class QualificationPackController {

    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("qualificationpack", new QualificationPackDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());
        model.addAttribute("qpack", getSectorSkillCouncil());
        request.getSession().setAttribute("body", "/admin/qualificationpack/qualificationpack.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/openaddqp", method = RequestMethod.GET)
    public String openaddqp(HttpServletRequest request, HttpServletResponse response, Model model) {

        String sscid = request.getParameter("sscid");
        QualificationPackDAO beanObj = new QualificationPackDAO();
        beanObj.setSsc_id(sscid);
        model.addAttribute("qualificationpack", beanObj);

        System.out.println("Getting ssc_id  " + sscid);
        model.addAttribute("action", "add.io");

        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/qualificationpack/addqp.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/openaddnos", method = RequestMethod.GET)
    public String openaddnos(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpid = request.getParameter("qpid");
        NOSDAO beanObj = new NOSDAO();
        beanObj.setQpackid(qpid);
        model.addAttribute("nosdao", beanObj);

        System.out.println("Getting qpid  " + qpid);
        model.addAttribute("action", "add.io");

        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/qualificationpack/addnos.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/openaddpc", method = RequestMethod.GET)
    public String openaddpc(HttpServletRequest request, HttpServletResponse response, Model model) {

        String nosid = request.getParameter("nosid");
        String qpackid = request.getParameter("qpid");

        NOSDAO nosdao = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(nosid));
        String getnosid = nosdao.getNosid();

        Map param = new HashMap();
        param.put("nosid", nosid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);

        int size = records.size() + 1;
        String makePCID = getnosid + "_PC" + size;
        PCDAO beanObj = new PCDAO();
        beanObj.setNosid(nosid);
        beanObj.setPcid(makePCID);
        beanObj.setQpackid(new Integer(qpackid));

        model.addAttribute("pcdao", beanObj);

        System.out.println("Getting nosid  " + nosid);
        model.addAttribute("action", "add.io");

        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/qualificationpack/addpc.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    String add(@RequestParam("name") String name, HttpServletRequest request) {

        System.out.println("getting data from jquery----------------" + name);
        return "OK";
    }

    @RequestMapping(value = "/saveqp", method = RequestMethod.GET)
    public @ResponseBody
    String saveState(@RequestParam("ssc_id") String ssid, @RequestParam("qpackid") String qpackid, @RequestParam("qpackname") String qpackname,
            @RequestParam("qpacklevel") String qpacklevel, @RequestParam("totaltheorymarks") String totaltheorymarks,
            @RequestParam("totalpracticalmarks") String totalpracticalmarks, @RequestParam("totalmarks") String totalmarks,
            @RequestParam("theorycutoffmarks") String theorycutoffmarks, @RequestParam("practicalcutoffmarks") String practicalcutoffmarks,
            @RequestParam("overallcutoffmarks") String overallcutoffmarks, @RequestParam("weightedavgmarks") String weightedavgmarks) {

        String status = "Record Save Successfully";

        QualificationPackDAO beanObj = new QualificationPackDAO();
        beanObj.setQpackid(qpackid);
        beanObj.setQpackname(qpackname);
        beanObj.setQpacklevel(qpacklevel);
        beanObj.setTotaltheorymarks(totaltheorymarks);
        beanObj.setTotalpracticalmarks(totalpracticalmarks);
        beanObj.setTotalmarks(totalmarks);
        beanObj.setTheorycutoffmarks(theorycutoffmarks);
        beanObj.setPracticalcutoffmarks(practicalcutoffmarks);
        beanObj.setOverallcutoffmarks(overallcutoffmarks);
        beanObj.setWeightedavgmarks(weightedavgmarks);
        beanObj.setSscid(ssid);
        this.superService.saveObject(beanObj);
        System.out.println("goin to save...." + ssid);
        return status;
    }

    @RequestMapping(value = "/savenos", method = RequestMethod.GET)
    public @ResponseBody
    String saveNOS(@RequestParam("qpackid") String qpid, @RequestParam("nosid") String nosid, @RequestParam("nosname") String nosname,
            @RequestParam("theorycutoffmarks") String theorycutoffmarks, @RequestParam("practicalcutoffmarks") String practicalcutoffmarks,
            @RequestParam("overallcutoffmarks") String overallcutoffmarks, @RequestParam("weightedavgmarks") String weightedavgmarks) {

        String status = "Record Save Successfully";

        NOSDAO beanObj = new NOSDAO();
        beanObj.setNosid(nosid);
        beanObj.setNosname(nosname);
        beanObj.setTheorycutoffmarks(theorycutoffmarks);
        beanObj.setPracticalcutoffmarks(practicalcutoffmarks);
        beanObj.setOverallcutoffmarks(overallcutoffmarks);
        beanObj.setWeightedavgmarks(weightedavgmarks);
        beanObj.setQpackid(qpid);
        this.superService.saveObject(beanObj);
        System.out.println("goin to save...." + nosid);
        return status;
    }

    @RequestMapping(value = "/savepc", method = RequestMethod.GET)
    public @ResponseBody
    String savePC(@RequestParam("qpackid") String qpackid, @RequestParam("nosid") String nosid, @RequestParam("pcid") String pcid, @RequestParam("pcname") String pcname,
            @RequestParam("theorycutoffmarks") String theorycutoffmarks, @RequestParam("practicalcutoffmarks") String practicalcutoffmarks,
            @RequestParam("overallcutoffmarks") String overallcutoffmarks) {

        String status = "Record Save Successfully";
        System.out.println("goin to save...." + pcname);
        PCDAO beanObj = new PCDAO();
        beanObj.setPcid(pcid);
        beanObj.setPcname(pcname);
        beanObj.setTheorycutoffmarks(theorycutoffmarks);
        beanObj.setPracticalcutoffmarks(practicalcutoffmarks);
        beanObj.setOverallcutoffmarks(overallcutoffmarks);
        beanObj.setNosid(nosid);
        beanObj.setQpackid(new Integer(qpackid));
        this.superService.saveObject(beanObj);
        System.out.println("goin to save...." + pcid);
        return status;
    }

    @RequestMapping(value = "/initupdateqp", method = RequestMethod.GET)
    public String initUpdateQP(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = request.getParameter("recid");
        QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(recid));
        beanObj.setSsc_id(beanObj.getSscid());
        beanObj.setTheorycutoffmarks(beanObj.getTheorycutoffmarks());
        model.addAttribute("qualificationpack", beanObj);
        model.addAttribute("action", "updateqp.io");
        model.addAttribute("mode", "update");

        request.getSession().setAttribute("body", "/admin/qualificationpack/addqp.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/updateqp", method = RequestMethod.GET)
    public @ResponseBody
    String updateqp(@RequestParam("qpid") String qpid, @RequestParam("ssc_id") String ssid, @RequestParam("qpackid") String qpackid, @RequestParam("qpackname") String qpackname,
            @RequestParam("qpacklevel") String qpacklevel, @RequestParam("totaltheorymarks") String totaltheorymarks,
            @RequestParam("totalpracticalmarks") String totalpracticalmarks, @RequestParam("totalmarks") String totalmarks,
            @RequestParam("theorycutoffmarks") String theorycutoffmarks, @RequestParam("practicalcutoffmarks") String practicalcutoffmarks,
            @RequestParam("overallcutoffmarks") String overallcutoffmarks, @RequestParam("weightedavgmarks") String weightedavgmarks) {

        String status = "Record Update Successfully";
        QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpid));
        beanObj.setQpackid(qpackid);
        beanObj.setQpackname(qpackname);
        beanObj.setQpacklevel(qpacklevel);
        beanObj.setTotaltheorymarks(totaltheorymarks);
        beanObj.setTotalpracticalmarks(totalpracticalmarks);
        beanObj.setTotalmarks(totalmarks);
        beanObj.setTheorycutoffmarks(theorycutoffmarks);
        beanObj.setPracticalcutoffmarks(practicalcutoffmarks);
        beanObj.setOverallcutoffmarks(overallcutoffmarks);
        beanObj.setWeightedavgmarks(weightedavgmarks);
        //beanObj.setSscid(ssid);
        this.superService.updateObject(beanObj);
        System.out.println("goin to save...." + qpid);
        return status;
    }

    @RequestMapping(value = "/deleteqp", method = RequestMethod.GET)
    public @ResponseBody
    String deleteqp(@RequestParam("qpid") String qpid, HttpServletRequest request, HttpServletResponse response, Model model) {

        QualificationPackDAO qpObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpid));

        System.out.println("qpack id " + qpid);

        Map param = new HashMap();
        param.put("qpackid", qpid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new NOSDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                NOSDAO nosdata = (NOSDAO) itr.next();
                System.out.println("Getting nos id" + nosdata.getNosID());

                /*
                Delete PC By NOS ID
                 */
                Map paramnos = new HashMap();
                paramnos.put("nosid", "" + nosdata.getNosID());
                List<SuperBean> recordspc = this.superService.listAllObjectsByCriteria(new PCDAO(), paramnos);
                if (recordspc.size() > 0) {
                    Iterator itrpc = recordspc.iterator();
                    while (itrpc.hasNext()) {
                        PCDAO pcdata = (PCDAO) itrpc.next();

                        /*
                            Delete Question By PC ID
                         */
                        Map paramquestion = new HashMap();
                        paramquestion.put("pcid", pcdata.getPcID());
                        List<SuperBean> questions = this.superService.listAllObjectsByCriteria(new QuestionDAO(), paramquestion);
                        if (questions.size() > 0) {
                            Iterator itrquestion = questions.iterator();
                            while (itrquestion.hasNext()) {
                                QuestionDAO questiondata = (QuestionDAO) itrquestion.next();

                                /*
                                    Delete Multilanguage Question By Question ID
                                 */
                                Map multilangquestion = new HashMap();
                                multilangquestion.put("pcid", pcdata.getPcID());
                                List<SuperBean> multilangquestions = this.superService.listAllObjectsByCriteria(new MultiLangQuestionDAO(), multilangquestion);
                                if (multilangquestions.size() > 0) {
                                    Iterator itrmultiquestion = multilangquestions.iterator();
                                    while (itrmultiquestion.hasNext()) {
                                        MultiLangQuestionDAO multilangquestiondata = (MultiLangQuestionDAO) itrquestion.next();
                                        this.superService.deleteObject(multilangquestiondata);

                                    }
                                }
                                this.superService.deleteObject(questiondata);
                            }
                        }

                        /*
                            Delete TheoryMMQQuestion By PC ID
                         */
                        Map theoryquestion = new HashMap();
                        theoryquestion.put("pcid", pcdata.getPcID());
                        List<SuperBean> theoryquestions = this.superService.listAllObjectsByCriteria(new TheoryPCIDWithMarks(), theoryquestion);
                        if (theoryquestions.size() > 0) {
                            Iterator itrtheoryquestion = theoryquestions.iterator();
                            while (itrtheoryquestion.hasNext()) {
                                TheoryPCIDWithMarks theoryquestiondata = (TheoryPCIDWithMarks) itrtheoryquestion.next();

                                this.superService.deleteObject(theoryquestiondata);
                            }
                        }

                        /*
                            Delete PracticalMMQQuestion By PC ID
                         */
                        Map practicalquestion = new HashMap();
                        practicalquestion.put("pcid", pcdata.getPcID());
                        List<SuperBean> practicalquestions = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), practicalquestion);
                        if (practicalquestions.size() > 0) {
                            Iterator itrpracticalquestion = practicalquestions.iterator();
                            while (itrpracticalquestion.hasNext()) {
                                PCWithMarksDAO practicalquestiondata = (PCWithMarksDAO) itrpracticalquestion.next();

                                this.superService.deleteObject(practicalquestiondata);
                            }
                        }

                        this.superService.deleteObject(pcdata);
                    }
                }
                this.superService.deleteObject(nosdata);
            }

        }
        Map batchparam = new HashMap();
        batchparam.put("qpackid", qpid);
        List<SuperBean> batchrecords = this.superService.listAllObjectsByCriteria(new BatchesDAO(), batchparam);
        if (batchrecords.size() > 0) {
            Iterator batchitr = batchrecords.iterator();
            while (batchitr.hasNext()) {
                BatchesDAO batchdata = (BatchesDAO) batchitr.next();
                Map userparam = new HashMap();
                userparam.put("batchid", batchdata.getBatch_id());
                List<SuperBean> userrecords = this.superService.listAllObjectsByCriteria(new UserDAO(), userparam);
                if (userrecords.size() > 0) {
                    Iterator useritr = userrecords.iterator();
                    while (useritr.hasNext()) {
                        UserDAO userdata = (UserDAO) useritr.next();
                        this.superService.deleteObject(userdata);
                    }

                    this.superService.deleteObject(batchdata);
                }

            }
        }
        this.superService.deleteObject(qpObj);

        return "delete";
    }

    @RequestMapping(value = "/initUpdatenos", method = RequestMethod.GET)
    public String initUpdatenos(HttpServletRequest request, HttpServletResponse response,
            Model model
    ) {

        String recid = request.getParameter("recid");
        NOSDAO beanObj = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(recid));

        beanObj.setQpackid(beanObj.getQpackid());
        model.addAttribute("nosdao", beanObj);

        model.addAttribute("action", "updatenos.io");
        model.addAttribute("mode", "update");

        request.getSession().setAttribute("body", "/admin/qualificationpack/addnos.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/updatenos", method = RequestMethod.GET)
    public @ResponseBody
    String updatenos(@RequestParam("nosID") String nosID,
            @RequestParam("qpackid") String qpackid,
            @RequestParam("nosid") String nosid,
            @RequestParam("nosname") String nosname,
            @RequestParam("theorycutoffmarks") String theorycutoffmarks,
            @RequestParam("practicalcutoffmarks") String practicalcutoffmarks,
            @RequestParam("overallcutoffmarks") String overallcutoffmarks,
            @RequestParam("weightedavgmarks") String weightedavgmarks
    ) {

        String status = "Record Update Successfully";
        NOSDAO beanObj = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(nosID));
        beanObj.setQpackid(qpackid);
        beanObj.setNosid(nosid);
        beanObj.setNosname(nosname);

        beanObj.setTheorycutoffmarks(theorycutoffmarks);
        beanObj.setPracticalcutoffmarks(practicalcutoffmarks);
        beanObj.setOverallcutoffmarks(overallcutoffmarks);
        beanObj.setWeightedavgmarks(weightedavgmarks);
        //beanObj.setSscid(ssid);
        this.superService.updateObject(beanObj);
        System.out.println("goin to save...." + nosID);
        return status;
    }

    @RequestMapping(value = "/deletenos", method = RequestMethod.GET)
    public @ResponseBody
    String deletenos(@RequestParam("nosid") String nosid, HttpServletRequest request,
            HttpServletResponse response, Model model
    ) {

        NOSDAO nosObj = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(nosid));

        /*
                Delete PC By NOS ID
         */
        Map paramnos = new HashMap();
        paramnos.put("nosid", "" + nosObj.getNosID());
        List<SuperBean> recordspc = this.superService.listAllObjectsByCriteria(new PCDAO(), paramnos);
        if (recordspc.size() > 0) {
            Iterator itrpc = recordspc.iterator();
            while (itrpc.hasNext()) {
                PCDAO pcdata = (PCDAO) itrpc.next();

                /*
                            Delete Question By PC ID
                 */
                Map paramquestion = new HashMap();
                paramquestion.put("pcid", pcdata.getPcID());
                List<SuperBean> questions = this.superService.listAllObjectsByCriteria(new QuestionDAO(), paramquestion);
                if (questions.size() > 0) {
                    Iterator itrquestion = questions.iterator();
                    while (itrquestion.hasNext()) {
                        QuestionDAO questiondata = (QuestionDAO) itrquestion.next();

                        /*
                                    Delete Multilanguage Question By Question ID
                         */
                        Map multilangquestion = new HashMap();
                        multilangquestion.put("pcid", pcdata.getPcID());
                        List<SuperBean> multilangquestions = this.superService.listAllObjectsByCriteria(new MultiLangQuestionDAO(), multilangquestion);
                        if (multilangquestions.size() > 0) {
                            Iterator itrmultiquestion = multilangquestions.iterator();
                            while (itrmultiquestion.hasNext()) {
                                MultiLangQuestionDAO multilangquestiondata = (MultiLangQuestionDAO) itrquestion.next();
                                this.superService.deleteObject(multilangquestiondata);

                            }
                        }
                        this.superService.deleteObject(questiondata);
                    }
                }

                /*
                            Delete TheoryMMQQuestion By PC ID
                 */
                Map theoryquestion = new HashMap();
                theoryquestion.put("pcid", pcdata.getPcID());
                List<SuperBean> theoryquestions = this.superService.listAllObjectsByCriteria(new TheoryPCIDWithMarks(), theoryquestion);
                if (theoryquestions.size() > 0) {
                    Iterator itrtheoryquestion = theoryquestions.iterator();
                    while (itrtheoryquestion.hasNext()) {
                        TheoryPCIDWithMarks theoryquestiondata = (TheoryPCIDWithMarks) itrtheoryquestion.next();

                        this.superService.deleteObject(theoryquestiondata);
                    }
                }

                /*
                            Delete PracticalMMQQuestion By PC ID
                 */
                Map practicalquestion = new HashMap();
                practicalquestion.put("pcid", pcdata.getPcID());
                List<SuperBean> practicalquestions = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), practicalquestion);
                if (practicalquestions.size() > 0) {
                    Iterator itrpracticalquestion = practicalquestions.iterator();
                    while (itrpracticalquestion.hasNext()) {
                        PCWithMarksDAO practicalquestiondata = (PCWithMarksDAO) itrpracticalquestion.next();

                        this.superService.deleteObject(practicalquestiondata);
                    }
                }

                this.superService.deleteObject(pcdata);
            }
        }

        return "delete";
    }

    @RequestMapping(value = "/initUpdatepc", method = RequestMethod.GET)
    public String initUpdatepc(HttpServletRequest request, HttpServletResponse response,
            Model model
    ) {

        String recid = request.getParameter("recid");
        PCDAO beanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), new Integer(recid));

        beanObj.setNosid(beanObj.getNosid());
        model.addAttribute("pcdao", beanObj);

        model.addAttribute("action", "updatepc.io");
        model.addAttribute("mode", "update");

        request.getSession().setAttribute("body", "/admin/qualificationpack/addpc.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/updatepc", method = RequestMethod.GET)
    public @ResponseBody
    String updatepc(@RequestParam("pcID") String pcID,
            @RequestParam("nosid") String nosid,
            @RequestParam("pcid") String pcid,
            @RequestParam("pcname") String pcname,
            @RequestParam("theorycutoffmarks") String theorycutoffmarks,
            @RequestParam("practicalcutoffmarks") String practicalcutoffmarks,
            @RequestParam("overallcutoffmarks") String overallcutoffmarks
    ) {

        String status = "Record Update Successfully";
        PCDAO beanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), new Integer(pcID));
        beanObj.setNosid(nosid);
        beanObj.setPcid(pcid);
        beanObj.setPcname(pcname);

        beanObj.setTheorycutoffmarks(theorycutoffmarks);
        beanObj.setPracticalcutoffmarks(practicalcutoffmarks);
        beanObj.setOverallcutoffmarks(overallcutoffmarks);

        //beanObj.setSscid(ssid);
        this.superService.updateObject(beanObj);
        System.out.println("goin to save...." + pcID);
        return status;
    }

    @RequestMapping(value = "/deletepc", method = RequestMethod.GET)
    public @ResponseBody
    String deletepc(@RequestParam("pcid") String pcid, HttpServletRequest request,
            HttpServletResponse response, Model model
    ) {

        PCDAO pcdata = (PCDAO) this.superService.getObjectById(new PCDAO(), new Integer(pcid));
        /*
                            Delete Question By PC ID
         */
        Map paramquestion = new HashMap();
        paramquestion.put("pcid", pcdata.getPcID());
        List<SuperBean> questions = this.superService.listAllObjectsByCriteria(new QuestionDAO(), paramquestion);
        if (questions.size() > 0) {
            Iterator itrquestion = questions.iterator();
            while (itrquestion.hasNext()) {
                QuestionDAO questiondata = (QuestionDAO) itrquestion.next();

                /*
                                    Delete Multilanguage Question By Question ID
                 */
                Map multilangquestion = new HashMap();
                multilangquestion.put("pcid", pcdata.getPcID());
                List<SuperBean> multilangquestions = this.superService.listAllObjectsByCriteria(new MultiLangQuestionDAO(), multilangquestion);
                if (multilangquestions.size() > 0) {
                    Iterator itrmultiquestion = multilangquestions.iterator();
                    while (itrmultiquestion.hasNext()) {
                        MultiLangQuestionDAO multilangquestiondata = (MultiLangQuestionDAO) itrquestion.next();
                        this.superService.deleteObject(multilangquestiondata);

                    }
                }
                this.superService.deleteObject(questiondata);
            }
        }

        /*
                            Delete TheoryMMQQuestion By PC ID
         */
        Map theoryquestion = new HashMap();
        theoryquestion.put("pcid", pcdata.getPcID());
        List<SuperBean> theoryquestions = this.superService.listAllObjectsByCriteria(new TheoryPCIDWithMarks(), theoryquestion);
        if (theoryquestions.size() > 0) {
            Iterator itrtheoryquestion = theoryquestions.iterator();
            while (itrtheoryquestion.hasNext()) {
                TheoryPCIDWithMarks theoryquestiondata = (TheoryPCIDWithMarks) itrtheoryquestion.next();

                this.superService.deleteObject(theoryquestiondata);
            }
        }

        /*
                            Delete PracticalMMQQuestion By PC ID
         */
        Map practicalquestion = new HashMap();
        practicalquestion.put("pcid", pcdata.getPcID());
        List<SuperBean> practicalquestions = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), practicalquestion);
        if (practicalquestions.size() > 0) {
            Iterator itrpracticalquestion = practicalquestions.iterator();
            while (itrpracticalquestion.hasNext()) {
                PCWithMarksDAO practicalquestiondata = (PCWithMarksDAO) itrpracticalquestion.next();

                this.superService.deleteObject(practicalquestiondata);
            }
        }

        this.superService.deleteObject(pcdata);

        return "delete";
    }

    @RequestMapping(value = "/checkQPID", method = RequestMethod.GET)
    public @ResponseBody
    String checkQPID(@RequestParam("name") String qpid) {
        String status = "avail";
        boolean flag = false;
        try {
            if (!qpid.isEmpty() && qpid != null) {
                System.out.println("getting data from jquery----------------" + qpid);
                Map param = new HashMap();
                param.put("qpackid", qpid);
                flag = this.superService.duplicateCheck(new QualificationPackDAO(), param);
                if (flag) {
                    status = "notavail";
                }

            }
        } catch (Exception e) {
            status = "notavail";
        }

        return status;
    }

    @RequestMapping(value = "/checkNOSID", method = RequestMethod.GET)
    public @ResponseBody
    String checkNOSID(@RequestParam("name") String nosid) {
        String status = "avail";
        boolean flag = false;
        try {
            if (!nosid.isEmpty() && nosid != null) {
                System.out.println("getting data from jquery----------------" + nosid);
                Map param = new HashMap();
                param.put("nosid", nosid);
                flag = this.superService.duplicateCheck(new NOSDAO(), param);
                if (flag) {
                    status = "notavail";
                }

            }
        } catch (Exception e) {
            status = "notavail";
        }

        return status;
    }

    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid
    ) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
    }

    @RequestMapping(value = "/checkTheoryMarks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String checkTheoryMarks(@RequestParam("qpackid") String qpackid,
            @RequestParam("nosID") String nosID,
            @RequestParam("theorymarks") String theorymarks
    ) {

        JSONObject jsonObj = new JSONObject();
        int totaltheroycutoffmarks = 0;
        QualificationPackDAO qpack = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));
        Map param = new HashMap();
        param.put("qpackid", qpackid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new NOSDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                NOSDAO nosdao = (NOSDAO) itr.next();
                if (new Integer(nosID) != nosdao.getNosID()) {
                    totaltheroycutoffmarks = totaltheroycutoffmarks + new Integer(nosdao.getTheorycutoffmarks());
                }

            }
        }
        int sumofallmarks = totaltheroycutoffmarks + new Integer(theorymarks);
        if (sumofallmarks > new Integer(qpack.getTotaltheorymarks())) {
            jsonObj.append("status", "greator");
        } else {
            jsonObj.append("status", "smaller");
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/checkPracticalMarks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String checkPracticalMarks(@RequestParam("qpackid") String qpackid,
            @RequestParam("nosID") String nosID,
            @RequestParam("practicalmarks") String practicalmarks
    ) {

        JSONObject jsonObj = new JSONObject();
        int totalpracticalcutoffmarks = 0;
        QualificationPackDAO qpack = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));
        Map param = new HashMap();
        param.put("qpackid", qpackid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new NOSDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                NOSDAO nosdao = (NOSDAO) itr.next();
                if (new Integer(nosID) != nosdao.getNosID()) {
                    totalpracticalcutoffmarks = totalpracticalcutoffmarks + new Integer(nosdao.getPracticalcutoffmarks());
                }

            }
        }
        int sumofallmarks = totalpracticalcutoffmarks + new Integer(practicalmarks);
        if (sumofallmarks > new Integer(qpack.getTotalpracticalmarks())) {
            jsonObj.append("status", "greator");
        } else {
            jsonObj.append("status", "smaller");
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/checkPCTheoryMarks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String checkPCTheoryMarks(@RequestParam("nosid") String nosid,
            @RequestParam("pcID") String pcID,
            @RequestParam("theorymarks") String theorymarks
    ) {

        JSONObject jsonObj = new JSONObject();
        int totaltheroycutoffmarks = 0;
        NOSDAO nosdao = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(nosid));
        Map param = new HashMap();
        param.put("nosid", nosid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCDAO pcdao = (PCDAO) itr.next();
                if (new Integer(pcID) != pcdao.getPcID()) {
                    totaltheroycutoffmarks = totaltheroycutoffmarks + new Integer(pcdao.getTheorycutoffmarks());
                }

            }
        }
        int sumofallmarks = totaltheroycutoffmarks + new Integer(theorymarks);
        if (sumofallmarks > new Integer(nosdao.getTheorycutoffmarks())) {
            jsonObj.append("status", "greator");
        } else {
            jsonObj.append("status", "smaller");
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/checkPCPracticalMarks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String checkPCPracticalMarks(@RequestParam("nosid") String nosid,
            @RequestParam("pcID") String pcID,
            @RequestParam("practicalmarks") String practicalmarks
    ) {

        JSONObject jsonObj = new JSONObject();
        int totalpracticalcutoffmarks = 0;
        NOSDAO nosdao = (NOSDAO) this.superService.getObjectById(new NOSDAO(), new Integer(nosid));
        Map param = new HashMap();
        param.put("nosid", nosid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCDAO pcdao = (PCDAO) itr.next();
                if (new Integer(pcID) != pcdao.getPcID()) {
                    totalpracticalcutoffmarks = totalpracticalcutoffmarks + new Integer(pcdao.getPracticalcutoffmarks());
                }

            }
        }
        int sumofallmarks = totalpracticalcutoffmarks + new Integer(practicalmarks);
        if (sumofallmarks > new Integer(nosdao.getPracticalcutoffmarks())) {
            jsonObj.append("status", "greator");
        } else {
            jsonObj.append("status", "smaller");
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/checkMaximumMarks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String checkMaximumMarks(@RequestParam("qpackid") String qpackid,
            @RequestParam("maximummarks") String maximummarks
    ) {

        int totalpcmarks = 0;
        JSONObject jsonObj = new JSONObject();
        QualificationPackDAO qpack = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));
        Map param = new HashMap();
        param.put("qpackid", new Integer(qpackid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCDAO pcdao = (PCDAO) itr.next();
                totalpcmarks = totalpcmarks + new Integer(pcdao.getOverallcutoffmarks());
            }
        }
        System.out.println("working...");
        if ((new Integer(maximummarks) + totalpcmarks) > new Integer(qpack.getTotalmarks())) {
            jsonObj.append("status", "yes");
            System.out.println("Yes");
        } else {
            jsonObj.append("status", "no");
            System.out.println("no");
        }

        return jsonObj.toString();
    }

    @RequestMapping(value = "/getQPcakDetails", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQPcakDetails(@RequestParam("qp_id") String qpid
    ) {

        System.out.println("QP ID::" + qpid);
        JSONArray jsonarr = new JSONArray();
        QualificationPackDAO qpack = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpid));
        JSONObject jsonObj = new JSONObject();
        jsonObj.append("ID", qpack.getQpid());
        jsonObj.append("QpackID", qpack.getQpackid());
        jsonObj.append("QpackName", qpack.getQpackname());
        jsonObj.append("QpackLevel", qpack.getQpacklevel());
        jsonObj.append("Totaltheorymarks", qpack.getTheorycutoffmarks());
        jsonObj.append("Totalpracticalmarks", qpack.getTotalpracticalmarks());
        jsonObj.append("Totalmarks", qpack.getTotalmarks());
        jsonObj.append("Theorycutoffmarks", qpack.getTheorycutoffmarks());
        jsonObj.append("Practicalcutoffmarks", qpack.getPracticalcutoffmarks());
        jsonObj.append("Overallcutoffmarks", qpack.getOverallcutoffmarks());
        jsonObj.append("Weightedavgmarks", qpack.getWeightedavgmarks());
        System.out.println("Getting data " + qpack.getQpacklevel());

        JSONArray nosjsonarray = getNOSByID(qpid);
        JSONArray pcjsonarray = getAllPC();

        jsonarr.put(jsonObj);
        jsonarr.put(nosjsonarray);
        jsonarr.put(pcjsonarray);
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

    public JSONArray getNOSByID(String qpid) {

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
                    jsonObj.append("NOSID", data.getNosid());
                    jsonObj.append("NOSNAME", data.getNosname());
                    jsonObj.append("Theorycutoffmarks", data.getTheorycutoffmarks());
                    jsonObj.append("Practicalcutoffmarks", data.getPracticalcutoffmarks());
                    jsonObj.append("Overallcutoffmarks", data.getOverallcutoffmarks());
                    jsonObj.append("Weightedavgmarks", data.getWeightedavgmarks());
                    jsonarr.put(jsonObj);
                }

                jsonObj = new JSONObject();
            }
        }

        return jsonarr;
    }

    public JSONArray getAllPC() {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();

        List<SuperBean> records = this.superService.listAllObjects(new PCDAO());
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCDAO data = (PCDAO) itr.next();

                jsonObj.append("ID", data.getPcID());
                jsonObj.append("PCID", data.getPcid());
                jsonObj.append("PCNAME", data.getPcname());
                jsonObj.append("Theorycutoffmarks", data.getTheorycutoffmarks());
                jsonObj.append("Practicalcutoffmarks", data.getPracticalcutoffmarks());
                jsonObj.append("Overallcutoffmarks", data.getOverallcutoffmarks());
                jsonObj.append("NOSID", data.getNosid());

                jsonarr.put(jsonObj);

                jsonObj = new JSONObject();
            }
        }

        return jsonarr;
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

    public Map<Integer, String> getQualificationPacks(int ssc_id) {

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

}
