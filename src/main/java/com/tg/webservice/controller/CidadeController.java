package com.tg.webservice.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.tg.webservice.dao.CidadeDao;
import com.tg.webservice.model.Cidade;

@Path("/cidade")
public class CidadeController {
	
	@GET
	@Path("/dolistar")
	@Produces(MediaType.APPLICATION_JSON)
	public String doListar(@QueryParam("idEstado") Long idEstado) {
		return this.listarCidadesJson(idEstado);
	}

	private String listarCidadesJson(Long idEstado) {
		try {
			List<Cidade> cidList = new CidadeDao().listarCidades(idEstado);
			JsonArray algumaCoisa = new JsonArray();
			for(Cidade dado : cidList){
				algumaCoisa.add(new Gson().toJsonTree(dado));
			}
			return algumaCoisa.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Deuuuuuuuuuu Ruim";
		}
	}

}
