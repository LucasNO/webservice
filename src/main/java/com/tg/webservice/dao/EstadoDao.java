package com.tg.webservice.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tg.webservice.model.Estado;
import com.tg.webservice.utitlity.HibernateUtil;

public class EstadoDao {

	public static List<Estado> listarEstados() throws Exception {
		Session s = HibernateUtil.openSession();
		try {
			String query = "Select est From Estado est order by nomeEstado asc";
			Query q = s.createQuery(query);
			return q.list();
		} catch (Exception e) {
			throw e;
		} finally {
			s.close();
		}
	}
}
