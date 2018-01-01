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
public class QuestionBankAnalysisDAO {
    
    private String questionid;
    private String question;
    private String candidateattempted;
    private String correctattempt;
    private String incorrectattempt;
    private String notattempt;
    
    private String sscid;
    private String qpid;
    private String month;

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

    public String getCandidateattempted() {
        return candidateattempted;
    }

    public void setCandidateattempted(String candidateattempted) {
        this.candidateattempted = candidateattempted;
    }

    public String getCorrectattempt() {
        return correctattempt;
    }

    public void setCorrectattempt(String correctattempt) {
        this.correctattempt = correctattempt;
    }

    public String getIncorrectattempt() {
        return incorrectattempt;
    }

    public void setIncorrectattempt(String incorrectattempt) {
        this.incorrectattempt = incorrectattempt;
    }

    public String getNotattempt() {
        return notattempt;
    }

    public void setNotattempt(String notattempt) {
        this.notattempt = notattempt;
    }

    public String getSscid() {
        return sscid;
    }

    public void setSscid(String sscid) {
        this.sscid = sscid;
    }

    public String getQpid() {
        return qpid;
    }

    public void setQpid(String qpid) {
        this.qpid = qpid;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    
    
}
