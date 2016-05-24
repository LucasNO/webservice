package com.tg.webservice.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tg.webservice.model.Cidade;
import com.tg.webservice.utitlity.HibernateUtil;

public class CidadeDao {
	
	public static List<Cidade> listarCidades(Long idEstado) throws Exception {
		Session s = HibernateUtil.openSession();
		try {
			String query = "Select cid From Cidade cid Where cid.idEstado = :idEstado order by nomeCidade asc";
			Query q = s.createQuery(query);
			q.setLong("idEstado", idEstado);
			return q.list();
		} catch (Exception e) {
			throw e;
		} finally {
			s.close();
		}
	}
}
