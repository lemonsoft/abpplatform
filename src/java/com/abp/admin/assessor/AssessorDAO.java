/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.assessor;

import com.abp.superdao.SuperBean;
import java.util.List;
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
@Table(name = "assessor") 
public class AssessorDAO implements SuperBean {
    @Id
    @Column(name = "assessor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assessorid;
    
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "job_role")
    private String jobrole;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "email_id")
    private String emailid;
    @Column(name = "contact_no")
    private String contactno;
    @Column(name = "aadhar_no")
    private String aadharno;
    @Column(name = "total_exp")
    private String totalexp;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "login_id")
    private String loginid;
    @Column(name = "password")
    private String password;
    @Column(name = "photo_name")
    private String photoname;
    @Column(name = "aadhar_image")
    private String aadharimage;
    @Column(name = "resume_name")
    private String resumename;
    @Column(name = "state_id")
    private int stateid;
    @Column(name = "district_id")
    private int districtid;
    @Column(name = "ssc_id")
    private int ssc_id;
    
    @Transient
    private String actions;
    @Transient
    private String ssc;
    @Transient
    private String state;
    @Transient
    private String district;
    @Transient
    private MultipartFile files;
    @Transient
    private List<String> options;

    public int getAssessorid() {
        return assessorid;
    }

    public void setAssessorid(int assessorid) {
        this.assessorid = assessorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

   

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAadharno() {
        return aadharno;
    }

    public void setAadharno(String aadharno) {
        this.aadharno = aadharno;
    }

    public String getTotalexp() {
        return totalexp;
    }

    public void setTotalexp(String totalexp) {
        this.totalexp = totalexp;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

    public String getAadharimage() {
        return aadharimage;
    }

    public void setAadharimage(String aadharimage) {
        this.aadharimage = aadharimage;
    }

    public String getResumename() {
        return resumename;
    }

    public void setResumename(String resumename) {
        this.resumename = resumename;
    }

    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public MultipartFile getFiles() {
        return files;
    }

    public void setFiles(MultipartFile files) {
        this.files = files;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(int ssc_id) {
        this.ssc_id = ssc_id;
    }
    
    
    
    
    
}
