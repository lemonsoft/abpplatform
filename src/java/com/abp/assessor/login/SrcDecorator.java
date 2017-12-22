/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.assessor.login;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getAttendance() {
        DisplayBatches actorData = (DisplayBatches) getCurrentRowObject();
        String action;
        action = "<button onclick=\"attendance("+actorData.getBatchID()+");\" class=\"button btn-blue\">Attendance</button>";
        return action;
    }

}
