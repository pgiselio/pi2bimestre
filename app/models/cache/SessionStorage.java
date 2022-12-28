package models.cache;

import models.usuario.Usuario;
import play.mvc.Controller;

public class SessionStorage implements java.io.Serializable{
    public static Usuario usuarioLogado;
}
