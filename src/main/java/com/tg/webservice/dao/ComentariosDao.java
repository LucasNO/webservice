package com.tg.webservice.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tg.webservice.model.Comentarios;
import com.tg.webservice.model.Linhas;
import com.tg.webservice.model.Usuario;
import com.tg.webservice.utitlity.HibernateUtil;
import java.util.Calendar;
import java.util.TimeZone;

public class ComentariosDao {

    public static List<Comentarios> listarComentarios(Long idLinha) throws Exception {
        Session s = HibernateUtil.openSession();
        try {
            String query = "Select com From Comentarios com Where com.idLinha = :idLinha "
                    + "order by idComentarios desc";
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

    public static boolean insertComentario(String descricao, String titulo, Usuario usuario, Linhas linhas) throws SQLException, Exception {
        boolean insertStatus = false;
        Session s = HibernateUtil.openSession();
        try {
            Calendar cal_Two = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
            Comentarios com = new Comentarios();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String data = df.format(cal_Two.getTime());
            com.setData(data);
            com.setDescricao(descricao);
            com.setTitulo(titulo);
            com.setIdLinha(linhas);
            com.setEmail(usuario);
            s.getTransaction().begin();
            s.saveOrUpdate(com);
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
}
