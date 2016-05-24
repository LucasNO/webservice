/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tg.webservice.dao;

import com.tg.webservice.model.Usuario;
import com.tg.webservice.utitlity.HibernateUtil;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucas
 */
public class UsuarioDao {

    public static boolean insertUser(String nome, String email) throws SQLException, Exception {
        boolean insertStatus = false;
        Session s = HibernateUtil.openSession();
        try {
            Usuario usr = new Usuario();
            usr.setEmail(email);
            usr.setNomeUsuario(nome);
            usr.setDataRegistro(new Date());
            s.getTransaction().begin();
            s.saveOrUpdate(usr);
            HibernateUtil.commit(s);
            insertStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            HibernateUtil.rollback(s);
            throw e;
        } finally {
            s.close();
        }
        return insertStatus;
    }

    public static boolean checkLogin(String email) throws Exception {
        boolean isUserAvailable = false;
        Session s = HibernateUtil.openSession();
        try {
            String query = "SELECT usr FROM Usuario usr where usr.email = :email";
            Query q = s.createQuery(query);
            q.setString("email", email);
            Object pev = q.uniqueResult();
            if (pev != null) {
                isUserAvailable = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            s.close();
        }
        return isUserAvailable;
    }
    
    public static Usuario carregaUsuario(String email) throws Exception{
    	Usuario usr = new Usuario();
    	Session s = HibernateUtil.openSession();
    	try {
    		String query = "SELECT usr FROM Usuario usr where usr.email = :email";
            Query q = s.createQuery(query);
            q.setString("email", email);
            Object pev = q.uniqueResult();
            usr = (Usuario) pev;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			s.close();
		}
    	return usr;
    }
}
