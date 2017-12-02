/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.generateqp;

import com.abp.admin.project.questions.QuestionDAO;
import com.abp.admin.project.theorymmq.TheoryMMQDAO;
import com.abp.admin.project.theorymmq.TheoryPCIDWithMarks;
import com.abp.admin.qualificationpack.PCDAO;
import com.abp.admin.qualificationpack.QualificationPackDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
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
@RequestMapping("/admin/generateqp")
public class GenerateQPController {

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
        return "/common";
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
//            PCWiseQuestionDAO pcwisedao = new PCWiseQuestionDAO();
//            PCDAO PCDAO = (PCDAO) this.superService.getObjectById(new PCDAO(), new Integer(recid));
//            pcwisedao.setPcid(new Integer(recid));
//            pcwisedao.setPcids(PCDAO.getPcid());
//            pcwisedao.setPcname(PCDAO.getPcname());
//            pcwisedao.setTheorymarks(PCDAO.getTheorycutoffmarks());
//            model.addAttribute("pcwisequestion", pcwisedao);
//            model.addAttribute("mode", "add");
        }

        request.getSession().setAttribute("body", "/admin/generateqp/pcwisequestion.jsp");
        return "/commonmodal";
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

        QualificationPackDAO beanObj = (QualificationPackDAO) this.superService.getObjectById(new QualificationPackDAO(), new Integer(qpackid));

        JSONArray jsonarrsend = new JSONArray();
        JSONArray jsonarr = getAllPCByQPackID(new Integer(qpackid));
        JSONArray questions = getAllQuestions(new Integer(qpackid));

        jsonarrsend.put(questions);
        jsonarrsend.put(jsonarr);

        JSONObject jsonObj = new JSONObject();
        jsonObj.append("totaltheorymarksqp", beanObj.getTotaltheorymarks());
        jsonarrsend.put(jsonObj);

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
        }
        return pcidwithmarks;
    }

}
