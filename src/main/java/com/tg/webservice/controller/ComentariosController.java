package com.tg.webservice.controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.tg.webservice.dao.ComentariosDao;
import com.tg.webservice.dao.LinhasDao;
import com.tg.webservice.dao.UsuarioDao;
import com.tg.webservice.model.Comentarios;
import com.tg.webservice.model.Linhas;
import com.tg.webservice.model.Usuario;

@Path("/comentario")
public class ComentariosController {
	
	@GET
	@Path("/dolistar")
	@Produces(MediaType.APPLICATION_JSON)
	public String doListar(@QueryParam("idLinha") Long idLinha) {
		return this.listarComentariosJson(idLinha);
	}

	private String listarComentariosJson(Long idLinha) {
		try {
			List<Comentarios> comList = new ComentariosDao().listarComentarios(idLinha);
			JsonArray algumaCoisa = new JsonArray();
			for(Comentarios dado : comList){
				algumaCoisa.add(new Gson().toJsonTree(dado));
			}
			return algumaCoisa.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Deuuuuuuuuuu Ruim";
		}
	}
	
	@POST
	@Path("/doNovoComentario")
	@Produces(MediaType.APPLICATION_JSON)
	public void doInserir(@FormParam("descricao") String descricao, @FormParam("titulo") String titulo, 
			@FormParam("email") String email, @FormParam("idLinha") Long idLinha) throws Exception {
		System.out.println(descricao+" "+titulo+" "+idLinha.toString()+" "+email);
		inserirNovoComentario(descricao, titulo, idLinha, email);
	}
	
	private void inserirNovoComentario(String descricao, String titulo, Long idLinha, String email) throws Exception {
        System.out.println("Inside checkCredentials");
        Usuario usr = UsuarioDao.carregaUsuario(email);
        Linhas lin = LinhasDao.carregaLinha(idLinha);
        if (StringUtils.isNotBlank(descricao) && StringUtils.isNotBlank(titulo)) {
            try {
                if (ComentariosDao.insertComentario(descricao, titulo, usr, lin)) {
                    System.out.println("RegisterComentario if");
                }
            } catch (SQLException sqle) {
                System.out.println("RegisterComentario catch sqle");
                sqle.printStackTrace();
            } catch (Exception e) {
                System.out.println("Inside checkCredentials catch "+e);
                e.printStackTrace();
            }
        } else {
            System.out.println("Inside checkCredentials else");
        }
    }
}
