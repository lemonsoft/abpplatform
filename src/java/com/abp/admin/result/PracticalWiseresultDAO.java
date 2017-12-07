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
@Table(name = "practicalwiseresult")
public class PracticalWiseresultDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "practicalid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "senarioid")
    private int senarioid;
    @Column(name = "questionid")
    private int questionid;

    @Column(name = "answerstatus")
    private String answerstatus;
    @Column(name = "userresultdetailid")
    private int userresultdetailid;
    @Column(name = "userid")
    private int userid;
    @Column(name = "assesmentid")
    private int assesmentid;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSenarioid() {
        return senarioid;
    }

    public void setSenarioid(int senarioid) {
        this.senarioid = senarioid;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getAnswerstatus() {
        return answerstatus;
    }

    public void setAnswerstatus(String answerstatus) {
        this.answerstatus = answerstatus;
    }

    public int getUserresultdetailid() {
        return userresultdetailid;
    }

    public void setUserresultdetailid(int userresultdetailid) {
        this.userresultdetailid = userresultdetailid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAssesmentid() {
        return assesmentid;
    }

    public void setAssesmentid(int assesmentid) {
        this.assesmentid = assesmentid;
    }

}
