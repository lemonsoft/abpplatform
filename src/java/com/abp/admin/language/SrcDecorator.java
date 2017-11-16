/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.language;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getActions() {
        LanguageDAO actorData = (LanguageDAO) getCurrentRowObject();
        String action;
        action = "<a href=\"initUpdate.io?recid=" + actorData.getId() + "\">Edit</a>|<a href=\"delete.io?recid=" + actorData.getId() + "\">Delete</a>";
        return action;
    }
}
