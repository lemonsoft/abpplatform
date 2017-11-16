/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.superdao;


import java.util.List;
import java.util.Map;

/**
 *
 * @author ss
 */
public interface SuperDAO {
    public void saveObject(SuperBean obj);

    public void updateObject(SuperBean obj);
    public void updateObjectFields(SuperBean obj,Map<String, String> Param);

    public List<SuperBean> listAllObjects(SuperBean obj);
    
    public List<SuperBean> listAllObjectsByCriteria(SuperBean obj,Map<String, String> param);

    public SuperBean getObjectById(SuperBean obj,int id);

    public void deleteObjectById(SuperBean obj,int id);
    public void deleteObject(SuperBean obj);
    public boolean duplicateCheck(SuperBean obj,Map<String, String> Param);
}
