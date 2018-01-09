/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batchanalysis;

import com.abp.admin.batches.UserDAO;
import com.abp.admin.result.UserResultDetailDAO;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/batchanalysis")
public class BatchAnalysisController {

    private static final Logger logger = Logger.getLogger(BatchAnalysisController.class);
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

        model.addAttribute("batchanalysisdao", new BatchAnalysisDAO());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/batchanalysis/batchanalysis.jsp");
        return "admin/common";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, BatchAnalysisDAO beanObj, Model model) {

        System.out.println("Get Batch Id : :  " + beanObj.getBatchid());

        int i = 1;
        ArrayList alldata=new ArrayList();
        Map usersparam = new HashMap();
        usersparam.put("batchid", new Integer(beanObj.getBatchid()));
        List<SuperBean> recordsusr = this.superService.listAllObjectsByCriteria(new UserDAO(), usersparam);
        if (recordsusr.size() > 0) {
            Iterator itrusr = recordsusr.iterator();
            while (itrusr.hasNext()) {
                BatchAnalysisDAO batchdao = new BatchAnalysisDAO();
                UserDAO datausr = (UserDAO) itrusr.next();
                batchdao.setSrno(""+i);
                batchdao.setCandidatename(datausr.getTraineename());
                batchdao.setEnrollmentno("" + datausr.getEnrollmentno());
                if (checkAttendance(datausr.getID(), new Integer(beanObj.getBatchid()))) {
                    batchdao.setAttendance("Present");
                    batchdao.setTheorymarks("--");
                    batchdao.setVivamarks("--");
                    batchdao.setNoswisemarks("--");
                    batchdao.setNoofquestionattempted("--");
                    batchdao.setNoofincorrectanswer("--");
                    batchdao.setNoofcorrectanswer("--");
                    batchdao.setTotaltimetaken("--");
                    batchdao.setResult("--");
                }else{
                    batchdao.setAttendance("Absent");
                    batchdao.setTheorymarks("--");
                    batchdao.setVivamarks("--");
                    batchdao.setNoswisemarks("--");
                    batchdao.setNoofquestionattempted("--");
                    batchdao.setNoofincorrectanswer("--");
                    batchdao.setNoofcorrectanswer("--");
                    batchdao.setTotaltimetaken("--");
                    batchdao.setResult("--");
                    
                }
                alldata.add(batchdao);
                i++;
            }
        }
        System.out.println(alldata);
        model.addAttribute("records", alldata);
        model.addAttribute("batchanalysisdao", new BatchAnalysisDAO());
        model.addAttribute("mode", "add");
        model.addAttribute("action", "search.io");
        request.getSession().setAttribute("body", "/admin/batchanalysis/batchanalysis.jsp");
        return "admin/common";
    }

    private boolean checkAttendance(int userid, int batchid) {

        boolean flag = false;
        Map usersparam = new HashMap();
        usersparam.put("userid", userid);
        usersparam.put("batchid", batchid);
        List<SuperBean> recordsusr = this.superService.listAllObjectsByCriteria(new UserResultDetailDAO(), usersparam);
        if (recordsusr.size() > 0) {
            flag = true;
        }

        return flag;

    }

}
