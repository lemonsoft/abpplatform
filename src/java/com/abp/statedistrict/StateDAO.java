/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.statedistrict;

import com.abp.superdao.SuperBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author ss
 */
@Entity
@Table(name = "State", uniqueConstraints = {
    @UniqueConstraint(columnNames = "state_name")})
public class StateDAO implements SuperBean, java.io.Serializable {

    private int stateID;
    private String stateName;
    private List<DistrictDAO> districts = new ArrayList<DistrictDAO>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id", unique = true, nullable = false)
    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    @Column(name = "state_name", unique = true, nullable = false, length = 50)
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy="state", cascade=CascadeType.ALL,orphanRemoval=true)
    public List<DistrictDAO> getDistricts() {
        return districts;
    }

    
    public void setDistricts(List<DistrictDAO> districts) {
        this.districts = districts;
    }

}
