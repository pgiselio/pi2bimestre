package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.artista.Artista;
import models.musica.Musica;

@With(GetUserLoggedIn.class)
public class Artistas extends Controller {
    public static void index() {
        List<Artista> artists = Artista.findAll();
        render(artists);
    }
    public static void salvar(Artista artista){
        artista.save();
        index();
    }
    public static void detalhar(Long id) {
        Artista artist = Artista.findById(id);
        if(artist == null)
            forbidden("Artista não encontrado!");
        List<Musica> musics = Musica.find("artist = ?1", artist).fetch();
        render(artist, musics);
    }

}