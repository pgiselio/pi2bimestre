package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{
    @Before
    static void checkAuthentication() {
    	if (session.get("operador") == null) {
    		flash.error("É necessário se autenticar no sistema!");
    		Usuarios.loginForm();
    	}
    }
}
