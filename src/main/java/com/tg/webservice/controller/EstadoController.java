package com.tg.webservice.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.tg.webservice.dao.EstadoDao;
import com.tg.webservice.model.Estado;

@Path("/estado")
public class EstadoController {

	@GET
	@Path("/dolistar")
	@Produces(MediaType.APPLICATION_JSON)
	public String doListar() {
		return this.listarEstadosJson();
	}

	private String listarEstadosJson() {
		try {
			List<Estado> estList = new EstadoDao().listarEstados();
			JsonArray algumaCoisa = new JsonArray();
			for(Estado dado : estList){
				algumaCoisa.add(new Gson().toJsonTree(dado));
			}
			return algumaCoisa.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Deuuuuuuuuuu Ruim";
		}
	}

}
