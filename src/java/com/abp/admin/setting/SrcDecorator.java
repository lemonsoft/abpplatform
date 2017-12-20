/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.setting;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getEnablelogin() {
        SettingDAO actorData = (SettingDAO) getCurrentRowObject();
        String action;
        if (actorData.getEnablelogin().equalsIgnoreCase("enable")) {
            action = "<a href=\"initChange.io?recid=" + actorData.getRecordid() + "&status=" + actorData.getEnablelogin() + "&username="+actorData.getStudentname()+"\"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/Tick-Box.png\" width=20 height=20/></a>";
        } else {
            action = "<a href=\"initChange.io?recid=" + actorData.getRecordid() + "&status=" + actorData.getEnablelogin() +  "&username="+actorData.getStudentname()+"\"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/wrong.png\" width=20 height=20/></a>";
        }

        return action;
    }

}
