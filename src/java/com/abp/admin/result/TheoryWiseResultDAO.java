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
@Table(name = "theorywiseresult")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
)
public class TheoryWiseResultDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "theoryid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "questionid")
    private int questionid;
    @Column(name = "questionno")
    private int questionno;

    @Column(name = "correctanswer")
    private String correctanswer;
    @Column(name = "timetaken")
    private String timetaken;
    @Column(name = "reviewlater")
    private String reviewlater;
    @Column(name = "userresultdetailid")
    private int userresultdetailid;
    @Column(name = "userid")
    private int userid;
    @Column(name = "assesmentid")
    private int assesmentid;
    @Column(name = "recorddate")
    private String recorddate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }

    public String getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(String timetaken) {
        this.timetaken = timetaken;
    }

    public String getReviewlater() {
        return reviewlater;
    }

    public void setReviewlater(String reviewlater) {
        this.reviewlater = reviewlater;
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

    public int getQuestionno() {
        return questionno;
    }

    public void setQuestionno(int questionno) {
        this.questionno = questionno;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

}
