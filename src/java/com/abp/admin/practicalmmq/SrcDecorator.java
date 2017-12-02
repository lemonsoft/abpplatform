/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.practicalmmq;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getActions() {
        SenarioQuestionDAO actorData = (SenarioQuestionDAO) getCurrentRowObject();
        String action;
        action = "<button type=\"button\" class=\"button btn-blue\"  onclick=\"editMapping('" + actorData.getId() + "');\">Edit Mapping</button>";
        return action;
    }
}
