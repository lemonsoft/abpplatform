/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.qualificationpack;

import com.abp.superdao.SuperBean;
import java.util.ArrayList;
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
@Table(name = "nos")
public class NOSDAO implements SuperBean {

    @Id
    @Column(name = "nosID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nosID;

    @Column(name = "nos_id")
    private String nosid;
    @Column(name = "nos_name")
    private String nosname;

    @Column(name = "theorycutoffmarks")
    private String theorycutoffmarks;
    @Column(name = "practicalcutoffmarks")
    private String practicalcutoffmarks;
    @Column(name = "overallcutoffmarks")
    private String overallcutoffmarks;
    @Column(name = "weightedavgmarks")
    private String weightedavgmarks;
    @Column(name = "qpack_id")
    private String qpackid;

    @Transient
    ArrayList pcdata = new ArrayList();

    public int getNosID() {
        return nosID;
    }

    public void setNosID(int nosID) {
        this.nosID = nosID;
    }

    public String getNosid() {
        return nosid;
    }

    public void setNosid(String nosid) {
        this.nosid = nosid;
    }

    public String getNosname() {
        return nosname;
    }

    public void setNosname(String nosname) {
        this.nosname = nosname;
    }

    public String getTheorycutoffmarks() {
        return theorycutoffmarks;
    }

    public void setTheorycutoffmarks(String theorycutoffmarks) {
        this.theorycutoffmarks = theorycutoffmarks;
    }

    public String getPracticalcutoffmarks() {
        return practicalcutoffmarks;
    }

    public void setPracticalcutoffmarks(String practicalcutoffmarks) {
        this.practicalcutoffmarks = practicalcutoffmarks;
    }

    public String getOverallcutoffmarks() {
        return overallcutoffmarks;
    }

    public void setOverallcutoffmarks(String overallcutoffmarks) {
        this.overallcutoffmarks = overallcutoffmarks;
    }

    public String getWeightedavgmarks() {
        return weightedavgmarks;
    }

    public void setWeightedavgmarks(String weightedavgmarks) {
        this.weightedavgmarks = weightedavgmarks;
    }

    public String getQpackid() {
        return qpackid;
    }

    public void setQpackid(String qpackid) {
        this.qpackid = qpackid;
    }

    public ArrayList getPcdata() {
        return pcdata;
    }

    public void setPcdata(ArrayList pcdata) {
        this.pcdata = pcdata;
    }

}
