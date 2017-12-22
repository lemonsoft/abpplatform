/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.assessor.practicalexam;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getOptions() {
        DisplayQuestion actorData = (DisplayQuestion) getCurrentRowObject();
        String action = "";
        String option = actorData.getOptions();
        System.out.println(" option "+option);
        if (option.equalsIgnoreCase("yes")) {
            System.out.println(" Inside Yes");
            action = "<input type=\"radio\" name=\"" + actorData.getQuestionid() + "\" id=\"" + actorData.getQuestionid() + "\" value=\"yes\" checked>Yes";
        } else {
            action = "<input type=\"radio\" name=\"" + actorData.getQuestionid() + "\" id=\"" + actorData.getQuestionid() + "\" value=\"yes\">Yes";
        }
        if (option.equalsIgnoreCase("no")) {
            System.out.println(" Inside No");
            action =action+ "&nbsp;&nbsp;<input type=\"radio\" name=\"" + actorData.getQuestionid() + "\" id=\"" + actorData.getQuestionid() + "\" value=\"no\" checked>No";
        } else {
            action =action+ "&nbsp;&nbsp;<input type=\"radio\" name=\"" + actorData.getQuestionid() + "\" id=\"" + actorData.getQuestionid() + "\" value=\"no\">No";
        }
        //action = "<input type=\"radio\" name=\""+actorData.getQuestionid()+"\" id=\""+actorData.getQuestionid()+"\" value=\"yes\">Yes &nbsp;&nbsp;<input type=\"radio\" name=\""+actorData.getQuestionid()+"\" id=\""+actorData.getQuestionid()+"\" value=\"no\">No";
        return action;
    }

}
