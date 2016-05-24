package com.tg.webservice.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.tg.webservice.dao.LinhasDao;
import com.tg.webservice.model.Linhas;

@Path("/linhas")
public class LinhasController {
	
	@GET
	@Path("/dolistar")
	@Produces(MediaType.APPLICATION_JSON)
	public String doListar(@QueryParam("idCidade") Long idCidade) {
		return this.listarLinhasJson(idCidade);
	}

	private String listarLinhasJson(Long idCidade) {
		try {
			List<Linhas> linhasList = new LinhasDao().listarLinhas(idCidade);
			JsonArray algumaCoisa = new JsonArray();
			for(Linhas dado : linhasList){
				algumaCoisa.add(new Gson().toJsonTree(dado));
			}
			return algumaCoisa.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Deuuuuuuuuuu Ruim";
		}
	}


}
