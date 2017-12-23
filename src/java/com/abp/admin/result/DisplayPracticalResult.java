/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.result;

/**
 *
 * @author ss
 */
public class DisplayPracticalResult {
    
    private String snquestion;
    private String answerstatus;
    private String actualmarks;
    private int scoredmarks;

   
    public String getAnswerstatus() {
        return answerstatus;
    }

    public void setAnswerstatus(String answerstatus) {
        this.answerstatus = answerstatus;
    }

    public String getActualmarks() {
        return actualmarks;
    }

    public void setActualmarks(String actualmarks) {
        this.actualmarks = actualmarks;
    }

    public int getScoredmarks() {
        return scoredmarks;
    }

    public void setScoredmarks(int scoredmarks) {
        this.scoredmarks = scoredmarks;
    }

    public String getSnquestion() {
        return snquestion;
    }

    public void setSnquestion(String snquestion) {
        this.snquestion = snquestion;
    }

    
    
    
}
