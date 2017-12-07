/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

import com.abp.admin.generateqp.QuestionPaperDAO;
import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class QuestionPaperDecorator extends TableDecorator {

    public String getActions() {
        QuestionPaperDAO actorData = (QuestionPaperDAO) getCurrentRowObject();
        String action;
        action = "<a href=\"#\" onclick=\"deleteQuestionPaper("+actorData.getActions()+");\"\">Delete</a>";
        return action;
    }
}
