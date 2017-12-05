/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.superservice;

import com.abp.superdao.SuperBean;
import com.abp.superdao.SuperDAO;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ss
 */
@Service
public class SuperServiceImpl implements SuperService {

    private SuperDAO superDAO;

    public void setSuperDAO(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    @Override
    @Transactional
    public void saveObject(SuperBean obj) {
        this.superDAO.saveObject(obj);
    }

    @Override
    @Transactional
    public void updateObject(SuperBean obj) {
        this.superDAO.updateObject(obj);
    }

    @Override
    @Transactional
    public void updateObjectFields(SuperBean obj, Map<String, String> Param) {
        this.superDAO.updateObjectFields(obj, Param);
    }

    @Override
    @Transactional
    public List<SuperBean> listAllObjects(SuperBean obj) {
        return this.superDAO.listAllObjects(obj);
    }

    @Override
    @Transactional
    public List<SuperBean> listAllObjectsByCriteria(SuperBean obj, Map<String, String> param) {
        return this.superDAO.listAllObjectsByCriteria(obj, param);
    }

    @Override
    @Transactional
    public List<SuperBean> listAllObjectsByAndCriteria(SuperBean obj, Map<String, String> param) {

        return this.superDAO.listAllObjectsByAndCriteria(obj, param);
    }

    @Override
    @Transactional
    public List<SuperBean> listAllObjectsByORCriteria(SuperBean obj, Map<String, String> param) {

        return this.superDAO.listAllObjectsByORCriteria(obj, param);
    }

    @Override
    @Transactional
    public SuperBean getObjectById(SuperBean obj, int id) {
        return this.superDAO.getObjectById(obj, id);
    }

    @Override
    @Transactional
    public void deleteObjectById(SuperBean obj, int id) {
        this.superDAO.deleteObjectById(obj, id);
    }

    @Override
    @Transactional
    public void deleteObject(SuperBean obj) {
        this.superDAO.deleteObject(obj);
    }

    @Override
    @Transactional
    public boolean duplicateCheck(SuperBean obj, Map<String, String> Param) {
        return this.superDAO.duplicateCheck(obj, Param);
    }
}
