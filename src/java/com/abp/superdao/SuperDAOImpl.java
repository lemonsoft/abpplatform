/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.superdao;

import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ss
 */
public class SuperDAOImpl implements SuperDAO {

    private static final Logger logger = LoggerFactory.getLogger(SuperDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void saveObject(SuperBean obj) {

        System.out.println("going to save..");

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(obj);
        logger.info("Object saved successfully,  Details=" + obj);
    }

    @Override
    public void updateObject(SuperBean obj) {

        Session session = this.sessionFactory.getCurrentSession();
        session.update(obj);
        //session.merge(obj);
        logger.info("Object updated successfully, Object Details=" + obj);
    }

    @Override
    public void updateObjectFields(SuperBean obj, Map<String, String> param) {
        Session session = this.sessionFactory.getCurrentSession();
        // Query query = session.createQuery("update " + obj.getClass().getName() + " set stockName = :stockName" + " where stockCode = :stockCode");
        String queryparam = "";
        String addwhere = "";
        for (Map.Entry<String, String> entry : param.entrySet()) {
            // System.out.println(entry.getKey() + "/" + entry.getValue());

            if (entry.getKey().contains("ID")) {
                addwhere = addwhere + entry.getKey() + "=:" + entry.getKey();
            } else {
                queryparam = queryparam + entry.getKey() + "=:" + entry.getKey() + ",";
            }
        }
        queryparam = queryparam.substring(0, queryparam.length() - 1);

        String compQuery = "update " + obj.getClass().getName() + " set " + queryparam + " where " + addwhere;
        System.out.println("query  : " + queryparam);
        System.out.println("where  : " + addwhere);
        System.out.println("query created : " + compQuery);
        Query query = session.createQuery(compQuery);
        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (entry.getKey().contains("id")) {
                query.setParameter(entry.getKey(), new Integer(entry.getValue()));
            } else {
                query.setParameter(entry.getKey(), entry.getValue());
            }

        }

        int result = query.executeUpdate();

        logger.info("Object updated successfully, Object Details=" + result);
    }

    @Override
    public List<SuperBean> listAllObjects(SuperBean obj) {

        Session session = this.sessionFactory.getCurrentSession();
        List<SuperBean> superList = session.createQuery("from " + obj.getClass().getName()).list();
        for (SuperBean p : superList) {
            logger.info("super List::" + p);
        }
        return superList;

    }

    @Override
    public List<SuperBean> listAllObjectsByCriteria(SuperBean obj, Map<String, String> param) {

        List results = null;
        Session session = this.sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(obj.getClass());
        for (Map.Entry<String, String> entry : param.entrySet()) {
            // System.out.println(entry.getKey() + "/" + entry.getValue());
            cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));

        }
        results = cr.list();

        return results;
    }

    @Override
    public SuperBean getObjectById(SuperBean obj, int id) {

        Session session = this.sessionFactory.getCurrentSession();
        SuperBean sobj = (SuperBean) session.load(obj.getClass(), new Integer(id));
        logger.info("Object loaded successfully, Object details=" + sobj);
        return sobj;
    }

    @Override
    public void deleteObjectById(SuperBean obj, int id) {
        Session session = this.sessionFactory.getCurrentSession();
        SuperBean sobj = (SuperBean) session.load(obj.getClass(), new Integer(id));
        if (null != sobj) {
            session.delete(sobj);
        }
        logger.info("Person deleted successfully, person details=" + sobj);
    }

    @Override
    public void deleteObject(SuperBean sobj) {
        Session session = this.sessionFactory.getCurrentSession();

        if (null != sobj) {
            session.delete(sobj);
        }
        logger.info("Person deleted successfully, person details=" + sobj);
    }

    @Override
    public boolean duplicateCheck(SuperBean obj, Map<String, String> param) {

        boolean flag = false;

        Session session = this.sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(obj.getClass());
        for (Map.Entry<String, String> entry : param.entrySet()) {
            // System.out.println(entry.getKey() + "/" + entry.getValue());
            cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));

        }
        List results = cr.list();
        if (results.size() > 0) {
            flag = true;
        }
        return flag;
    }
}
