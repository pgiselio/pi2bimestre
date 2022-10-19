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

    public static void form() {
        render();
    }

    public static void detalhar(Long id) {
        Musica musica = Musica.findById(id);
        render(musica);
    }

    public static void findByName(String name){
        final String nameUpperCase = name.toUpperCase();

        List<Musica> musics = Musica.find("upper(name) like ?1 AND isDeleted = ?2", "%" + nameUpperCase + "%", false).fetch(); 
        renderTemplate("Musicas/listar.html", name, musics);
    }

    public static void listar(){
        List<Musica> musics = Musica.find("isDeleted = ?1", false).fetch();
        render(musics);
    }

    public static void save(Musica music){
        music.save();
        listar();
    }

    public static void delete(Long id){
        final Musica music = Musica.findById(id);
        music.isDeleted = true;
        music.save();
        listar();
    }

    public static void editar(Long id) {
		Musica music = Musica.findById(id);
		renderTemplate("Musicas/form.html", music);
	}
    
}