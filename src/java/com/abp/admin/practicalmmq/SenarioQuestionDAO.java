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
@Table(name = "senarioquestions")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
)
public class SenarioQuestionDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "srno")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "question")
    private String question;

    @Column(name = "pcsselectedmarks")
    private String pcsselectedmarks;

    @Column(name = "totalmarks")
    private int totalmarks;

    @Column(name = "senario_id")
    private int senario_id;
    
    @Transient
    private String action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPcsselectedmarks() {
        return pcsselectedmarks;
    }

    public void setPcsselectedmarks(String pcsselectedmarks) {
        this.pcsselectedmarks = pcsselectedmarks;
    }

    public int getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(int totalmarks) {
        this.totalmarks = totalmarks;
    }

    public int getSenario_id() {
        return senario_id;
    }

    public void setSenario_id(int senario_id) {
        this.senario_id = senario_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    

}
