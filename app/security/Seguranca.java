package security;

import controllers.Usuarios;
import models.usuario.Papel;
import models.usuario.Usuario;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

public class Seguranca extends Controller {
	@Before(unless = { "Musicas.detalhar", "Musicas.listarTop10", "Musicas.detalharPorArtista", "Musicas.findByName" })
	public static void checkAuthentication() {
		if (session.get("userSession") == null) {
			flash.error("É necessário se autenticar no sistema!");
			Usuarios.loginForm();
		}
	}
	@Before
    static void setConnectedUser() {
        if(session.get("userSession") != null) {
            Usuario user = Usuario.find("byEmail", session.get("userSession")).first();
            renderArgs.put("usuarioLogado", user);
        }
    }
	@Before
	static void verificarAdministrador() {
		String papel = session.get("papel");
		Administrador adminAnnotation = getActionAnnotation(Administrador.class);
		if (adminAnnotation != null && !Papel.ADMINISTRADOR.name().equals(papel)) {
			unauthorized("Acesso restrito aos administradores do sistema");
		}
	}	
}
