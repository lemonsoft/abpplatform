/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.jobroleanalysis;

/**
 *
 * @author ss
 */
public class JobRoleAnalysisDAO {
    
    private String fromdate;
    private String todate;
    private String jobrole;
    private String theorypass;
    private String theoryfail;
    private String vivapass;
    private String vivafail;

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public String getTheorypass() {
        return theorypass;
    }

    public void setTheorypass(String theorypass) {
        this.theorypass = theorypass;
    }

    public String getTheoryfail() {
        return theoryfail;
    }

    public void setTheoryfail(String theoryfail) {
        this.theoryfail = theoryfail;
    }

    public String getVivapass() {
        return vivapass;
    }

    public void setVivapass(String vivapass) {
        this.vivapass = vivapass;
    }

    public String getVivafail() {
        return vivafail;
    }

    public void setVivafail(String vivafail) {
        this.vivafail = vivafail;
    }
    
    
    
}
