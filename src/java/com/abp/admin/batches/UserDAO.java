/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

import com.abp.superdao.SuperBean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ss
 */
@Entity
@Table(name = "users")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
)
public class UserDAO implements SuperBean, Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "batchid")
    private int batchid;
    @Column(name = "enrollmentno")
    private int enrollmentno;

    @Column(name = "trainingagency")
    private String trainingagency;
    @Column(name = "traineename")
    private String traineename;

    @Column(name = "gender")
    private String gender;
    @Column(name = "category")
    private String category;
    @Column(name = "dateofbirth")
    private String dateofbirth;
    @Column(name = "fathername")
    private String fathername;
    @Column(name = "mothername")
    private String mothername;
    @Column(name = "traineeadress")
    private String traineeadress;
    @Column(name = "district")
    private String district;
    @Column(name = "states")
    private String states;
    @Column(name = "traineephone")
    private String traineephone;
    @Column(name = "eduqualification")
    private String eduqualification;
    @Column(name = "techqualification")
    private String techqualification;
    @Column(name = "istraineconductocupstd")
    private String istraineconductocupstd;
    @Column(name = "jobrole")
    private String jobrole;
    @Column(name = "istagencynsdcpartner")
    private String istagencynsdcpartner;
    @Column(name = "skillingupskilling")
    private String skillingupskilling;
    @Column(name = "coursestartdate")
    private String coursestartdate;
    @Column(name = "dateofcompletion")
    private String dateofcompletion;
    @Column(name = "trainersname")
    private String trainersname;
    @Column(name = "tproviderspocname")
    private String tproviderspocname;
    @Column(name = "contactno")
    private String contactno;
    @Column(name = "emailid")
    private String emailid;
    @Column(name = "totalfeefortraining")
    private String totalfeefortraining;
    @Column(name = "dateofassessment")
    private String dateofassessment;
    @Column(name = "assessmentagency")
    private String assessmentagency;
    @Column(name = "totalfeeforassessment")
    private String totalfeeforassessment;
    @Column(name = "assessmentfeefromwhom")
    private String assessmentfeefromwhom;
    @Column(name = "totalfeeforcertification")
    private String totalfeeforcertification;
    @Column(name = "certificationchargefromwhom")
    private String certificationchargefromwhom;
    @Column(name = "priorworkexpyear")
    private String priorworkexpyear;
    @Column(name = "employed")
    private String employed;
    @Column(name = "nameofemployer")
    private String nameofemployer;
    @Column(name = "keycontactfrmemployer")
    private String keycontactfrmemployer;
    @Column(name = "phonenoofkeycontact")
    private String phonenoofkeycontact;
    @Column(name = "perageincreasewagepostcert")
    private String perageincreasewagepostcert;
    @Column(name = "benifitgainfrmcert")
    private String benifitgainfrmcert;
    @Column(name = "username")
    private String username;
    @Column(name = "webcam")
    private String webcam;
    

    @Transient
    private MultipartFile files;
    @Transient
    private String status;
    @Transient
    private String photoname;

    public MultipartFile getFiles() {
        return files;
    }

    public void setFiles(MultipartFile files) {
        this.files = files;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBatchid() {
        return batchid;
    }

    public void setBatchid(int batchid) {
        this.batchid = batchid;
    }

    public int getEnrollmentno() {
        return enrollmentno;
    }

    public void setEnrollmentno(int enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    public String getTrainingagency() {
        return trainingagency;
    }

    public void setTrainingagency(String trainingagency) {
        this.trainingagency = trainingagency;
    }

    public String getTraineename() {
        return traineename;
    }

    public void setTraineename(String traineename) {
        this.traineename = traineename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getTraineeadress() {
        return traineeadress;
    }

    public void setTraineeadress(String traineeadress) {
        this.traineeadress = traineeadress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getTraineephone() {
        return traineephone;
    }

    public void setTraineephone(String traineephone) {
        this.traineephone = traineephone;
    }

    public String getEduqualification() {
        return eduqualification;
    }

    public void setEduqualification(String eduqualification) {
        this.eduqualification = eduqualification;
    }

    public String getTechqualification() {
        return techqualification;
    }

    public void setTechqualification(String techqualification) {
        this.techqualification = techqualification;
    }

    public String getIstraineconductocupstd() {
        return istraineconductocupstd;
    }

    public void setIstraineconductocupstd(String istraineconductocupstd) {
        this.istraineconductocupstd = istraineconductocupstd;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public String getIstagencynsdcpartner() {
        return istagencynsdcpartner;
    }

    public void setIstagencynsdcpartner(String istagencynsdcpartner) {
        this.istagencynsdcpartner = istagencynsdcpartner;
    }

    public String getSkillingupskilling() {
        return skillingupskilling;
    }

    public void setSkillingupskilling(String skillingupskilling) {
        this.skillingupskilling = skillingupskilling;
    }

    public String getCoursestartdate() {
        return coursestartdate;
    }

    public void setCoursestartdate(String coursestartdate) {
        this.coursestartdate = coursestartdate;
    }

    public String getDateofcompletion() {
        return dateofcompletion;
    }

    public void setDateofcompletion(String dateofcompletion) {
        this.dateofcompletion = dateofcompletion;
    }

    public String getTrainersname() {
        return trainersname;
    }

    public void setTrainersname(String trainersname) {
        this.trainersname = trainersname;
    }

    public String getTproviderspocname() {
        return tproviderspocname;
    }

    public void setTproviderspocname(String tproviderspocname) {
        this.tproviderspocname = tproviderspocname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getTotalfeefortraining() {
        return totalfeefortraining;
    }

    public void setTotalfeefortraining(String totalfeefortraining) {
        this.totalfeefortraining = totalfeefortraining;
    }

    public String getDateofassessment() {
        return dateofassessment;
    }

    public void setDateofassessment(String dateofassessment) {
        this.dateofassessment = dateofassessment;
    }

    public String getAssessmentagency() {
        return assessmentagency;
    }

    public void setAssessmentagency(String assessmentagency) {
        this.assessmentagency = assessmentagency;
    }

    public String getTotalfeeforassessment() {
        return totalfeeforassessment;
    }

    public void setTotalfeeforassessment(String totalfeeforassessment) {
        this.totalfeeforassessment = totalfeeforassessment;
    }

    public String getAssessmentfeefromwhom() {
        return assessmentfeefromwhom;
    }

    public void setAssessmentfeefromwhom(String assessmentfeefromwhom) {
        this.assessmentfeefromwhom = assessmentfeefromwhom;
    }

    public String getTotalfeeforcertification() {
        return totalfeeforcertification;
    }

    public void setTotalfeeforcertification(String totalfeeforcertification) {
        this.totalfeeforcertification = totalfeeforcertification;
    }

    public String getCertificationchargefromwhom() {
        return certificationchargefromwhom;
    }

    public void setCertificationchargefromwhom(String certificationchargefromwhom) {
        this.certificationchargefromwhom = certificationchargefromwhom;
    }

    public String getPriorworkexpyear() {
        return priorworkexpyear;
    }

    public void setPriorworkexpyear(String priorworkexpyear) {
        this.priorworkexpyear = priorworkexpyear;
    }

    public String getEmployed() {
        return employed;
    }

    public void setEmployed(String employed) {
        this.employed = employed;
    }

    public String getNameofemployer() {
        return nameofemployer;
    }

    public void setNameofemployer(String nameofemployer) {
        this.nameofemployer = nameofemployer;
    }

    public String getKeycontactfrmemployer() {
        return keycontactfrmemployer;
    }

    public void setKeycontactfrmemployer(String keycontactfrmemployer) {
        this.keycontactfrmemployer = keycontactfrmemployer;
    }

    public String getPhonenoofkeycontact() {
        return phonenoofkeycontact;
    }

    public void setPhonenoofkeycontact(String phonenoofkeycontact) {
        this.phonenoofkeycontact = phonenoofkeycontact;
    }

    public String getPerageincreasewagepostcert() {
        return perageincreasewagepostcert;
    }

    public void setPerageincreasewagepostcert(String perageincreasewagepostcert) {
        this.perageincreasewagepostcert = perageincreasewagepostcert;
    }

    public String getBenifitgainfrmcert() {
        return benifitgainfrmcert;
    }

    public void setBenifitgainfrmcert(String benifitgainfrmcert) {
        this.benifitgainfrmcert = benifitgainfrmcert;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebcam() {
        return webcam;
    }

    public void setWebcam(String webcam) {
        this.webcam = webcam;
    }

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

}
