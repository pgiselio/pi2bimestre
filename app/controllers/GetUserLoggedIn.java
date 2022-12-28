package controllers;

import models.usuario.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class GetUserLoggedIn extends Controller{
    @Before
    static void setConnectedUser() {
        if(session.get("userSession") != null) {
            Usuario user = Usuario.find("byEmail", session.get("userSession")).first();
            renderArgs.put("usuarioLogado", user);
        }
    }
}
