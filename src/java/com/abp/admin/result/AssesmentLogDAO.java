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
@Table(name = "assesmentlog")
public class AssesmentLogDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "logid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "userid")
    private int userid;
    @Column(name = "userresultdetailid")
    private int userresultdetailid;
    @Column(name = "assesmentid")
    private int assesmentid;

    @Column(name = "logactiondate")
    private String logactiondate;
    @Column(name = "actiontaken")
    private String actiontaken;

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

    public int getUserresultdetailid() {
        return userresultdetailid;
    }

    public void setUserresultdetailid(int userresultdetailid) {
        this.userresultdetailid = userresultdetailid;
    }

    public int getAssesmentid() {
        return assesmentid;
    }

    public void setAssesmentid(int assesmentid) {
        this.assesmentid = assesmentid;
    }

    public String getLogactiondate() {
        return logactiondate;
    }

    public void setLogactiondate(String logactiondate) {
        this.logactiondate = logactiondate;
    }

    public String getActiontaken() {
        return actiontaken;
    }

    public void setActiontaken(String actiontaken) {
        this.actiontaken = actiontaken;
    }

}
