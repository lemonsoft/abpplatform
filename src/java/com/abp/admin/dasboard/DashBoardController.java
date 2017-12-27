/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.dasboard;

import com.abp.admin.assessor.AssessorDAO;
import com.abp.admin.batches.BatchesDAO;
import com.abp.admin.ssc.SSCDAO;
import com.abp.superdao.SuperBean;
import com.abp.superservice.SuperService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ss
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashBoardController {
    
    private SuperService superService;

    @Autowired(required = true)
    @Qualifier(value = "superService")
    public void setSuperService(SuperService superService) {
        this.superService = superService;
    }
    
    @RequestMapping(value="/init", method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("admin/common");
        
        model.addObject("totalSectors", totalSectors());
        model.addObject("totalassessor", totalassessor());
        model.addObject("totalbatches", totalbatches());
        
        request.getSession().setAttribute("body", "/admin/dashboard/dashboard.jsp");
        return model;
    }
    
    private int totalSectors(){
        
        List<SuperBean> allsectors=this.superService.listAllObjects(new SSCDAO());
        
        return allsectors.size();
    }
    private int totalassessor(){
        
        List<SuperBean> allassessor=this.superService.listAllObjects(new AssessorDAO());
        
        return allassessor.size();
    }
    private int totalbatches(){
        
        List<SuperBean> allbatches=this.superService.listAllObjects(new BatchesDAO());
        
        return allbatches.size();
    }
}
