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
@Table(name = "questionwiselog")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
)
public class QuestionWiseLogDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "questionwiselogid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "questionno")
    private int questionno;
   
    @Column(name = "userid")
    private int userid;
    @Column(name = "userresultdetailid")
    private int userresultdetailid;
    @Column(name = "assesmentid")
    private int assesmentid;

    @Column(name = "startdate")
    private String startdate;
    @Column(name = "enddate")
    private String enddate;
    @Column(name = "timetaken")
    private String timetaken;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuestionno() {
        return questionno;
    }

    public void setQuestionno(int questionno) {
        this.questionno = questionno;
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

    public String getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(String timetaken) {
        this.timetaken = timetaken;
    }
    
    
    
    
}
