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
public class AllQuestionPaperDecorator extends TableDecorator {

    public String getQuestionpaperid() {
        QuestionPaperDAO actorData = (QuestionPaperDAO) getCurrentRowObject();
        String action;
        action = "<input type=\"radio\" id=\"assessorid\" name=\"ID\" value=\""+actorData.getQuestionpaperid()+"\" /> "+actorData.getQuestionpaperid();
        return action;
    }
}
