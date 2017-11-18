/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

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
@Table(name = "batches")
public class BatchesDAO implements SuperBean {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "batch_id")
    private int batch_id;
    @Column(name = "batch_size")
    private int batch_size;
    @Column(name = "center_id")
    private String center_id;
    @Column(name = "project_id")
    private int project_id;

    @Column(name = "assessment_start_date")
    private String assessmentStartDate;
    @Column(name = "assessment_end_date")
    private String assessmentEndDate;

    @Column(name = "state_id")
    private int state_id;
    @Column(name = "district_id")
    private int district_id;

    @Column(name = "center_pincode")
    private String centerPincode;
    @Column(name = "center_contactno")
    private String centerContactno;
    @Column(name = "center_emailid")
    private String centerEmailid;
    @Column(name = "center_address")
    private String centerAddress;
    @Column(name = "tp_name")
    private String tpName;

    @Column(name = "login_restrict")
    private int loginRestrict;
    @Column(name = "qpack_id")
    private int qpackId;
    @Column(name = "assessor_id")
    private int assessorId;
    @Column(name = "question_paper_id")
    private int questionPaperId;
    @Column(name = "capture_image")
    private char captureImage;

    @Transient
    private String ssc_id;
    @Transient
    private String qpid;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getBatch_size() {
        return batch_size;
    }

    public void setBatch_size(int batch_size) {
        this.batch_size = batch_size;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getCenterPincode() {
        return centerPincode;
    }

    public void setCenterPincode(String centerPincode) {
        this.centerPincode = centerPincode;
    }

    public String getCenterContactno() {
        return centerContactno;
    }

    public void setCenterContactno(String centerContactno) {
        this.centerContactno = centerContactno;
    }

    public String getCenterEmailid() {
        return centerEmailid;
    }

    public void setCenterEmailid(String centerEmailid) {
        this.centerEmailid = centerEmailid;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName;
    }

    public int getLoginRestrict() {
        return loginRestrict;
    }

    public void setLoginRestrict(int loginRestrict) {
        this.loginRestrict = loginRestrict;
    }

    public int getQpackId() {
        return qpackId;
    }

    public void setQpackId(int qpackId) {
        this.qpackId = qpackId;
    }

    public int getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(int assessorId) {
        this.assessorId = assessorId;
    }

    public int getQuestionPaperId() {
        return questionPaperId;
    }

    public void setQuestionPaperId(int questionPaperId) {
        this.questionPaperId = questionPaperId;
    }

    public char getCaptureImage() {
        return captureImage;
    }

    public void setCaptureImage(char captureImage) {
        this.captureImage = captureImage;
    }

    public String getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(String ssc_id) {
        this.ssc_id = ssc_id;
    }

    public String getQpid() {
        return qpid;
    }

    public void setQpid(String qpid) {
        this.qpid = qpid;
    }

}
