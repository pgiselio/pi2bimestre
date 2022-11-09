package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.musica.Musica;

public class Application extends Controller {

    public static void index() {
        List<Musica> Top10 =  Musicas.listarTop10();
        render(Top10);
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