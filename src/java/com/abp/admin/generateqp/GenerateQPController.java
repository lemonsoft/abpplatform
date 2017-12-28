/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.generateqp;

import com.abp.admin.practicalmmq.PCWithMarksDAO;
import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.project.theorymmq.TheoryMMQDAO;
import com.abp.admin.project.theorymmq.TheoryPCIDWithMarks;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/admin/generateqp")
public class GenerateQPController {

    private static final Logger logger = Logger.getLogger(GenerateQPController.class);
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

        model.addAttribute("generateqp", new GenerateQPDAO());

        model.addAttribute("ssc", getSectorSkillCouncil());

        model.addAttribute("action", "search.io");

        request.getSession().setAttribute("body", "/admin/generateqp/generateqp.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/pcwisequestion", method = RequestMethod.GET)
    public String pcwisequestion(HttpServletRequest request, HttpServletResponse response, Model model) {

        String recid = request.getParameter("pcid");

        Map<String, Integer> record = getAllMarksByPCID(new Integer(recid));
        model.addAttribute("marks1", record.get("marks1"));
        model.addAttribute("marks2", record.get("marks2"));
        model.addAttribute("marks3", record.get("marks3"));
        model.addAttribute("marks4", record.get("marks4"));
        model.addAttribute("marks5", record.get("marks5"));
        model.addAttribute("marks6", record.get("marks6"));
        model.addAttribute("marks7", record.get("marks7"));
        model.addAttribute("marks8", record.get("marks8"));
        model.addAttribute("marks9", record.get("marks9"));
        model.addAttribute("marks10", record.get("marks10"));
        model.addAttribute("marks11", record.get("marks11"));
        model.addAttribute("marks12", record.get("marks12"));
        model.addAttribute("marks13", record.get("marks13"));
        model.addAttribute("marks14", record.get("marks14"));
        model.addAttribute("marks15", record.get("marks15"));
        model.addAttribute("marks16", record.get("marks16"));
        model.addAttribute("marks17", record.get("marks17"));
        model.addAttribute("marks18", record.get("marks18"));
        model.addAttribute("marks19", record.get("marks19"));
        model.addAttribute("marks20", record.get("marks20"));
        model.addAttribute("marks21", record.get("marks21"));
        model.addAttribute("marks22", record.get("marks22"));
        model.addAttribute("marks23", record.get("marks23"));
        model.addAttribute("marks24", record.get("marks24"));
        model.addAttribute("marks25", record.get("marks25"));
        try {
            Map param = new HashMap();
            param.put("pcid", new Integer(recid));
            List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWiseQuestionDAO(), param);
            if (records.size() > 0) {
                PCWiseQuestionDAO pcwisedao = (PCWiseQuestionDAO) records.get(0);
                PCDAO PCDAO = (PCDAO) this.superService.getObjectById(new PCDAO(), new Integer(recid));
                //pcwisedao.setPcid(new Integer(recid));
                pcwisedao.setPcids(PCDAO.getPcid());
                pcwisedao.setPcname(PCDAO.getPcname());
                pcwisedao.setTheorymarks(PCDAO.getTheorycutoffmarks());
                model.addAttribute("pcwisequestion", pcwisedao);
                model.addAttribute("mode", "update");
            } else {
                PCWiseQuestionDAO pcwisedao = new PCWiseQuestionDAO();
                PCDAO PCDAO = (PCDAO) this.superService.getObjectById(new PCDAO(), new Integer(recid));
                pcwisedao.setPcid(new Integer(recid));
                pcwisedao.setPcids(PCDAO.getPcid());
                pcwisedao.setPcname(PCDAO.getPcname());
                pcwisedao.setTheorymarks(PCDAO.getTheorycutoffmarks());
                model.addAttribute("pcwisequestion", pcwisedao);
                model.addAttribute("mode", "add");

            }

        } catch (Exception e) {
            logger.error("This is Error message", e);
        }

