package controllers;

import play.*;
import play.cache.Cache;
import play.libs.Crypto;
import play.mvc.*;

import java.util.*;

import models.*;
import models.cache.SessionStorage;
import models.localizacao.Endereco;
import models.musica.Musica;
import models.usuario.Usuario;

@With(GetUserLoggedIn.class)
public class Usuarios extends Controller {
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
            SessionStorage sessionStorage = new SessionStorage();
            sessionStorage.usuarioLogado = user;
            Cache.set(session.getId(), sessionStorage);
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

    public static void confirmacao() {
        render();
    }

    public static void loginForm() {
        render();
    }

    public static void cadastroForm() {
        List<Endereco> enderecos = Endereco.findAll();
        render(enderecos);
    }

    public static void perfil(Long id) {
        Usuario user = Usuario.findById(id);
        List<Musica> musics = Musica.find("sendedBy = ?1 AND isDeleted = ?2", user, false).fetch(5);
        render(user, musics);
    }
    public static void logout() {
        session.clear();
        Application.index();
    }
}
