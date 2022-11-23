package controllers;

import play.*;
import play.libs.Crypto;
import play.mvc.*;

import java.util.*;

import org.apache.commons.codec.digest.Crypt;

import models.*;
import models.usuario.Usuario;

public class Usuarios extends Controller {

    public static void login(String email, String password) {
        String passwordEnconded = Crypt.crypt(password);
        Usuario user = Usuario.find(
                "email = ?1 and password = ?2",
                email, passwordEnconded).first();
        if (user == null) {
            flash.error("Login ou senha inválidos");
            loginForm();
        } else {
            session.put("operador", user.email);
        }
    }

    public static void cadastrar(Usuario newUser){
        Usuario user = Usuario.find(
                "email = ?1",
                newUser.email).first();
                if (user == null) {
                    flash.success("Cadastro realizado com sucesso");
                } else {
                    cadastroForm();
                }

    }

    public static void confirmacao(){
        render();
    }

    public static void loginForm() {
        render();
    }

    public static void cadastroForm() {
        render();
    }

    public static void perfil(){
        render();
    }

    public static void logout() {
        session.clear();
        Application.index();
    }
}
