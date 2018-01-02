/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.questionbankanalysis;

/**
 *
 * @author ss
 */
public class DisplayQuestionBank {
    
    private String questionid;
    private String question;
    private String incorrectattempt;
    private String correctatmpt;
    private String notattempt;
    private String noofattempt;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIncorrectattempt() {
        return incorrectattempt;
    }

    public void setIncorrectattempt(String incorrectattempt) {
        this.incorrectattempt = incorrectattempt;
    }

    

    public String getCorrectatmpt() {
        return correctatmpt;
    }

    public void setCorrectatmpt(String correctatmpt) {
        this.correctatmpt = correctatmpt;
    }

    public String getNotattempt() {
        return notattempt;
    }

    public void setNotattempt(String notattempt) {
        this.notattempt = notattempt;
    }

    public String getNoofattempt() {
        return noofattempt;
    }

    public void setNoofattempt(String noofattempt) {
        this.noofattempt = noofattempt;
    }
    
    
    
}
