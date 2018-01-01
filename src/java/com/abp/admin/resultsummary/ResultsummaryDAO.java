/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.resultsummary;

import com.abp.superdao.SuperBean;

/**
 *
 * @author ss
 */
public class ResultsummaryDAO implements SuperBean {
    
    private String batchid;

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    
    
    
}