        request.getSession().setAttribute("body", "/admin/generateqp/pcwisequestion.jsp");
        return "admin/commonmodal";
    }

    /*
       Jquery Methods Calling from Pages
     */
    @RequestMapping(value = "/getQP", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getQP(@RequestParam("ssc_id") String sscid) {

        System.out.println("SSC ID::" + sscid);
        String districts = getQualificationPackByID(sscid);

        return districts;
    }

    @RequestMapping(value = "/getAllPCByQPID", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getAllPCByQPID(@RequestParam("qpackid") String qpackid) {

        JSONArray jsonarrsend = new JSONArray();
        try {
            QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));

            JSONArray jsonarr = getAllPCByQPackID(new Integer(qpackid));
            JSONArray questions = getAllQuestions(new Integer(qpackid));

            jsonarrsend.put(questions);
            jsonarrsend.put(jsonarr);

            JSONObject jsonObj = new JSONObject();
            jsonObj.append("totaltheorymarksqp", beanObj.getTotaltheorymarks());

            boolean flag = checkAllMarksEqualtoQp(new Integer(qpackid));
            jsonObj.append("generate", flag);
            jsonarrsend.put(jsonObj);
        } catch (Exception e) {
            logger.error("This is Error message", e);
        }
        return jsonarrsend.toString();
    }

    @RequestMapping(value = "/addPcwiseQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String addPcwiseQuestion(@RequestParam("pcid") String pcid, @RequestParam("pcids") String pcids, @RequestParam("pcname") String pcname, @RequestParam("theorymarks") String theorymarks, @RequestParam("totaladdedmarks") String totaladdedmarks, @RequestParam("marks1") String marks1, @RequestParam("marks2") String marks2, @RequestParam("marks3") String marks3,
            @RequestParam("marks4") String marks4, @RequestParam("marks5") String marks5, @RequestParam("marks6") String marks6, @RequestParam("marks7") String marks7, @RequestParam("marks8") String marks8, @RequestParam("marks9") String marks9, @RequestParam("marks10") String marks10, @RequestParam("marks11") String marks11, @RequestParam("marks12") String marks12, @RequestParam("marks13") String marks13,
            @RequestParam("marks14") String marks14, @RequestParam("marks15") String marks15, @RequestParam("marks16") String marks16, @RequestParam("marks17") String marks17, @RequestParam("marks18") String marks18, @RequestParam("marks19") String marks19, @RequestParam("marks20") String marks20, @RequestParam("marks21") String marks21, @RequestParam("marks22") String marks22, @RequestParam("marks23") String marks23,
            @RequestParam("marks24") String marks24, @RequestParam("marks25") String marks25) {

        JSONObject jsonObj = new JSONObject();
        PCWiseQuestionDAO pcwisedao = new PCWiseQuestionDAO();
        pcwisedao.setPcid(new Integer(pcid));
        pcwisedao.setPcids(pcids);
        pcwisedao.setPcname(pcname);
        pcwisedao.setTheorymarks(theorymarks);
        pcwisedao.setTotaladdedmarks(new Integer(totaladdedmarks));
        pcwisedao.setMarks1(new Integer(marks1));
        pcwisedao.setMarks2(new Integer(marks2));
        pcwisedao.setMarks3(new Integer(marks3));
        pcwisedao.setMarks4(new Integer(marks4));
        pcwisedao.setMarks5(new Integer(marks5));
        pcwisedao.setMarks6(new Integer(marks6));
        pcwisedao.setMarks7(new Integer(marks7));
        pcwisedao.setMarks8(new Integer(marks8));
        pcwisedao.setMarks9(new Integer(marks9));
        pcwisedao.setMarks10(new Integer(marks10));
        pcwisedao.setMarks11(new Integer(marks11));
        pcwisedao.setMarks12(new Integer(marks12));
        pcwisedao.setMarks13(new Integer(marks13));
        pcwisedao.setMarks14(new Integer(marks14));
        pcwisedao.setMarks15(new Integer(marks15));
        pcwisedao.setMarks16(new Integer(marks16));
        pcwisedao.setMarks17(new Integer(marks17));
        pcwisedao.setMarks18(new Integer(marks18));
        pcwisedao.setMarks19(new Integer(marks19));
        pcwisedao.setMarks20(new Integer(marks20));
        pcwisedao.setMarks21(new Integer(marks21));
        pcwisedao.setMarks22(new Integer(marks22));
        pcwisedao.setMarks23(new Integer(marks23));
        pcwisedao.setMarks24(new Integer(marks24));
        pcwisedao.setMarks25(new Integer(marks25));

        try {
            this.superService.saveObject(pcwisedao);
            jsonObj.append("status", "save");
        } catch (Exception e) {
            jsonObj.append("status", "fail");
            logger.error("This is Error message", e);

        }
        return jsonObj.toString();
    }

    @RequestMapping(value = "/updatePcwiseQuestion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String updatePcwiseQuestion(@RequestParam("id") String id, @RequestParam("pcid") String pcid, @RequestParam("pcids") String pcids, @RequestParam("pcname") String pcname, @RequestParam("theorymarks") String theorymarks, @RequestParam("totaladdedmarks") String totaladdedmarks, @RequestParam("marks1") String marks1, @RequestParam("marks2") String marks2, @RequestParam("marks3") String marks3,
            @RequestParam("marks4") String marks4, @RequestParam("marks5") String marks5, @RequestParam("marks6") String marks6, @RequestParam("marks7") String marks7, @RequestParam("marks8") String marks8, @RequestParam("marks9") String marks9, @RequestParam("marks10") String marks10, @RequestParam("marks11") String marks11, @RequestParam("marks12") String marks12, @RequestParam("marks13") String marks13,
            @RequestParam("marks14") String marks14, @RequestParam("marks15") String marks15, @RequestParam("marks16") String marks16, @RequestParam("marks17") String marks17, @RequestParam("marks18") String marks18, @RequestParam("marks19") String marks19, @RequestParam("marks20") String marks20, @RequestParam("marks21") String marks21, @RequestParam("marks22") String marks22, @RequestParam("marks23") String marks23,
            @RequestParam("marks24") String marks24, @RequestParam("marks25") String marks25) {

        JSONObject jsonObj = new JSONObject();
        PCWiseQuestionDAO pcwisedao = (PCWiseQuestionDAO) this.superService.getObjectById(new PCWiseQuestionDAO(), new Integer(id));
        //PCWiseQuestionDAO pcwisedao = new PCWiseQuestionDAO();
        pcwisedao.setPcid(new Integer(pcid));
        pcwisedao.setPcids(pcids);
        pcwisedao.setPcname(pcname);
        pcwisedao.setTheorymarks(theorymarks);
        pcwisedao.setTotaladdedmarks(new Integer(totaladdedmarks));
        pcwisedao.setMarks1(new Integer(marks1));
        pcwisedao.setMarks2(new Integer(marks2));
        pcwisedao.setMarks3(new Integer(marks3));
        pcwisedao.setMarks4(new Integer(marks4));
        pcwisedao.setMarks5(new Integer(marks5));
        pcwisedao.setMarks6(new Integer(marks6));
        pcwisedao.setMarks7(new Integer(marks7));
        pcwisedao.setMarks8(new Integer(marks8));
        pcwisedao.setMarks9(new Integer(marks9));
        pcwisedao.setMarks10(new Integer(marks10));
        pcwisedao.setMarks11(new Integer(marks11));
        pcwisedao.setMarks12(new Integer(marks12));
        pcwisedao.setMarks13(new Integer(marks13));
        pcwisedao.setMarks14(new Integer(marks14));
        pcwisedao.setMarks15(new Integer(marks15));
        pcwisedao.setMarks16(new Integer(marks16));
        pcwisedao.setMarks17(new Integer(marks17));
        pcwisedao.setMarks18(new Integer(marks18));
        pcwisedao.setMarks19(new Integer(marks19));
        pcwisedao.setMarks20(new Integer(marks20));
        pcwisedao.setMarks21(new Integer(marks21));
        pcwisedao.setMarks22(new Integer(marks22));
        pcwisedao.setMarks23(new Integer(marks23));
        pcwisedao.setMarks24(new Integer(marks24));
        pcwisedao.setMarks25(new Integer(marks25));

        try {
            this.superService.updateObject(pcwisedao);
            jsonObj.append("status", "save");
        } catch (Exception e) {
            jsonObj.append("status", "fail");
            logger.error("This is Error message", e);

        }
        return jsonObj.toString();
    }

    @RequestMapping(value = "/openquestionpaper", method = RequestMethod.GET)
    public String openquestionpaper(HttpServletRequest request, HttpServletResponse response, Model model) {

        String qpackid = request.getParameter("qpackid");
        QuestionPaperDAO qpaper = new QuestionPaperDAO();
        qpaper.setQpackid(new Integer(qpackid));

        model.addAttribute("qpaper", qpaper);

        model.addAttribute("action", "add.io");

        model.addAttribute("mode", "add");

        request.getSession().setAttribute("body", "/admin/generateqp/questionpaper.jsp");
        return "admin/commonmodal";
    }

    @RequestMapping(value = "/generateQuestionPaper", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String generateQuestionPaper(@RequestParam("qpackid") String qpackid, @RequestParam("questionpapername") String questionpapername, @RequestParam("totaltime") String totaltime, @RequestParam("israndom") String israndom, @RequestParam("isoptionrandom") String isoptionrandom, @RequestParam("isactive") String isactive) {

        JSONObject jsonObj = new JSONObject();
        QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));
        String questionids = "";
        String theorymmqids = "";
        String practicalids = "";
        boolean flag = true;
        Map param = new HashMap();
        param.put("qpackid", new Integer(qpackid));
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWiseQuestionDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCWiseQuestionDAO pcwisequestion = (PCWiseQuestionDAO) itr.next();
                if (pcwisequestion.getMarks1() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks1(), 1);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 1");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks2() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks2(), 2);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 2");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks3() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks3(), 3);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 3");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks4() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks4(), 4);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 4");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks5() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks5(), 5);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 5");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks6() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks6(), 6);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 6");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks7() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks7(), 7);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 7");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks8() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks8(), 8);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 8");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks9() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks9(), 9);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 9");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks10() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks10(), 10);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 10");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks11() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks11(), 11);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 11");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks12() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks12(), 12);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 12");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks13() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks13(), 13);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 13");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks14() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks14(), 14);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 14");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks15() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks15(), 15);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 15");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks16() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks16(), 16);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 16");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks17() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks17(), 17);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 17");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks18() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks18(), 18);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 18");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks19() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks19(), 19);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 19");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks20() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks20(), 20);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 20");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks21() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks21(), 21);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 21");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks22() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks22(), 22);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 22");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks23() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks23(), 23);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 23");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks24() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks24(), 24);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 24");
                    } else {
                        questionids = questionids + ids;
                    }
                }
                if (pcwisequestion.getMarks25() > 0) {
                    String ids = getQuestionIDsByPCID(pcwisequestion.getPcid(), pcwisequestion.getMarks25(), 25);
                    if (ids.equals("insufficient")) {
                        flag = false;
                        PCDAO pcbeanObj = (PCDAO) this.superService.getObjectById(new PCDAO(), pcwisequestion.getPcid());
                        jsonObj.append("error", "Question Paper not created due to insufficient Questions in " + pcbeanObj.getPcid() + " for marks 25");
                    } else {
                        questionids = questionids + ids;
                    }
                }

            }
            System.out.println(" questionids  " + questionids);

            Map param2 = new HashMap();
            param2.put("qpackid", new Integer(qpackid));
            List<SuperBean> recordspc = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
            {
                if (recordspc.size() > 0) {
                    Iterator itrpc = recordspc.iterator();
                    while (itrpc.hasNext()) {
                        PCDAO pcdao = (PCDAO) itrpc.next();
                        theorymmqids = theorymmqids + getTheoryMMQuestion(pcdao.getPcID());
                        practicalids = practicalids + getPracticalMMQuestion(pcdao.getPcID());
                    }

                }

            }
            System.out.println(" theorymmqids  " + theorymmqids);
            System.out.println(" practicalids  " + practicalids);

            if (flag) {
                QuestionPaperDAO questionpaper = new QuestionPaperDAO();
                System.out.println(" qpackid  " + qpackid);
                System.out.println(" questionpapername  " + questionpapername);
                System.out.println(" totaltime  " + totaltime);
                System.out.println(" israndom  " + israndom);
                System.out.println(" isoptionrandom  " + isoptionrandom);
                System.out.println(" isactive  " + isactive);
                questionids = questionids.substring(0, questionids.length() - 1);
                theorymmqids = theorymmqids.substring(0, theorymmqids.length() - 1);
                practicalids = practicalids.substring(0, practicalids.length() - 1);
                System.out.println(" questionids  " + questionids);
                System.out.println(" theorymmqids  " + theorymmqids);
                System.out.println(" practicalids  " + practicalids);

                questionpaper.setQpackid(new Integer(qpackid));
                questionpaper.setQuestionpapername(questionpapername);
                questionpaper.setTotaltime(new Integer(totaltime));
                questionpaper.setTotalmarks(beanObj.getTotalmarks());
                questionpaper.setIsrandom(israndom);
                questionpaper.setIsoptionrandom(isoptionrandom);
                questionpaper.setIsactive(isactive);
                questionpaper.setQuestionids(questionids);
                questionpaper.setTheorymmqids(theorymmqids);
                questionpaper.setPracticalmmqids(practicalids);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date now = new Date();
                String strDate = sdf.format(now);

                questionpaper.setCreateddatetime(strDate);

                try {
                    this.superService.saveObject(questionpaper);
                    jsonObj.append("status", "Question Paper created.");
                } catch (Exception e) {
                    logger.error("This is Error message", e);
                    jsonObj.append("status", "Error in creating Question Paper.");
                }

            }
        }

        return jsonObj.toString();

    }

    @RequestMapping(value = "/createQuestionPaper", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String createQuestionPaper() {

        return "";
    }

    public String getQuestionIDsByPCID(int pcid, int number, int marks) {

        String questionids = "";
        Map param = new HashMap();
        param.put("pcid", pcid);
        param.put("marks", marks);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
        if (records.size() >= number) {

            for (int i = 0; i < number; i++) {
                QuestionDAO question = (QuestionDAO) records.get(i);
                questionids = questionids + "" + question.getId() + ",";
            }

        } else {

            questionids = "insufficient";
        }

        System.out.println("Marks " + marks + "     Number of question : " + number + "   Question ids  : " + questionids);
        return questionids;
    }

    public String getPracticalMMQuestion(int pcid) {

        String practicalmmqids = "";
        Map param = new HashMap();
        param.put("pcid", pcid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCWithMarksDAO pcwithmarkdao = (PCWithMarksDAO) itr.next();
                practicalmmqids = practicalmmqids + pcwithmarkdao.getQuestion_id() + ",";
            }

        }

        return practicalmmqids;

    }

    public String getTheoryMMQuestion(int pcid) {

        String theorymmqids = "";
        Map param = new HashMap();
        param.put("pcid", pcid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new TheoryPCIDWithMarks(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                TheoryPCIDWithMarks pcwithmarkdao = (TheoryPCIDWithMarks) itr.next();
                theorymmqids = theorymmqids + pcwithmarkdao.getQuestion_id() + ",";
            }
            //theorymmqids=theorymmqids.substring(0, theorymmqids.length()-1);

        }

        return theorymmqids;

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

    public Map<String, Integer> getAllMarksByPCID(int pcid) {

        Map result = new HashMap();
        int marks1 = 0, marks2 = 0, marks3 = 0, marks4 = 0, marks5 = 0, marks6 = 0, marks7 = 0, marks8 = 0, marks9 = 0, marks10 = 0, marks11 = 0, marks12 = 0, marks13 = 0, marks14 = 0, marks15 = 0, marks16 = 0, marks17 = 0, marks18 = 0, marks19 = 0, marks20 = 0, marks21 = 0, marks22 = 0, marks23 = 0, marks24 = 0, marks25 = 0;
        Map param = new HashMap();
        param.put("pcid", pcid);
        param.put("isapproved", "Y");
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new QuestionDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                QuestionDAO data = (QuestionDAO) itr.next();
                System.out.println("   " + data.getMarks());
                if (data.getMarks() == 1) {
                    result.put("marks1", ++marks1);
                } else {
                    result.put("marks1", marks1);
                }
                if (data.getMarks() == 2) {
                    result.put("marks2", ++marks2);
                } else {
                    result.put("marks2", marks2);
                }
                if (data.getMarks() == 3) {
                    result.put("marks3", ++marks3);
                } else {
                    result.put("marks3", marks3);
                }
                if (data.getMarks() == 4) {
                    result.put("marks4", ++marks4);
                } else {
                    result.put("marks4", marks4);
                }
                if (data.getMarks() == 5) {
                    result.put("marks5", ++marks5);
                } else {
                    result.put("marks5", marks5);
                }
                if (data.getMarks() == 6) {
                    result.put("marks6", ++marks6);
                } else {
                    result.put("marks6", marks6);
                }
                if (data.getMarks() == 7) {
                    result.put("marks7", ++marks7);
                } else {
                    result.put("marks7", marks7);
                }
                if (data.getMarks() == 8) {
                    result.put("marks8", ++marks8);
                } else {
                    result.put("marks8", marks8);
                }
                if (data.getMarks() == 9) {
                    result.put("marks9", ++marks9);
                } else {
                    result.put("marks9", marks9);
                }
                if (data.getMarks() == 10) {
                    result.put("marks10", ++marks10);
                } else {
                    result.put("marks10", marks10);
                }
                if (data.getMarks() == 11) {
                    result.put("marks11", ++marks11);
                } else {
                    result.put("marks11", marks11);
                }
                if (data.getMarks() == 12) {
                    result.put("marks12", ++marks12);
                } else {
                    result.put("marks12", marks12);
                }
                if (data.getMarks() == 13) {
                    result.put("marks13", ++marks13);
                } else {
                    result.put("marks13", marks13);
                }
                if (data.getMarks() == 14) {
                    result.put("marks14", ++marks14);
                } else {
                    result.put("marks14", marks14);
                }
                if (data.getMarks() == 15) {
                    result.put("marks15", ++marks15);
                } else {
                    result.put("marks15", marks15);
                }
                if (data.getMarks() == 16) {
                    result.put("marks16", ++marks16);
                } else {
                    result.put("marks16", marks16);
                }
                if (data.getMarks() == 17) {
                    result.put("marks17", ++marks17);
                } else {
                    result.put("marks17", marks17);
                }
                if (data.getMarks() == 18) {
                    result.put("marks18", ++marks18);
                } else {
                    result.put("marks18", marks18);
                }
                if (data.getMarks() == 19) {
                    result.put("marks19", ++marks19);
                } else {
                    result.put("marks19", marks19);
                }
                if (data.getMarks() == 20) {
                    result.put("marks20", ++marks20);
                } else {
                    result.put("marks20", marks20);
                }
                if (data.getMarks() == 21) {
                    result.put("marks21", ++marks21);
                } else {
                    result.put("marks21", marks21);
                }
                if (data.getMarks() == 22) {
                    result.put("marks22", ++marks22);
                } else {
                    result.put("marks22", marks22);
                }
                if (data.getMarks() == 23) {
                    result.put("marks23", ++marks23);
                } else {
                    result.put("marks23", marks23);
                }
                if (data.getMarks() == 24) {
                    result.put("marks24", ++marks24);
                } else {
                    result.put("marks24", marks24);
                }
                if (data.getMarks() == 25) {
                    result.put("marks25", ++marks25);
                } else {
                    result.put("marks25", marks25);
                }

            }
        } else {
            result.put("marks1", marks1);
            result.put("marks2", marks2);
            result.put("marks3", marks3);
            result.put("marks4", marks4);
            result.put("marks5", marks5);
            result.put("marks6", marks6);
            result.put("marks7", marks7);
            result.put("marks8", marks8);
            result.put("marks9", marks9);
            result.put("marks10", marks10);
            result.put("marks11", marks11);
            result.put("marks12", marks12);
            result.put("marks13", marks13);
            result.put("marks14", marks14);
            result.put("marks15", marks15);
            result.put("marks16", marks16);
            result.put("marks17", marks17);
            result.put("marks18", marks18);
            result.put("marks19", marks19);
            result.put("marks20", marks20);
            result.put("marks21", marks21);
            result.put("marks22", marks22);
            result.put("marks23", marks23);
            result.put("marks24", marks24);
            result.put("marks25", marks25);

        }

        return result;

    }

    public JSONArray getAllPCByQPackID(int qpackid) {

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        Map param = new HashMap();
        param.put("qpackid", qpackid);
        List<SuperBean> records = this.superService.listAllObjectsByCriteria(new PCDAO(), param);
        if (records.size() > 0) {
            Iterator itr = records.iterator();
            while (itr.hasNext()) {
                PCDAO data = (PCDAO) itr.next();

                jsonObj.append("ID", data.getPcID());
                jsonObj.append("PCID", data.getPcid());
                jsonObj.append("PCNAME", data.getPcname());
                jsonObj.append("Theorymarks", data.getTheorycutoffmarks());

                jsonarr.put(jsonObj);

                jsonObj = new JSONObject();
            }
        }

        return jsonarr;
    }

    public JSONArray getAllQuestions(int qpid) {

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

        return jsonarr;
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
            pcidwithmarks = pcidwithmarks.substring(0, pcidwithmarks.length() - 1);
        }
        return pcidwithmarks;
    }

    public boolean checkAllMarksEqualtoQp(int qpackid) {

        boolean flag = false;
        int totaltheorymarks = 0;
        int practicalmarks = 0;
        QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), qpackid);

        Map paramnos = new HashMap();
        paramnos.put("qpackid", qpackid);
        List<SuperBean> recordspc = this.superService.listAllObjectsByCriteria(new PCDAO(), paramnos);
        if (recordspc.size() > 0) {
            Iterator itrpc = recordspc.iterator();
            while (itrpc.hasNext()) {
                PCDAO pcdata = (PCDAO) itrpc.next();

                Map paramtheory = new HashMap();
                paramtheory.put("pcid", pcdata.getPcID());
                List<SuperBean> theorymarksrecords = this.superService.listAllObjectsByCriteria(new PCWiseQuestionDAO(), paramtheory);
                PCWiseQuestionDAO theorymarks = (PCWiseQuestionDAO) theorymarksrecords.get(0);
                totaltheorymarks = totaltheorymarks + theorymarks.getTotaladdedmarks();

                Map parampractical = new HashMap();
                parampractical.put("pcid", pcdata.getPcID());
                List<SuperBean> recordspractical = this.superService.listAllObjectsByCriteria(new PCWithMarksDAO(), parampractical);
                if (recordspractical.size() > 0) {
                    Iterator itrpractical = recordspractical.iterator();
                    while (itrpractical.hasNext()) {
                        PCWithMarksDAO pcpractical = (PCWithMarksDAO) itrpractical.next();
                        practicalmarks = practicalmarks + pcpractical.getMarks();

                    }

                }
            }
        }
        System.out.println("Theory marks QP  :  " + beanObj.getTotaltheorymarks() + "   entered " + totaltheorymarks);
        if (new Integer(beanObj.getTotaltheorymarks()) == totaltheorymarks) {

            flag = true;
        } else {
            flag = false;
        }
        System.out.println("Practical marks QP  :  " + beanObj.getTotalpracticalmarks() + "   entered " + practicalmarks);
        if (new Integer(beanObj.getTotalpracticalmarks()) == practicalmarks) {

            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

}
