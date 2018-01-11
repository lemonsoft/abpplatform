/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.qbankanalysis;

/**
 *
 * @author ss
 */
public class QBankanalysisdao {
    
    private String sscid;
    private String jobrole;
    private String month;
    
    private String srno;
    private String questionid;
    private String question;
    private String noofcandidatewhoattempted;
    private String correctattempted;
    private String incorrectattempted;
    private String notattempted;

    public String getSscid() {
        return sscid;
    }

    public void setSscid(String sscid) {
        this.sscid = sscid;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

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

    public String getNoofcandidatewhoattempted() {
        return noofcandidatewhoattempted;
    }

    public void setNoofcandidatewhoattempted(String noofcandidatewhoattempted) {
        this.noofcandidatewhoattempted = noofcandidatewhoattempted;
    }

    public String getCorrectattempted() {
        return correctattempted;
    }

    public void setCorrectattempted(String correctattempted) {
        this.correctattempted = correctattempted;
    }

    public String getIncorrectattempted() {
        return incorrectattempted;
    }

    public void setIncorrectattempted(String incorrectattempted) {
        this.incorrectattempted = incorrectattempted;
    }

    public String getNotattempted() {
        return notattempted;
    }

    public void setNotattempted(String notattempted) {
        this.notattempted = notattempted;
    }
   
}
