/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.ssc;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getActions() {
        SSCDAO actorData = (SSCDAO) getCurrentRowObject();
        String action;
        action = "<a href=\"initUpdate.io?recid=" + actorData.getSscId() + "\">Edit</a>|<a href=\"delete.io?recid=" + actorData.getSscId() + "\">Delete</a>";
        return action;
    }
}
