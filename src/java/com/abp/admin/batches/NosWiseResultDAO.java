/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

import java.util.ArrayList;

/**
 *
 * @author ss
 */
public class NosWiseResultDAO {
    
    private String enrollmentno;
    private String traineename;
    private ArrayList  nosdetails;
    private String totaltheorymarks;
    private String totalpracticalmarks;
    private String totalmarks;
    private String result;

    public String getEnrollmentno() {
        return enrollmentno;
    }

    public void setEnrollmentno(String enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    public String getTraineename() {
        return traineename;
    }

    public void setTraineename(String traineename) {
        this.traineename = traineename;
    }

    public ArrayList getNosdetails() {
        return nosdetails;
    }

    public void setNosdetails(ArrayList nosdetails) {
        this.nosdetails = nosdetails;
    }

    public String getTotaltheorymarks() {
        return totaltheorymarks;
    }

    public void setTotaltheorymarks(String totaltheorymarks) {
        this.totaltheorymarks = totaltheorymarks;
    }

    public String getTotalpracticalmarks() {
        return totalpracticalmarks;
    }

    public void setTotalpracticalmarks(String totalpracticalmarks) {
        this.totalpracticalmarks = totalpracticalmarks;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
   
    
}
