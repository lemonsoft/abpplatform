/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.project.questions;

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
@Table(name = "questions")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
)
public class QuestionDAO implements SuperBean {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    
    @Column(name = "question_title")
    private String question_title;
    @Column(name = "option1")
    private String option1;
    @Column(name = "option2")
    private String option2;
    @Column(name = "option3")
    private String option3;
    @Column(name = "option4")
    private String option4;
    @Column(name = "option5")
    private String option5;

    @Column(name = "marks")
    private int marks;

    @Column(name = "correct_option")
    private String correct_option;
    @Column(name = "cans1")
    private String cans1;
    @Column(name = "cans2")
    private String cans2;
    @Column(name = "cans3")
    private String cans3;
    @Column(name = "cans4")
    private String cans4;
    @Column(name = "cans5")
    private String cans5;
    @Column(name = "solution")
    private String solution;

    @Column(name = "noofoption")
    private int noofoption;

    @Column(name = "question_type")
    private String question_type;
    @Column(name = "question_level")
    private String question_level;
    @Column(name = "questionimgurl")
    private String questionimgurl;
    @Column(name = "imageurl1")
    private String imageurl1;
    @Column(name = "imageurl2")
    private String imageurl2;
    @Column(name = "imageurl3")
    private String imageurl3;
    @Column(name = "imageurl4")
    private String imageurl4;
    @Column(name = "imageurl5")
    private String imageurl5;
    
    @Column(name = "isapproved")
    private String isapproved;
    @Column(name = "isphaseout")
    private String isphaseout;

    @Column(name = "qpackid")
    private int qpackid;
    @Column(name = "nosid")
    private int nosid;
    @Column(name = "pcid")
    private int pcid;
    @Transient
    private String ssc_id;
    @Transient
    private String pc_id;
    @Transient
    private String actions;
    @Transient
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(String correct_option) {
        this.correct_option = correct_option;
    }

    public String getCans1() {
        return cans1;
    }

    public void setCans1(String cans1) {
        this.cans1 = cans1;
    }

    public String getCans2() {
        return cans2;
    }

    public void setCans2(String cans2) {
        this.cans2 = cans2;
    }

    public String getCans3() {
        return cans3;
    }

    public void setCans3(String cans3) {
        this.cans3 = cans3;
    }

    public String getCans4() {
        return cans4;
    }

    public void setCans4(String cans4) {
        this.cans4 = cans4;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getNoofoption() {
        return noofoption;
    }

    public void setNoofoption(int noofoption) {
        this.noofoption = noofoption;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getQuestion_level() {
        return question_level;
    }

    public void setQuestion_level(String question_level) {
        this.question_level = question_level;
    }

    public String getImageurl1() {
        return imageurl1;
    }

    public void setImageurl1(String imageurl1) {
        this.imageurl1 = imageurl1;
    }

    public String getImageurl2() {
        return imageurl2;
    }

    public void setImageurl2(String imageurl2) {
        this.imageurl2 = imageurl2;
    }

    public String getImageurl3() {
        return imageurl3;
    }

    public void setImageurl3(String imageurl3) {
        this.imageurl3 = imageurl3;
    }

    public String getImageurl4() {
        return imageurl4;
    }

    public void setImageurl4(String imageurl4) {
        this.imageurl4 = imageurl4;
    }

    public int getQpackid() {
        return qpackid;
    }

    public void setQpackid(int qpackid) {
        this.qpackid = qpackid;
    }

    public int getNosid() {
        return nosid;
    }

    public void setNosid(int nosid) {
        this.nosid = nosid;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
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

    public String getQuestionimgurl() {
        return questionimgurl;
    }

    public void setQuestionimgurl(String questionimgurl) {
        this.questionimgurl = questionimgurl;
    }

    public String getCans5() {
        return cans5;
    }

    public void setCans5(String cans5) {
        this.cans5 = cans5;
    }

    public String getPc_id() {
        return pc_id;
    }

    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageurl5() {
        return imageurl5;
    }

    public void setImageurl5(String imageurl5) {
        this.imageurl5 = imageurl5;
    }

    public String getIsapproved() {
        return isapproved;
    }

    public void setIsapproved(String isapproved) {
        this.isapproved = isapproved;
    }

    public String getIsphaseout() {
        return isphaseout;
    }

    public void setIsphaseout(String isphaseout) {
        this.isphaseout = isphaseout;
    }

    

}
