/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.setting;

/**
 *
 * @author ss
 */
public class SettingDAO {

    private String recordid;

    private String studentname;

    private String enablelogin;

    private String enableip;

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getEnablelogin() {
        return enablelogin;
    }

    public void setEnablelogin(String enablelogin) {
        this.enablelogin = enablelogin;
    }

    public String getEnableip() {
        return enableip;
    }

    public void setEnableip(String enableip) {
        this.enableip = enableip;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

}
