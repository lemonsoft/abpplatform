/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.practicalmmq;

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
@Table(name = "practicalmmq")
public class PracticalMMQDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "senario")
    private String senario;
    @Column(name = "marks")
    private int marks;
    @Column(name = "qpackid")
    private int qpackid;
    
    @Transient
    private String ssc_id;

    @Transient
    private String actions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenario() {
        return senario;
    }

    public void setSenario(String senario) {
        this.senario = senario;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

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

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
    
    
    
}
