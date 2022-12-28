package controllers;

import models.usuario.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	@Before
	static void checkAuthentication() {
		if (session.get("userSession") == null) {
			flash.error("É necessário se autenticar no sistema!");
			Usuarios.loginForm();
		}
		Usuario user = Usuario.find("byEmail", session.get("userSession")).first();
		renderArgs.put("usuarioLogado", user);

	}
}
