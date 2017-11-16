/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.ssc;

import com.abp.superdao.SuperBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ss
 */
@Entity
@Table(name="ssc")
public class SSCDAO implements SuperBean{
    
    
    @Id
    @Column(name = "ssc_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sscId;
    @Column(name = "ssc_name")
    private String sscName;
    @Column(name = "ssc_code")
    private String sscCode;
    
    @Transient
    private String actions;

    public int getSscId() {
        return sscId;
    }

    public void setSscId(int sscId) {
        this.sscId = sscId;
    }
    public String getSscName() {
        return sscName;
    }

    public void setSscName(String sscName) {
        this.sscName = sscName;
    }
    public String getSscCode() {
        return sscCode;
    }

    public void setSscCode(String sscCode) {
        this.sscCode = sscCode;
    }

    

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
    
    
    
}
