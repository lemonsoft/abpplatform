/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.project;


import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getActions() {
        ProjectDAO actorData = (ProjectDAO) getCurrentRowObject();
        String action;
        action = "<a href=\"initUpdate.io?recid=" + actorData.getID()+ "\">Edit</a>|<a href=\"delete.io?recid=" + actorData.getID()+ "\">Delete</a>";
        return action;
    }
    
}
