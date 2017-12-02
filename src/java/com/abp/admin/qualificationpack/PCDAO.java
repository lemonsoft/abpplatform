/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.qualificationpack;

import com.abp.superdao.SuperBean;
import java.io.Serializable;
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
@Table(name = "pc")
public class PCDAO implements SuperBean, Serializable {
    @Id
    @Column(name = "pcID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pcID;
    
    @Column(name = "pc_id")
    private String pcid;
    @Column(name = "pc_name")
    private String pcname;
    
    @Column(name = "theorycutoffmarks")
    private String theorycutoffmarks;
    @Column(name = "practicalcutoffmarks")
    private String practicalcutoffmarks;
    @Column(name = "overallcutoffmarks")
    private String overallcutoffmarks;
    @Column(name = "nosid")
    private String nosid;
    
    @Column(name = "qpack_id")
    private int qpackid;

    public int getPcID() {
        return pcID;
    }

    public void setPcID(int pcID) {
        this.pcID = pcID;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
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

    public String getNosid() {
        return nosid;
    }

    public void setNosid(String nosid) {
        this.nosid = nosid;
    }

    public int getQpackid() {
        return qpackid;
    }

    public void setQpackid(int qpackid) {
        this.qpackid = qpackid;
    }
    
    
    
}
