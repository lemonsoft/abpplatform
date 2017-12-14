/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.generateqp;

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
@Table(name = "questionpaper")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
)
public class QuestionPaperDAO implements SuperBean {

    @Id
    @Column(name = "questionpaperid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int questionpaperid;

    @Column(name = "qpackid")
    private int qpackid;

    @Column(name = "questionpapername")
    private String questionpapername;
    @Column(name = "israndom")
    private String israndom;
    @Column(name = "isoptionrandom")
    private String isoptionrandom;
    @Column(name = "isactive")
    private String isactive;

    @Column(name = "questionids")
    private String questionids;
    @Column(name = "theorymmqids")
    private String theorymmqids;
    @Column(name = "practicalmmqids")
    private String practicalmmqids;

    @Column(name = "totaltime")
    private int totaltime;
    @Column(name = "createddatetime")
    private String createddatetime;
    @Column(name = "totalmarks")
    private String totalmarks;

    @Transient
    private String actions;
    @Transient
    private String ssc_id;

    public int getQuestionpaperid() {
        return questionpaperid;
    }

    public void setQuestionpaperid(int questionpaperid) {
        this.questionpaperid = questionpaperid;
    }

    public int getQpackid() {
        return qpackid;
    }

    public void setQpackid(int qpackid) {
        this.qpackid = qpackid;
    }

    public String getQuestionpapername() {
        return questionpapername;
    }

    public void setQuestionpapername(String questionpapername) {
        this.questionpapername = questionpapername;
    }

    public String getIsrandom() {
        return israndom;
    }

    public void setIsrandom(String israndom) {
        this.israndom = israndom;
    }

    public String getIsoptionrandom() {
        return isoptionrandom;
    }

    public void setIsoptionrandom(String isoptionrandom) {
        this.isoptionrandom = isoptionrandom;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getQuestionids() {
        return questionids;
    }

    public void setQuestionids(String questionids) {
        this.questionids = questionids;
    }

    public int getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(int totaltime) {
        this.totaltime = totaltime;
    }

    

    

    public String getCreateddatetime() {
        return createddatetime;
    }

    public void setCreateddatetime(String createddatetime) {
        this.createddatetime = createddatetime;
    }

    public String getTheorymmqids() {
        return theorymmqids;
    }

    public void setTheorymmqids(String theorymmqids) {
        this.theorymmqids = theorymmqids;
    }

    public String getPracticalmmqids() {
        return practicalmmqids;
    }

    public void setPracticalmmqids(String practicalmmqids) {
        this.practicalmmqids = practicalmmqids;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(String ssc_id) {
        this.ssc_id = ssc_id;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

}
