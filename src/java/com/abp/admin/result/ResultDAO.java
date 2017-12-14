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
public class ResultDAO {
    
    private String ssc_id;
    private String qpid;
    private String batchid;

    public String getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(String ssc_id) {
        this.ssc_id = ssc_id;
    }

    public String getQpid() {
        return qpid;
    }

    public void setQpid(String qpid) {
        this.qpid = qpid;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    
    
}
