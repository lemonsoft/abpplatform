/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.generateqp;

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
@Table(name = "pcwisequestion")
public class PCWiseQuestionDAO implements SuperBean {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "qpackid")
    private int qpackid;
    
    @Column(name = "pcid")
    private int pcid;

    @Column(name = "totaladdedmarks")
    private int totaladdedmarks;

    @Column(name = "marks1")
    private int marks1;
    @Column(name = "marks2")
    private int marks2;
    @Column(name = "marks3")
    private int marks3;
    @Column(name = "marks4")
    private int marks4;
    @Column(name = "marks5")
    private int marks5;
    @Column(name = "marks6")
    private int marks6;
    @Column(name = "marks7")
    private int marks7;
    @Column(name = "marks8")
    private int marks8;
    @Column(name = "marks9")
    private int marks9;
    @Column(name = "marks10")
    private int marks10;
    @Column(name = "marks11")
    private int marks11;
    @Column(name = "marks12")
    private int marks12;
    @Column(name = "marks13")
    private int marks13;
    @Column(name = "marks14")
    private int marks14;
    @Column(name = "marks15")
    private int marks15;
    @Column(name = "marks16")
    private int marks16;
    @Column(name = "marks17")
    private int marks17;
    @Column(name = "marks18")
    private int marks18;
    @Column(name = "marks19")
    private int marks19;
    @Column(name = "marks20")
    private int marks20;
    @Column(name = "marks21")
    private int marks21;
    @Column(name = "marks22")
    private int marks22;
    @Column(name = "marks23")
    private int marks23;
    @Column(name = "marks24")
    private int marks24;
    @Column(name = "marks25")
    private int marks25;
    
    @Transient
    private String pcids;
    @Transient
    private String pcname;
    @Transient
    private String theorymarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQpackid() {
        return qpackid;
    }

    public void setQpackid(int qpackid) {
        this.qpackid = qpackid;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public int getTotaladdedmarks() {
        return totaladdedmarks;
    }

    public void setTotaladdedmarks(int totaladdedmarks) {
        this.totaladdedmarks = totaladdedmarks;
    }

    public int getMarks1() {
        return marks1;
    }

    public void setMarks1(int marks1) {
        this.marks1 = marks1;
    }

    public int getMarks2() {
        return marks2;
    }

    public void setMarks2(int marks2) {
        this.marks2 = marks2;
    }

    public int getMarks3() {
        return marks3;
    }

    public void setMarks3(int marks3) {
        this.marks3 = marks3;
    }

    public int getMarks4() {
        return marks4;
    }

    public void setMarks4(int marks4) {
        this.marks4 = marks4;
    }

    public int getMarks5() {
        return marks5;
    }

    public void setMarks5(int marks5) {
        this.marks5 = marks5;
    }

    public int getMarks6() {
        return marks6;
    }

    public void setMarks6(int marks6) {
        this.marks6 = marks6;
    }

    public int getMarks7() {
        return marks7;
    }

    public void setMarks7(int marks7) {
        this.marks7 = marks7;
    }

    public int getMarks8() {
        return marks8;
    }

    public void setMarks8(int marks8) {
        this.marks8 = marks8;
    }

    public int getMarks9() {
        return marks9;
    }

    public void setMarks9(int marks9) {
        this.marks9 = marks9;
    }

    public int getMarks10() {
        return marks10;
    }

    public void setMarks10(int marks10) {
        this.marks10 = marks10;
    }

    public int getMarks11() {
        return marks11;
    }

    public void setMarks11(int marks11) {
        this.marks11 = marks11;
    }

    public int getMarks12() {
        return marks12;
    }

    public void setMarks12(int marks12) {
        this.marks12 = marks12;
    }

    public int getMarks13() {
        return marks13;
    }

    public void setMarks13(int marks13) {
        this.marks13 = marks13;
    }

    public int getMarks14() {
        return marks14;
    }

    public void setMarks14(int marks14) {
        this.marks14 = marks14;
    }

    public int getMarks15() {
        return marks15;
    }

    public void setMarks15(int marks15) {
        this.marks15 = marks15;
    }

    public int getMarks16() {
        return marks16;
    }

    public void setMarks16(int marks16) {
        this.marks16 = marks16;
    }

    public int getMarks17() {
        return marks17;
    }

    public void setMarks17(int marks17) {
        this.marks17 = marks17;
    }

    public int getMarks18() {
        return marks18;
    }

    public void setMarks18(int marks18) {
        this.marks18 = marks18;
    }

    public int getMarks19() {
        return marks19;
    }

    public void setMarks19(int marks19) {
        this.marks19 = marks19;
    }

    public int getMarks20() {
        return marks20;
    }

    public void setMarks20(int marks20) {
        this.marks20 = marks20;
    }

    public int getMarks21() {
        return marks21;
    }

    public void setMarks21(int marks21) {
        this.marks21 = marks21;
    }

    public int getMarks22() {
        return marks22;
    }

    public void setMarks22(int marks22) {
        this.marks22 = marks22;
    }

    public int getMarks23() {
        return marks23;
    }

    public void setMarks23(int marks23) {
        this.marks23 = marks23;
    }

    public int getMarks24() {
        return marks24;
    }

    public void setMarks24(int marks24) {
        this.marks24 = marks24;
    }

    public int getMarks25() {
        return marks25;
    }

    public void setMarks25(int marks25) {
        this.marks25 = marks25;
    }

    public String getPcids() {
        return pcids;
    }

    public void setPcids(String pcids) {
        this.pcids = pcids;
    }

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
    }

    public String getTheorymarks() {
        return theorymarks;
    }

    public void setTheorymarks(String theorymarks) {
        this.theorymarks = theorymarks;
    }
    
    
}
