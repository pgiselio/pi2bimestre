package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.musica.Musica;

public class Musicas extends Controller{
    public static void index() {
        render();
    }

    public static void buscaPorNome(String nome){
        final String nomeUpperCase = nome.toUpperCase();
        List<Musica> musicas = Musica.find(
            "SELECT m FROM Musica m LEFT JOIN Artista a INNER JOIN a.musicas mus "+
            "WHERE mus.id = m.id AND"+ 
            "(a.name.upperCase LIKE ? OR m.name.upperCase LIKE ? )", 
            nomeUpperCase).fetch();

        render(musicas);
    }
    
}