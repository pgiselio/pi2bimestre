package controllers;

import play.*;
import play.cache.Cache;
import play.libs.Crypto;
import play.mvc.*;
import security.GetUserLoggedIn;

import java.util.*;

import models.*;
import models.artista.Artista;
import models.localizacao.Endereco;
import models.musica.Musica;
import models.usuario.Usuario;

@With(GetUserLoggedIn.class)
public class Usuarios extends Controller {

    @Before(only = { "login", "loginForm", "cadastrar", "cadastroForm"})
    static void redirectIfLoggedin() {
        if (session.get("userSession") != null) {
            Usuario user = Usuario.find("byEmail", session.get("userSession")).first();
            perfil(user.id);
        }
    }

    public static void loginForm() {
        render();
    }

    public static void cadastroForm() {
        List<Endereco> enderecos = Endereco.findAll();
        render(enderecos);
    }

    public static void confirmacao() {
        render();
    }

    public static void login(String email, String password) {
        String passwordEnconded = Crypto.passwordHash(password);
        Usuario user = Usuario.find(
                "email = ?1 and password = ?2",
                email, passwordEnconded).first();
        if (user == null) {
            flash.error("Login ou senha inválidos");
            loginForm();
        } else {
            session.put("userSession", user.email);
            session.put("papel", user.papel);
            perfil(user.id);
        }
    }

    public static void cadastrar(Usuario newUser) {
        Usuario user = Usuario.find(
                "email = ?1",
                newUser.email).first();
        if (user == null) {
            newUser.criptografarSenha();
            newUser.save();
            flash.success("Cadastro realizado com sucesso");
            cadastroForm();
        } else {
            flash.error("VISH!");
            cadastroForm();
        }

    }

    public static void perfil(Long id) {
        Usuario user = Usuario.findById(id);
        List<Musica> musics = Musica.find("sendedBy = ?1 AND isDeleted = ?2", user, false).fetch(5);
        render(user, musics);
    }

    public static void contribuicoes(Long id) {
        Usuario user = Usuario.findById(id);
        List<Musica> musics = Musica.find("sendedBy = ?1 AND isDeleted = ?2", user, false).fetch();
        renderTemplate("Usuarios/perfil.html", user, musics);
    }

    public static void favoritos(Long id) {
        Usuario user = Usuario.findById(id);
        List<Musica> musics = user.musicasFavoritas;
        renderTemplate("Usuarios/perfil.html", user, musics);
    }

    public static void enviarFoto(Usuario u){
        Usuario user = Usuario.find("byEmail", session.get("userSession")).first();
        user.photo = u.photo;
        user.save();
        Artistas.enviarFotoForm();
    }

    public static void downloadFoto(Long id) {
        Usuario usuario = Usuario.findById(id);
        renderBinary(usuario.photo.getFile());
    }

    public static void logout() {
        session.clear();
        Application.index();
    }
}
