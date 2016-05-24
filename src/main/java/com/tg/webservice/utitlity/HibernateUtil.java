/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tg.webservice.utitlity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Lucas
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session openSession() {
        return sessionFactory.openSession();
    }
    
    public static Session openSessionTransacao() {
        Session s = sessionFactory.openSession();
        s.beginTransaction().begin();
        return s;
    }
    
    public static void rollback(Session session) {
        try {
            if (session != null && session.isOpen()) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                session.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void commit(Session session) {
        if (session != null && session.isOpen()) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
    }
}
