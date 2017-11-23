/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

import com.abp.admin.assessor.AssessorDAO;
import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class AssessorDecorator extends TableDecorator {

    public String getActions() {
        AssessorDAO actorData = (AssessorDAO) getCurrentRowObject();
        String action;
        action = "<a href=\"deleteAssessor.io?recid=" + actorData.getActions()+ "\">Delete</a>";
        return action;
    }
}
