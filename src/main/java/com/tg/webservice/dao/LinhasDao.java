package com.tg.webservice.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tg.webservice.model.Linhas;
import com.tg.webservice.model.Usuario;
import com.tg.webservice.utitlity.HibernateUtil;

public class LinhasDao {
	
	public static List<Linhas> listarLinhas(Long idCidade) throws Exception {
		Session s = HibernateUtil.openSession();
		try {
			String query = "Select lin From Linhas lin Where lin.idCidade = :idCidade "
					+ "order by numeroLinha asc";
			Query q = s.createQuery(query);
			q.setLong("idCidade", idCidade);
			return q.list();
		} catch (Exception e) {
			throw e;
		} finally {
			s.close();
		}
	}

	public static Linhas carregaLinha(Long idLinha) throws Exception{
    	Linhas lin = new Linhas();
    	Session s = HibernateUtil.openSession();
    	try {
    		String query = "SELECT lin FROM Linhas lin where lin.idLinhas = :idLinha";
            Query q = s.createQuery(query);
            q.setLong("idLinha", idLinha);
            Object pev = q.uniqueResult();
            lin = (Linhas) pev;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			s.close();
		}
    	return lin;
    }
}
