/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.statedistrict;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getActions() {
        DistrictDAO actorData = (DistrictDAO) getCurrentRowObject();
        String action;
        action = "<a href=\"initUpdate.io?recid=" + actorData.getDistrictID()+ "\">Edit</a>|<a href=\"delete.io?recid=" + actorData.getDistrictID()+ "\">Delete</a>";
        return action;
    }
}
