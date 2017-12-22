/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.result;

import com.abp.superdao.SuperBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ss
 */
@Entity
@Table(name = "assesmentresult")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
)
public class AssesmentResult implements SuperBean, Serializable {

    @Id
    @Column(name = "assesmentid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "batchid")
    private int batchid;

    @Column(name = "centerimage")
    private String centerimage;
    @Column(name = "assessorimage")
    private String assessorimage;
    @Column(name = "assessorimage1")
    private String assessorimage1;
    @Column(name = "startdate")
    private String startdate;
    @Column(name = "enddate")
    private String enddate;
    @Column(name = "latonstart")
    private String latonstart;
    @Column(name = "latonend")
    private String latonend;
    @Column(name = "longonstart")
    private String longonstart;
    @Column(name = "longonend")
    private String longonend;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBatchid() {
        return batchid;
    }

    public void setBatchid(int batchid) {
        this.batchid = batchid;
    }

    public String getAssessorimage1() {
        return assessorimage1;
    }

    public void setAssessorimage1(String assessorimage1) {
        this.assessorimage1 = assessorimage1;
    }

    public String getCenterimage() {
        return centerimage;
    }

    public void setCenterimage(String centerimage) {
        this.centerimage = centerimage;
    }

    public String getAssessorimage() {
        return assessorimage;
    }

    public void setAssessorimage(String assessorimage) {
        this.assessorimage = assessorimage;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getLatonstart() {
        return latonstart;
    }

    public void setLatonstart(String latonstart) {
        this.latonstart = latonstart;
    }

    public String getLatonend() {
        return latonend;
    }

    public void setLatonend(String latonend) {
        this.latonend = latonend;
    }

    public String getLongonstart() {
        return longonstart;
    }

    public void setLongonstart(String longonstart) {
        this.longonstart = longonstart;
    }

    public String getLongonend() {
        return longonend;
    }

    public void setLongonend(String longonend) {
        this.longonend = longonend;
    }

}
