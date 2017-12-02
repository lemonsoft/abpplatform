/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.generateqp;

import com.abp.superdao.SuperBean;
import javax.persistence.Transient;

/**
 *
 * @author ss
 */
public class GenerateQPDAO implements SuperBean {

    private int qpackid;

    private String ssc_id;

    public int getQpackid() {
        return qpackid;
    }

    public void setQpackid(int qpackid) {
        this.qpackid = qpackid;
    }

    public String getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(String ssc_id) {
        this.ssc_id = ssc_id;
    }

}
