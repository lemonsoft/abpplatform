/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batchanalysis;

/**
 *
 * @author ss
 */
public class BatchAnalysisDAO {
    
    private String srno;
    private String candidatename;
    private String enrollmentno;
    private String attendance;
    private String theorymarks;
    private String vivamarks;
    private String noswisemarks;
    private String noofquestionattempted;
    private String noofincorrectanswer;
    private String noofcorrectanswer;
    private String totaltimetaken;
    private String result;
    
    private String batchid;

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getCandidatename() {
        return candidatename;
    }

    public void setCandidatename(String candidatename) {
        this.candidatename = candidatename;
    }

    public String getEnrollmentno() {
        return enrollmentno;
    }

    public void setEnrollmentno(String enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getTheorymarks() {
        return theorymarks;
    }

    public void setTheorymarks(String theorymarks) {
        this.theorymarks = theorymarks;
    }

    public String getVivamarks() {
        return vivamarks;
    }

    public void setVivamarks(String vivamarks) {
        this.vivamarks = vivamarks;
    }

    public String getNoswisemarks() {
        return noswisemarks;
    }

    public void setNoswisemarks(String noswisemarks) {
        this.noswisemarks = noswisemarks;
    }

    public String getNoofquestionattempted() {
        return noofquestionattempted;
    }

    public void setNoofquestionattempted(String noofquestionattempted) {
        this.noofquestionattempted = noofquestionattempted;
    }

    public String getNoofincorrectanswer() {
        return noofincorrectanswer;
    }

    public void setNoofincorrectanswer(String noofincorrectanswer) {
        this.noofincorrectanswer = noofincorrectanswer;
    }

    public String getNoofcorrectanswer() {
        return noofcorrectanswer;
    }

    public void setNoofcorrectanswer(String noofcorrectanswer) {
        this.noofcorrectanswer = noofcorrectanswer;
    }

    public String getTotaltimetaken() {
        return totaltimetaken;
    }

    public void setTotaltimetaken(String totaltimetaken) {
        this.totaltimetaken = totaltimetaken;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    
    
    
}
