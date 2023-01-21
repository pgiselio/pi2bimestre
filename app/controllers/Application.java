package controllers;

import play.*;
import play.mvc.*;
import security.GetUserLoggedIn;

import java.util.*;

import models.*;
import models.musica.Musica;
import models.usuario.Usuario;

@With(GetUserLoggedIn.class)
public class Application extends Controller {

    public static void index() {
        Usuario user = Usuario.find("email = ?1", session.get("userSession")).first();
        List<Musica> Top10 =  Musicas.listarTop10();
        render(Top10, user);
    }

    public static void sobre() {
        render();
    }

    public static void artistas() {
        render();
    }

    public static void musicas() {
        render();
    }

}