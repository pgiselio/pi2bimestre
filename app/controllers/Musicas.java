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
        if(musica != null){
            musica.views++;
            musica.save();
        }
        render(musica);
    }
    public static void detalharPorArtista(String artist, String name) {
        final String nameUpperCase = name.toUpperCase();
        Musica musica = Musica.find("SELECT m FROM Musica m WHERE isDeleted = ?1 AND upper(name) like ?2 ORDER BY m.views DESC", false, nameUpperCase).first();
        if(musica != null){
            musica.views++;
            musica.save();
        }
        renderTemplate("Musicas/detalhar.html", musica);
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

    public static List<Musica> listarTop10(){
        List<Musica> musics = Musica.find("SELECT m FROM Musica m WHERE isDeleted = ?1 ORDER BY m.views DESC", false).fetch(10);
        return musics;
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