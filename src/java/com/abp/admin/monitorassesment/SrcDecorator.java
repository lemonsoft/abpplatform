/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.monitorassesment;


import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getActions() {
        MonitorAssesmentDAO actorData = (MonitorAssesmentDAO) getCurrentRowObject();
        String action;
        action = "<button class=\"button btn-blue\" onclick=\"viewAssesment("+actorData.getBatchid()+");\">Select</button>";
        return action;
    }

}
