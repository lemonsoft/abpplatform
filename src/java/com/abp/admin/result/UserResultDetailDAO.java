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
@Table(name = "userresultdetail")
public class UserResultDetailDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "userresultdetailid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "userid")
    private int userid;

    @Column(name = "examstarttime")
    private String examstarttime;
    @Column(name = "examendtime")
    private String examendtime;
    @Column(name = "totaltime")
    private String totaltime;
    @Column(name = "timetaken")
    private String timetaken;

    @Column(name = "ipaddress")
    private String ipaddress;
    @Column(name = "browserversion")
    private String browserversion;
    @Column(name = "examstatus")
    private String examstatus;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;

    @Column(name = "assesmentid")
    private int assesmentid;
    @Column(name = "batchid")
    private int batchid;
    @Column(name = "logincount")
    private int logincount;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getExamstarttime() {
        return examstarttime;
    }

    public void setExamstarttime(String examstarttime) {
        this.examstarttime = examstarttime;
    }

    public String getExamendtime() {
        return examendtime;
    }

    public void setExamendtime(String examendtime) {
        this.examendtime = examendtime;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public String getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(String timetaken) {
        this.timetaken = timetaken;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getBrowserversion() {
        return browserversion;
    }

    public void setBrowserversion(String browserversion) {
        this.browserversion = browserversion;
    }

    public String getExamstatus() {
        return examstatus;
    }

    public void setExamstatus(String examstatus) {
        this.examstatus = examstatus;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getAssesmentid() {
        return assesmentid;
    }

    public void setAssesmentid(int assesmentid) {
        this.assesmentid = assesmentid;
    }

    public int getBatchid() {
        return batchid;
    }

    public void setBatchid(int batchid) {
        this.batchid = batchid;
    }

    public int getLogincount() {
        return logincount;
    }

    public void setLogincount(int logincount) {
        this.logincount = logincount;
    }

}
