/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.qualificationpack;

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
@Table(name = "qualificationpack")
public class QualificationPackDAO implements SuperBean {

    @Id
    @Column(name = "qpID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qpid;

    @Column(name = "qpack_id")
    private String qpackid;
    @Column(name = "qpackname")
    private String qpackname;
    @Column(name = "qpacklevel")
    private String qpacklevel;
    @Column(name = "totaltheorymarks")
    private String totaltheorymarks;
    @Column(name = "totalpracticalmarks")
    private String totalpracticalmarks;
    @Column(name = "totalmarks")
    private String totalmarks;
    @Column(name = "theorycutoffmarks")
    private String theorycutoffmarks;
    @Column(name = "practicalcutoffmarks")
    private String practicalcutoffmarks;
    @Column(name = "overallcutoffmarks")
    private String overallcutoffmarks;
    @Column(name = "weightedavgmarks")
    private String weightedavgmarks;
    @Column(name = "ssc_id")
    private String sscid;

    @Transient
    private String ssc_id;
    @Transient
    private String actions;

    public String getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(String ssc_id) {
        this.ssc_id = ssc_id;
    }

    public int getQpid() {
        return qpid;
    }

    public void setQpid(int qpid) {
        this.qpid = qpid;
    }

    public String getQpackid() {
        return qpackid;
    }

    public void setQpackid(String qpackid) {
        this.qpackid = qpackid;
    }

    public String getQpackname() {
        return qpackname;
    }

    public void setQpackname(String qpackname) {
        this.qpackname = qpackname;
    }

    public String getQpacklevel() {
        return qpacklevel;
    }

    public void setQpacklevel(String qpacklevel) {
        this.qpacklevel = qpacklevel;
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

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getSscid() {
        return sscid;
    }

    public void setSscid(String sscid) {
        this.sscid = sscid;
    }

}
