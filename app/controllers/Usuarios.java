package controllers;

import play.*;
import play.libs.Crypto;
import play.mvc.*;

import java.util.*;

import org.apache.commons.codec.digest.Crypt;

import models.*;
import models.usuario.Usuario;

public class Usuarios extends Controller{
    
    public static void login(String email, String password){
        String passwordEnconded = Crypt.crypt(password);
        long userCount = Usuario.count(
                "email = ?1 and password = ?2", 
                email, passwordEnconded
                );
        if (userCount > 0) {
            renderText("Deu certo carai!");
        }
        renderText("Deu errado, seu porra");
        
    }

    public static void loginForm(){
        render();
    }

    public static void cadastroForm(){
        render();
    }

    public static void logout(){
        session.clear();
        Application.index();
    }
}
