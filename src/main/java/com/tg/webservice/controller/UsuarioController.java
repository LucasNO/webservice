/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tg.webservice.controller;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.tg.webservice.dao.UsuarioDao;
import com.tg.webservice.utitlity.Utitlity;

/**
 *
 * @author Lucas
 */
@Path("/usuario")
public class UsuarioController {

	@POST
	@Path("/doregister")
	@Produces(MediaType.APPLICATION_JSON)
	public String doRegister(@FormParam("nome") String name,
			@FormParam("email") String uname) {
		String response = "";
		int retCode = registerUser(name, uname);
		if (retCode == 0) {
			response = Utitlity.constructJSON("register", true);
		} else if (retCode == 1) {
			response = Utitlity.constructJSON("register", false,
					"You are already registered");
		} else if (retCode == 2) {
			response = Utitlity
					.constructJSON("register", false,
							"Special Characters are not allowed in Username and Password");
		} else if (retCode == 3) {
			response = Utitlity.constructJSON("register", false,
					"Error occured");
		}
		return response;

	}

	private int registerUser(String name, String uname) {
		System.out.println("Inside checkCredentials");
		int result = 3;
		if (StringUtils.isNotBlank(uname)) {
			try {
				if (!checkCredentials(uname))
					if (UsuarioDao.insertUser(name, uname)) {
						System.out.println("RegisterUSer if");
						result = 0;
					}
			} catch (SQLException sqle) {
				System.out.println("RegisterUSer catch sqle");
				if (sqle.getErrorCode() == 1062) {
					result = 1;
				} else if (sqle.getErrorCode() == 1064) {
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			} catch (Exception e) {
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
		} else {
			System.out.println("Inside checkCredentials else");
			result = 3;
		}

		return result;
	}

	@POST
	@Path("/dologin")
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin(@FormParam("email") String uname) {
		String response = "";
		if (checkCredentials(uname)) {
			response = Utitlity.constructJSON("login", true);
		} else {
			response = Utitlity.constructJSON("login", false,
					"Incorrect Email or Password");
		}
		return response;
	}

	private boolean checkCredentials(String uname) {
		System.out.println("Inside checkCredentials");
		boolean result = false;
		if (StringUtils.isNotBlank(uname)) {
			try {
				result = UsuarioDao.checkLogin(uname);
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		} else {
			result = false;
		}

		return result;
	}
}
