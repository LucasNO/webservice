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
import com.tg.webservice.dao.CidadeDao;
import com.tg.webservice.dao.ComentariosDao;
import com.tg.webservice.dao.LinhasDao;
import com.tg.webservice.dao.PosicaoOnibusDao;
import com.tg.webservice.dao.UsuarioDao;
import com.tg.webservice.model.Cidade;
import com.tg.webservice.model.Linhas;
import com.tg.webservice.model.PosicaoOnibus;
import com.tg.webservice.model.Usuario;

@Path("/posicaoonibus")
public class PosicaoOnibusController {
	
	@POST
	@Path("/enviar")
	@Produces(MediaType.APPLICATION_JSON)
	public void doInserir(@FormParam("latitude") double latitude, @FormParam("longitude") double longitude, 
			@FormParam("idLinha") Long idLinha, @FormParam("email") String email) throws Exception {
		inserirNovaPosicao(latitude, longitude, idLinha, email);
	}
	
	private void inserirNovaPosicao(double latitude, double longitude, Long idLinha, String email) throws Exception {
        System.out.println("Inside checkCredentials");
        Usuario usr = UsuarioDao.carregaUsuario(email);
        Linhas lin = LinhasDao.carregaLinha(idLinha);
            try {
                if (PosicaoOnibusDao.insertPosicao(latitude, longitude, usr, lin)) {
                    System.out.println("RegisterComentario if");
                }
            } catch (SQLException sqle) {
                System.out.println("RegisterComentario catch sqle");
                sqle.printStackTrace();
            } catch (Exception e) {
                System.out.println("Inside checkCredentials catch "+e);
                e.printStackTrace();
            }
    }
	
	@GET
	@Path("/dolistar")
	@Produces(MediaType.APPLICATION_JSON)
	public String doListar(@QueryParam("idLinha") Long idLinha) {
		return this.listarPosicoesJson(idLinha);
	}

	private String listarPosicoesJson(Long idLinha) {
		try {
			List<PosicaoOnibus> posList = new PosicaoOnibusDao().listarPosicoes(idLinha);
			JsonArray algumaCoisa = new JsonArray();
			for(PosicaoOnibus dado : posList){
				algumaCoisa.add(new Gson().toJsonTree(dado));
			}
			return algumaCoisa.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Deuuuuuuuuuu Ruim";
		}
	}

}
