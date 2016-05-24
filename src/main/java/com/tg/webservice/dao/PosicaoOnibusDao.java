package com.tg.webservice.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tg.webservice.model.Linhas;
import com.tg.webservice.model.PosicaoOnibus;
import com.tg.webservice.model.Usuario;
import com.tg.webservice.utitlity.HibernateUtil;
import java.util.Calendar;
import java.util.TimeZone;

public class PosicaoOnibusDao {
	
	public static boolean insertPosicao(double latitude, double longitude, Usuario usuario, Linhas linhas) throws SQLException, Exception {
        boolean insertStatus = false;
        Session s = HibernateUtil.openSession();
        try {
            Calendar cal_Two = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
            PosicaoOnibus pos = new PosicaoOnibus();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
            String data = df.format(cal_Two.getTime());
            pos.setData(data);
            pos.setEmail(usuario);
            pos.setIdLinha(linhas);
            pos.setLatitude(latitude);
            pos.setLongitude(longitude);
            s.getTransaction().begin();
            s.saveOrUpdate(pos);
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
	
	public static List<PosicaoOnibus> listarPosicoes(Long idLinha) throws Exception {
		Session s = HibernateUtil.openSession();
		try {
			String query = "Select pos From PosicaoOnibus pos Where pos.idLinha = :idLinha "
					+ "order by idPosicaoOnibus desc";
			Query q = s.createQuery(query);
			q.setLong("idLinha", idLinha);
                        q.setMaxResults(15);
			return q.list();
		} catch (Exception e) {
			throw e;
		} finally {
			s.close();
		}
	}

}
