/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.assessor;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getActions() {
        AssessorDAO actorData = (AssessorDAO) getCurrentRowObject();
        String action;
        action = "<a href=\"initUpdate.io?recid=" + actorData.getAssessorid() + "\">Edit</a>|<a href=\"delete.io?recid=" + actorData.getAssessorid() + "\">Delete</a>|<a href=\"download.io?recid=" + actorData.getAssessorid() + "\">Resume</a>";
        return action;
    }

    public String getPhotoname() {
        AssessorDAO actorData = (AssessorDAO) getCurrentRowObject();
        String photo = "<img src=\"http://localhost:8084/ABP-Ver1/uploaded/assessor/"+actorData.getPhotoname()+"\" width=50 height=50 />";
        return photo;
    }
}
