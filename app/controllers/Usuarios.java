package controllers;

import play.*;
import play.libs.Crypto;
import play.mvc.*;

import java.util.*;

import models.*;
import models.localizacao.Endereco;
import models.usuario.Usuario;

public class Usuarios extends Controller {

    @Before(only = { "perfil" })
    static void checkAuthentication() {
        if (session.get("userSession") == null) {
            flash.error("É necessário se autenticar no sistema!");
            Usuarios.loginForm();
        }
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
            perfil();
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

    public static void perfil() {
        render();
    }

    public static void logout() {
        session.clear();
        Application.index();
    }
}
